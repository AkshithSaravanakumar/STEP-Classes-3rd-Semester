package leave;

/**
 * Leave processing. Only the {@link Employee} base type and {@link Reviewer}
 * interface are referenced here, so a new employee type needs no change.
 */
public class LeaveWorkflow {

    private LeaveWorkflow() {
    }

    /**
     * @param employee the requesting employee
     * @param startDate first day of leave
     * @param endDate last day of leave
     * @param days total leave days
     * @return the created request, or null when the policy refused it
     */
    public static LeaveRequest submit(Employee employee, String startDate, String endDate, int days) {
        return employee.submitLeave(startDate, endDate, days);
    }

    /**
     * @param reviewer the reviewing manager
     * @param request the request to approve
     * @return true when approved
     */
    public static boolean approve(Reviewer reviewer, LeaveRequest request) {
        return reviewer.review(request);
    }

    /**
     * @param reviewer the reviewing manager
     * @param request the request to reject
     * @param reason why it is refused
     * @return true when rejected
     */
    public static boolean reject(Reviewer reviewer, LeaveRequest request, String reason) {
        return reviewer.reject(request, reason);
    }

    public static void main(String[] args) {
        FullTimeEmployee john = new FullTimeEmployee("E1", "John");
        PartTimeEmployee jane = new PartTimeEmployee("E2", "Jane");
        Reviewer alice = Reviewer.named("Alice");
        Reviewer bob = Reviewer.named("Bob");

        LeaveRequest johnRequest = submit(john, "Jan 1", "Jan 5", 5);
        approve(alice, johnRequest);

        LeaveRequest janeRequest = submit(jane, "Feb 10", "Feb 11", 2);
        reject(bob, janeRequest, "Insufficient part-time balance");

        johnRequest.changeStatus(LeaveStatus.PENDING);
    }
}