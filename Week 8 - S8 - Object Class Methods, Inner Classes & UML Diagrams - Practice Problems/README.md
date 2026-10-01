# Week 8 - S8 - Object Class Methods, Inner Classes & UML Diagrams - Practice Problems

Solutions for `STEP SEM-3 Week 8 Practice Problems.pdf`.

## Layout

Each of the five coding questions is an independent system, so each lives in its
own package. That keeps `Room`, `Customer`, `Product` and friends from colliding
across questions, which they otherwise would.

| Package | Question | Entry point |
| --- | --- | --- |
| `vehiclerental` | Q1 Vehicle Rental System | `RentalSystem.main` |
| `leave` | Q2 Employee Leave Request Workflow | `LeaveWorkflow.main` |
| `exam` | Q3 Online Examination System | `ExaminationSystem.main` |
| `hotel` | Q4 Hotel Booking System | `BookingSystem.main` |
| `payment` | Q5 Payment Processing for a Shopping System | `PaymentProcessor.main` |

## How the design requirements are met

**Q1 Vehicle Rental** — `Vehicle` is abstract with `calculateCharge(int)`; `Sedan`,
`SUV` and `Truck` each supply their own rule. `Rental` snapshots the charge once,
in its constructor. `RentalSystem` holds a `RentalRegistry` inner class, which is
what enforces "no active rental for this vehicle". A fourth category such as
`Van` is a new subclass; `RentalSystem` does not change.

**Q2 Leave workflow** — `Employee` is abstract and delegates the rules to a
composed `LeavePolicy`. `FullTimeEmployee`, `PartTimeEmployee` and `Contractor`
differ only in the policy they pass up. `LeaveRequest` guards every status change
behind `changeStatus`, so `APPROVED` and `REJECTED` are terminal. The workflow
depends on the `Employee` type and the `Reviewer` interface only.

**Q3 Examination** — `Question` is abstract with `evaluate(Answer)`; MCQ, true/false
and short-answer each grade themselves. `Attempt.AttemptStatus` is an inner enum,
and `Attempt` holds its own answers (the UML composition) and scores them by
calling `evaluate`, so it is unaware of question types.
`ExaminationSystem.AttemptRegistry` (inner class) enforces one submitted attempt
per student per examination.

**Q4 Hotel** — `Room` is abstract with `calculatePrice(int)`. `Reservation` stores
its period, computes price once from the room, and owns its own cancellation
deadline. `BookingSystem.isAvailable` skips cancelled reservations and uses
`Reservation.overlaps`, so a cancelled booking frees the room.

**Q5 Payment** — `PaymentMethod` is the interface every payment path depends on.
`PaymentProcessor.pay` refuses an empty order, and calls `markPaid()` only on
`PaymentResult.isSuccess()`, so a failure leaves the order `PENDING`.
`Order.OrderItem` is an inner class holding product and quantity together.

## Answers

- `MCQ Answers.md` — the ten multiple-choice questions with reasons.
- `Concept Answers.md` — the five written questions.

## Compile and run

```powershell
$out = "C:\temp\wk8"
New-Item -ItemType Directory -Force -Path $out | Out-Null
javac -encoding UTF-8 -d $out (Get-ChildItem -Recurse -Filter *.java | % { $_.FullName })
java -cp $out vehiclerental.RentalSystem
```
