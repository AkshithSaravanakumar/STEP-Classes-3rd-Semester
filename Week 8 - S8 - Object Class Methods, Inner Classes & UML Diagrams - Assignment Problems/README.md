# Week 8 - S8 - Object Class Methods, Inner Classes & UML Diagrams - Assignment Problems

Solutions for `STEP SEM-3 Week 8 Assignment Problems.pdf`.

## Layout

Each question is an independent system, so each lives in its own package. That
keeps `Student`, `Member` and friends from colliding across questions, which
they otherwise would.

| Package | Question | Entry point |
| --- | --- | --- |
| `laundry` | Q1 Hostel Laundry Queue | `LaundryService.main` |
| `submission` | Q2 Assignment Submission Portal | `SubmissionPortal.main` |
| `cinema` | Q3 Campus Premiere Ticket Counter | `TicketCounter.main` |
| `gym` | Q4 FitZone Membership Desk | `MembershipDesk.main` |
| `notice` | Q5 Campus Notice Broadcaster | `NoticeBoard.main` |

## How the design requirements are met

**Q1 Laundry** — `WashType` is abstract with `getDurationMinutes()` and
`calculateCharge()`. `QuickWash`, `NormalWash` and `HeavyWash` supply their own
numbers, and `DelicateWash` was added afterwards without touching the service.
`WashingMachine` holds a `WashCycle` as a nested state and refuses a start while
that cycle is running, so "already busy" is a property of the machine rather than
a flag the service has to track. `LaundryService.MachineRegistry` is the inner
class that owns the machines and the running cycles.

**Q2 Submissions** — `Assignment` is abstract and owns the due date, the late-day
count and the penalty rule; `CodingAssignment` carries `getPenaltyPerDay()` at
10% and `WrittenAssignment` at 20%. `Submission` is immutable about its marks:
`grade` refuses a second attempt and recomputes the final mark from the penalty,
so grading the same object twice cannot move the number.
`SubmissionPortal.GradedRecordStore` is the inner class that stops a
resubmission after grading.

**Q3 Cinema** — `Seat` is abstract with `calculatePrice()`; `RegularSeat`,
`PremiumSeat` and `ReclinerSeat` each set their own rate. `Show` owns its seat map
and the booked-seat set, so `isSeatAvailable`, `holdSeats` and `releaseSeats` stay
consistent with each other. `TicketCounter` checks the six-seat ceiling, rejects
unknown, already-booked and repeated seat ids, and only then builds the `Booking`.

**Q4 Gym** — `MembershipPlan` is the interface with a `default` `calculateFee()`,
so `MonthlyPlan`, `QuarterlyPlan` and `AnnualPlan` differ only in months and
discount. `HalfYearlyPlan` was added afterwards and required no change to
`Membership` or `MembershipDesk`. `MembershipStatus.canFreeze()`, `canUnfreeze()`
and `canExpire()` are the guards, and every transition in `Membership` routes
through them, so an `EXPIRED` membership cannot be brought back.

**Q5 Notice board** — `NotificationChannel` is the interface the board depends on.
`EmailChannel`, `SmsChannel` and `AppChannel` implement it, and `WhatsAppChannel`
was added afterwards with no change to `NoticeBoard`. `Notice` validates itself in
its constructor, so a notice with no target department cannot exist, and
`Student` requires at least one preferred channel. `NoticeBoard.post` only ever
iterates over `getPreferredChannels()`.

## Compile and run

```powershell
$src = "D:\SRM KTR B.Tech Degree\STEP Program\2nd Year\STEP-Classes-3rd-Semester\Week 8 - S8 - Object Class Methods, Inner Classes & UML Diagrams - Assignment Problems"
$out = "C:\temp\wk8assignment"
New-Item -ItemType Directory -Force -Path $out | Out-Null
javac -encoding UTF-8 -Xlint:all -d $out (Get-ChildItem -LiteralPath $src -Recurse -Filter *.java | % { $_.FullName })
java -cp $out laundry.LaundryService
java -cp $out submission.SubmissionPortal
java -cp $out cinema.TicketCounter
java -cp $out gym.MembershipDesk
java -cp $out notice.NoticeBoard
```
