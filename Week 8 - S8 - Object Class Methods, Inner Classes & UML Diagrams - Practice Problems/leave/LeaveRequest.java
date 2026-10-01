package leave;

/**
 * A leave request is a plain state holder. It owns its status and guards every
 * transition itself, so the review workflow cannot drive it into an invalid
 * state even by mistake.
 *
 * UML:
 * <pre>
 * LeaveRequest
 *  - employee : Employee
 *  - startDate : String
 *  - endDate : String
 *  - days : int
 *  - status : LeaveStatus
 *  - reviewer : String
 *  + getStatus() : LeaveStatus
 *  + approve(reviewer : String) : boolean
 *  + reject(reviewer : String, reason : String) : boolean
 *  + changeStatus(target : LeaveStatus) : boolean
 * </pre>
 *
 * LeaveRequest "0..*" -- "1" Employee
 */
public class LeaveRequest {

    private final Employee employee;
    private final String startDate;
    private final String endDate;
    private final int days;

    private LeaveStatus status = LeaveStatus.PENDING;
    private String reviewer;
    private String decisionNote;

    /**
     * @param employee the requesting employee
     * @param startDate first day of leave, as text
     * @param endDate last day of leave, as text
     * @param days total leave days
     */
    public LeaveRequest(Employee employee, String startDate, String endDate, int days) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getDays() {
        return days;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getDecisionNote() {
        return decisionNote;
    }

    /**
     * @param reviewer the manager who approved the request
     * @return true when the transition was allowed
     */
    public boolean approve(String reviewer) {
        if (!changeStatus(LeaveStatus.APPROVED)) {
            return false;
        }
        this.reviewer = reviewer;
        return true;
    }

    /**
     * @param reviewer the manager who rejected the request
     * @param reason why the request was rejected
     * @return true when the transition was allowed
     */
    public boolean reject(String reviewer, String reason) {
        if (!changeStatus(LeaveStatus.REJECTED)) {
            return false;
        }
        this.reviewer = reviewer;
        this.decisionNote = reason;
        return true;
    }

    /**
     * The single guarded entry point for every status change. Once the request
     * has reached APPROVED or REJECTED it is immutable.
     *
     * @param target the requested new status
     * @return true when the change was applied
     */
    public boolean changeStatus(LeaveStatus target) {
        if (!status.isMutable()) {
            System.out.println("Cannot change leave request status from " + status + " to " + target + ".");
            return false;
        }
        this.status = target;
        return true;
    }

    @Override
    public String toString() {
        return employee.getName() + "'s leave request (" + startDate + "-" + endDate + ")";
    }
}