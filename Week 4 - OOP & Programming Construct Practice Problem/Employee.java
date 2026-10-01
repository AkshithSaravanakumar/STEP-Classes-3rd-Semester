public class Employee {

    private String empId;
    private double salary;

    /**
     * @param empId  employee id
     * @param salary starting salary
     */
    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public double getSalary() {
        return salary;
    }

    /**
     * Adds the bonus to this employee's salary. The parameter is named salary
     * exactly like the field, so both this.salary and the bare salary are used
     * deliberately to show which one is the field and which is the argument.
     *
     * @param salary the bonus amount to add
     */
    public void raiseSalary(double salary) {
        double oldSalary = this.salary;
        this.salary = this.salary + salary;
        System.out.println(empId + " | Salary raised from Rs " + oldSalary
                + " to Rs " + this.salary);
    }

    /**
     * Prints the final salary line.
     */
    public void printFinalSalary() {
        System.out.println(empId + " | Final Salary: Rs " + salary);
    }

    public static void main(String[] args) {
        String[] empIds = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};
        double bonus = 5000;

        Employee[] team = new Employee[empIds.length];

        // The identical bonus is applied to every employee in a single pass
        for (int i = 0; i < team.length; i++) {
            team[i] = new Employee(empIds[i], startingSalaries[i]);
            team[i].raiseSalary(bonus);
        }

        for (int i = 0; i < team.length; i++) {
            team[i].printFinalSalary();
        }
    }
}