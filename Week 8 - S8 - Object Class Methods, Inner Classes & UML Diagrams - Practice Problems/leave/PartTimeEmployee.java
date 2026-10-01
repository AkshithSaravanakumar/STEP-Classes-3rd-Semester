package leave;

/**
 * Part-time employees are pro-rated, so their ceiling is lower than a
 * full-time employee's.
 */
public class PartTimeEmployee extends Employee {

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
            return "part-time allowance is " + maxLeaveDays + " days per request";
        }
    }

    public PartTimeEmployee(String employeeId, String name) {
        super(employeeId, name, new Allowance(10));
    }
}