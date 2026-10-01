public class CompanyEmployee {

    private String empName;
    private double salary;

    /**
     * Shared by every employee object, so it is declared static instead of being
     * stored separately on each instance.
     */
    private static String companyName = "Bright Horizon Technologies";

    private static int employeeCount;

    /**
     * Creates an employee and increments the shared employeeCount once per
     * constructed object.
     *
     * @param empName employee name
     * @param salary  monthly salary
     */
    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public String getEmpName() {
        return empName;
    }

    public double getSalary() {
        return salary;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    /**
     * Static method: it may only touch static members, never instance fields
     * such as empName or salary.
     */
    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        CompanyEmployee first = new CompanyEmployee("Priya", 48000);
        CompanyEmployee second = new CompanyEmployee("Karthik", 52000);
        CompanyEmployee third = new CompanyEmployee("Anitha", 61000);

        // Accessed through the class name, not through any object
        CompanyEmployee.printCompanyInfo();
    }
}