# MCQ Answers

## Q1 — C, Polymorphism

`TicketProcessor` handles concerts, plays and matches through one code path. It
calls a common method and the actual runtime type decides the behaviour, which is
runtime polymorphism. Inheritance would describe the event hierarchy but is not
what lets the processor treat them uniformly.

## Q2 — C, Encapsulation

`dateOfBirth` is hidden and only changed through `updateProfileInfo`, which
validates before mutating. Hiding state behind a validating method is
encapsulation. Abstraction would be about exposing an interface without
implementation detail, which is not the emphasis here.

## Q3 — C, Author 1 --- 1..* Book

An author writes one or more books, so Author to Book is `1` to `1..*`. A book has
exactly one author. The books survive the author's removal, so this is a plain
association, not composition.

## Q4 — D, Composition

The cart items have no independent existence and are destroyed with the cart, so
the ownership and lifecycle dependency is total. Composition is the filled diamond;
aggregation would be a hollow one and would allow the items to outlive the cart.

## Q5 — D, Generalization

`ProgrammingCourse` is an "is-a" `Course`, which is the definition of
generalization. Realization would require implementing an interface, and
`ProgrammingCourse` does extend a class.

## Q6 — C, Realization

`PdfDocument` implements the `Printable` contract, so the connection is a
dashed-arrow realization. The point of the scenario is that the `Printer` is
decoupled from the concrete document type, which is a realization rather than a
direct association.

## Q7 — B, Association

Neither side owns the other and both can exist independently, so this is a plain
association. An order referencing a customer id that may later be archived rules
out composition, and there is no subclass relationship, ruling out generalization.

## Q8 — C, State diagram

A task's lifecycle is a set of states and the valid transitions between them, which
is exactly what a state diagram models. A sequence diagram would need a specific
scenario to be meaningful, and a class diagram shows static structure.

## Q9 — B, Abstraction

The reporting module depends on the `generate()` contract without knowing how any
report is built. Hiding implementation behind a contract is abstraction.

## Q10 — C, Sequence diagram

The scenario is about the order and timing of messages exchanged between several
objects over time, which is sequence-diagram territory. An activity diagram would
show the flow of steps but not the participants exchanging messages.

## Answer key

| Q | Answer |
| --- | --- |
| 1 | C Polymorphism |
| 2 | C Encapsulation |
| 3 | C Author 1 --- 1..* Book |
| 4 | D Composition |
| 5 | D Generalization |
| 6 | C Realization |
| 7 | B Association |
| 8 | C State diagram |
| 9 | B Abstraction |
| 10 | C Sequence diagram |
