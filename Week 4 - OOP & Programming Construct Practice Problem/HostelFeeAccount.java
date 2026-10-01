public class HostelFeeAccount extends FeeAccount {

    private String hostelBlock;

    /**
     * @param regNo      registration number
     * @param amountDue  fee amount due
     * @param hostelBlock hostel block allotted
     */
    public HostelFeeAccount(String regNo, double amountDue, String hostelBlock) {
        super(regNo, amountDue);
        this.hostelBlock = hostelBlock;
    }

    public String getHostelBlock() {
        return hostelBlock;
    }

    /**
     * Hostel payment behaviour: the amount is split across two installments.
     *
     * @param amount payment amount
     */
    @Override
    public void pay(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}