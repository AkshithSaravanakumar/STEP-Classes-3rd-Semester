public class LateFeeAccount {

    private String regNo;
    private double totalFee;

    /**
     * @param regNo    registration number
     * @param totalFee total fee for the account
     */
    public LateFeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    public String getRegNo() {
        return regNo;
    }

    public double getTotalFee() {
        return totalFee;
    }

    /**
     * Late fee is one percent of the total fee per day late. Declared final so
     * the formula can never be quietly changed by a subclass.
     *
     * @param daysLate number of days the payment is late
     * @return late fee amount
     */
    final double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0.0;
        }
        return totalFee * (daysLate / 100.0);
    }

    /**
     * Prints the summary line for an account, or a skip message when the
     * account is on time. Declared final so the locked formula cannot be
     * overridden.
     *
     * @param daysLate number of days the payment is late
     */
    final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            // Skipped entirely, not charged a fee of Rs 0
            System.out.println(regNo + " - On time, no late fee");
            return;
        }

        System.out.println(regNo + " | Total Fee: Rs " + totalFee
                + " | Late Fee: Rs " + calculateLateFee(daysLate));
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        LateFeeAccount[] batch = new LateFeeAccount[regNos.length];

        // The whole batch is processed in a single pass
        for (int i = 0; i < batch.length; i++) {
            batch[i] = new LateFeeAccount(regNos[i], totalFees[i]);
            batch[i].printSummary(daysLate[i]);
        }
    }
}