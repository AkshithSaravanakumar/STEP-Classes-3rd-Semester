package leave;

/**
 * Base employee. The leave workflow only ever talks to this type, so a new
 * employee type plugs in without touching the workflow.
 *
 * UML:
 * <pre>
 * Employee (abstract)
 *  - employeeId : String
 *  - name : String
 *  + getName() : String
 *  + getPolicy() : LeavePolicy
 *  + submitLeave(startDate, endDate, days) : LeaveRequest
 *
 * Employee <|-- FullTimeEmployee
 * Employee <|-- PartTimeEmployee
 * Employee <|-- Contractor
 * Employee ..> LeavePolicy (composition, one per employee)
 * </pre>
 */
public abstract class Employee {

    private final String employeeId;
    private final String name;
    private final LeavePolicy policy;

    /**
     * @param employeeId the employee's unique id
     * @param name the employee's display name
     * @param policy the leave rules that apply to this employee type
     */
    protected Employee(String employeeId, String name, LeavePolicy policy) {
        this.employeeId = employeeId;
        this.name = name;
        this.policy = policy;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public LeavePolicy getPolicy() {
        return policy;
    }

    /**
     * Validates against this employee's own policy before creating a request,
     * so policy violations never enter the review workflow.
     *
     * @param startDate first day of leave
     * @param endDate last day of leave
     * @param days total leave days
     * @return the new request, or null when the policy rejects it
     */
    public LeaveRequest submitLeave(String startDate, String endDate, int days) {
        if (days <= 0) {
            System.out.println("Leave request for " + name + " rejected: days must be positive.");
            return null;
        }
        LeaveRequest request = new LeaveRequest(this, startDate, endDate, days);
        if (!policy.isEligible(request)) {
            System.out.println("Leave request for " + name + " (" + startDate + "-" + endDate
                    + ") not allowed by policy: " + policy.describe());
            return null;
        }
        System.out.println("Leave request submitted for " + name + " (" + startDate + "-" + endDate + ").");
        System.out.println("Status: " + request.getStatus() + ".");
        return request;
    }
}