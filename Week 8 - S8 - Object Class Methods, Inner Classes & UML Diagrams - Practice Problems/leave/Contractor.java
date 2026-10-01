package leave;

/**
 * Contractors get no paid leave, so their policy rejects every request. Note
 * that the workflow still processes them identically; only the policy differs.
 */
public class Contractor extends Employee {

    public static class NoPaidLeave implements LeavePolicy {

        @Override
        public boolean isEligible(LeaveRequest request) {
            return false;
        }

        @Override
        public String describe() {
            return "contractors are not entitled to paid leave";
        }
    }

    public Contractor(String employeeId, String name) {
        super(employeeId, name, new NoPaidLeave());
    }
}