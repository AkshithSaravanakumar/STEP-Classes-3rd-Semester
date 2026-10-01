import java.util.Scanner;

public class PayrollAccount {

    private double basicSalary;
    private double bonus;

    /**
     * Creates a payroll account with an opening basic salary. A negative value
     * is corrected to 0 and reported, since the salary can never be negative.
     *
     * @param basicSalary opening basic salary
     */
    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: basic salary cannot be negative, starting at 0.0");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0.0;
    }

    /**
     * Credits a bonus to the account.
     *
     * @param amount bonus amount to add
     */
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus rejected: amount must be greater than 0");
            return;
        }

        bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    /**
     * Deducts tax as a percentage of the current basic salary.
     *
     * @param percent tax percentage, must be between 0 and 100
     */
    public void deductTax(double percent) {
        if (percent <= 0 || percent > 100) {
            System.out.println("Tax rejected: percent must be between 0 and 100");
            return;
        }

        basicSalary -= basicSalary * (percent / 100.0);
        System.out.println("Tax deducted: " + (int) percent + "%");
    }

    /**
     * Read-only access to the net salary. There are deliberately no setters, so
     * basicSalary and bonus can never be overwritten from outside the class.
     *
     * @return basic salary plus bonus
     */
    public double getNetSalary() {
        return basicSalary + bonus;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter opening basic salary: ");
        double basicSalary = scanner.nextDouble();

        PayrollAccount payroll = new PayrollAccount(basicSalary);

        System.out.print("Enter bonus amount: ");
        payroll.creditBonus(scanner.nextDouble());

        System.out.print("Enter tax percent: ");
        payroll.deductTax(scanner.nextDouble());

        System.out.println("Net salary: Rs " + payroll.getNetSalary());

        scanner.close();
    }
}