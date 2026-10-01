package leave;

/**
 * Full-time employees get the most generous leave allowance, expressed as an
 * inner class so the ceiling lives next to the rule that uses it.
 */
public class FullTimeEmployee extends Employee {

    /**
     * Inner class: the allowance this employee type grants. Keeping it as a
     * nested type means the constant and the check it drives are read together.
     *
     * UML: FullTimeEmployee o-- Allowance (inner class)
     */
    public static class Allowance implements LeavePolicy {

        private final int maxLeaveDays;

        public Allowance(int maxLeaveDays) {
            this.maxLeaveDays = maxLeaveDays;
        }

        public int getMaxLeaveDays() {
            return maxLeaveDays;
        }

        @Override
        public boolean isEligible(LeaveRequest request) {
            return request.getDays() <= maxLeaveDays;
        }

        @Override
        public String describe() {
            return "full-time allowance is " + maxLeaveDays + " days per request";
        }
    }

    public FullTimeEmployee(String employeeId, String name) {
        super(employeeId, name, new Allowance(20));
    }
}