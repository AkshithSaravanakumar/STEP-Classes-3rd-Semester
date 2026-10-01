# Concept Answers

## Q1 — Encapsulation in the `User` object

The `User` object is responsible for one thing above all: never holding an invalid
email. That means the `email` field is `private`, so no caller can assign to it
directly, and the only way to change it is `changeEmail(String)`.

If the field were accessible, a caller could write `"not-an-email"`, an empty
string, or another user's address, and the object would happily store it. Because
the object has no say in the matter, nothing guarantees that a `User` read back
out of the system has a usable email. Any other class holding a `User` reference
would be equally free to corrupt it, and the corruption would be invisible until
much later, at the point where a confirmation mail is sent.

Routing every change through `changeEmail` inverts that. The method runs its own
validation first: it checks the format (a local part, an `@`, a domain with a
dot, no spaces) and then checks uniqueness, typically by asking a repository or
service. Only if both pass does it assign. If either fails, the field keeps its
previous value and the object stays consistent.

So the state the `User` controls is the email's validity and uniqueness, and
control is achieved by making the field private and exposing a validating method
in its place. Readers still get the current value through `getEmail`, so
encapsulation here costs nothing in usability while removing the ability to put
the object into a bad state. This is why the field is not merely "best practice
private" but the mechanism the whole design rests on.

## Q2 — Inheritance versus composition for optional capabilities

Inheritance is the right choice when the relationship is genuinely "is-a" and
stable: a `CSVReport` really is a `Report` and could not be described any other
way, and every subclass genuinely shares the same core structure with no
variation. If a type always needs the parent's behaviour and does not need to
swap it out, inheritance keeps the design simple.

Composition is the right choice when capabilities are optional, independent and
varying, which is exactly the situation here. A sales summary needs `DataExport`
and does not need `DataVisualization`; a chart report is the reverse. These are
capabilities a report *has*, not categories it *is*. Modelling them as optional
interfaces that a report simply implements, or as collaborators passed in, means
each report declares only what it can do.

Forcing everything into one inheritance hierarchy breaks down in three ways.
First, a single chain cannot express optionality: a `ChartOnlyReport` would have to
sit somewhere on the chain, and every sibling inherits whatever it declares,
including the parts it does not use. Second, you get the fragile base class
problem, where adding a capability to the parent forces edits across all
subclasses and often breaks them. Third, the number of combinations multiplies. With
two optional capabilities you need four combinations; add a third and you need
eight. A linear hierarchy either collapses them into near-duplicate classes or
grows combinatorially.

Composition avoids all three because new features become new collaborators that
plug in, not new subclasses that must be threaded through the tree. Adding a
`PdfReport`, a `ScheduledReport`, or a third capability like `DataStreaming` is an
additive change that leaves the existing hierarchy untouched. In this
`ReportingService`, optional capabilities belong in interfaces the reports
implement selectively, and the service depends on those interfaces rather than on
concrete report types.

## Q3 — Abstraction, overriding and runtime polymorphism in a notification system

`Notification` is declared as an interface with a single `send(String)` method,
plus implementations for `EmailNotification`, `SMSNotification` and
`PushNotification`. Abstraction is what allows the service to hold a reference to
the contract rather than to any one implementation; `NotificationService` compiles
against `Notification` and never names a concrete class.

When a notification is triggered, the service calls `send(...)` on the interface
reference. At compile time the compiler checks only the interface signature. At
runtime, the JVM looks at the actual object the reference points at, finds the
method in that object's class, and dispatches there. That late binding is runtime
polymorphism, and overriding is the mechanism that makes it possible, since each
implementation supplies its own body for the same signature.

The absence of conditional logic is the practical payoff. A version written with
`if (type == EMAIL) ... else if (type == SMS)` has a hard-coded list of types, so
adding a fourth means editing the service and creating a new branch. The
polymorphic version has no list at all: the service sends, and the object decides.

Introducing `InAppNotification` therefore means writing one new class that
implements `Notification`. The service is not edited, recompiled or retested for
correctness, because no code in it changed. This is the open/closed principle in
practice: open for extension, closed for modification. The one caveat is that
`NotificationService` must never start branching on notification type, since that
would reintroduce the coupling the interface removed.

## Q4 — Composition versus aggregation in the organization scenario

Composition means the parts cannot exist without the whole, and their lifecycles
are tied: when the whole is destroyed, the parts are destroyed with it, and the
parts have no meaningful life of their own. Aggregation is the weaker "shared"
relationship. The parts are held by the whole but have independent existence and
can outlive it, be passed elsewhere, or be reassigned to a different owner.

`Organization` and `Department` is composition. When the organization ceases to
exist, its departments are dissolved, and a department has no meaning outside the
organization that created it. The lifetime dependency is total, so the filled
diamond is correct. The multiplicity would be one organization composed of one or
more departments, and each department belongs to exactly one organization.

`Department` and `Employee` is aggregation. Dissolving a department does not
dissolve its employees; they are reassigned or become independent, and employees
move between departments over a career. The employee clearly exists outside any
particular department, so the hollow diamond is correct. The multiplicity is
one department to zero-or-more employees, and each employee belongs to at most one
department at a time.

The distinction is about ownership, not about the number of objects involved. The
test is what happens to the parts when the whole goes away: destroyed with it means
composition, surviving it means aggregation.

## Q5 — Student/course multiplicity versus the duplicate-enrollment rule

A course can be taken by many students and a student can enroll in many courses,
so the association is many-to-many. In UML multiplicity notation that is written
`Student "*" -- "*" Course`, meaning one student relates to zero or more courses
and one course relates to zero or more students. The zero lower bound is important:
a new student has not enrolled in anything yet, and a brand-new course may have no
enrollments, and neither state should be considered invalid.

The business rule, however, is a different kind of statement. Multiplicity
describes *how many* associations may exist between a pair of objects. It is a
statement about counts, and by design it permits many associations between the same
student and the same course. A model that merely says `*` to `*` will happily hold
three enrollment records linking one student to one course without complaint.

Preventing duplicates is a constraint on *identity and uniqueness* of the
association, not on how many associations are allowed. In practice it is enforced
by mechanisms outside the multiplicity notation: a uniqueness constraint on the
pair in the database, a uniqueness check inside an `enroll(Student, Course)`
method that refuses a second record, or a set keyed by the student-course pair so
that re-adding the same key is a no-op.

So multiplicity and the rule are related but distinct. The multiplicity sets up a
many-to-many relationship, which is what makes a uniqueness constraint necessary in
the first place. The rule is then layered on top by system logic, because
multiplicity has no vocabulary for "at most one". In other words, multiplicity
describes the shape of the relationship, while business rules restrict which
instances of that shape are actually allowed.
