public class FeeAccount {

    private String regNo;
    private double amountDue;

    /**
     * @param regNo     registration number
     * @param amountDue fee amount due
     */
    public FeeAccount(String regNo, double amountDue) {
        this.regNo = regNo;
        this.amountDue = amountDue;
    }

    public String getRegNo() {
        return regNo;
    }

    public double getAmountDue() {
        return amountDue;
    }

    /**
     * Day-scholar payment behaviour: the whole amount is paid at once.
     *
     * @param amount payment amount
     */
    public void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}