public class Employee {

    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    /**
     * Constructor for permanent employees with a known salary from day one.
     *
     * @param empId   employee id
     * @param empName employee name
     * @param salary  monthly salary
     */
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    /**
     * Overloaded constructor for interns who join without a fixed salary
     * structure. Chains to the three-argument constructor via this(...) so the
     * shared setup logic is written only once, then flags the employee as an
     * intern afterwards.
     *
     * @param empId   employee id
     * @param empName employee name
     */
    public Employee(String empId, String empName) {
        this(empId, empName, 0);
        this.isIntern = true;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isIntern() {
        return isIntern;
    }

    /**
     * Prints all four fields on a single line.
     */
    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000);
        Employee intern = new Employee("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}