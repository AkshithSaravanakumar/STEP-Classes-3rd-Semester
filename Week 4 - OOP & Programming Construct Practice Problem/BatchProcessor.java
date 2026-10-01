public class BatchProcessor {

    private int hostelCount;
    private int dayScholarCount;

    /**
     * Dispatches the payment for one account using an instanceof check, then
     * tallies it under the matching counter.
     *
     * @param account the account to process
     * @param amount  payment amount
     */
    void processPayment(FeeAccount account, double amount) {
        // HostelFeeAccount is checked first because it is the subclass
        if (account instanceof HostelFeeAccount) {
            HostelFeeAccount hostel = (HostelFeeAccount) account;
            hostel.pay(amount);
            hostelCount++;
        } else if (account instanceof FeeAccount) {
            FeeAccount dayScholar = (FeeAccount) account;
            dayScholar.pay(amount);
            dayScholarCount++;
        } else {
            System.out.println("Unknown account type, skipped");
        }
    }

    /**
     * Prints both counters once, after the full batch has been processed.
     */
    void printCounts() {
        System.out.println("Hostel accounts processed: " + hostelCount
                + " | Day-scholar accounts processed: " + dayScholarCount);
    }

    public static void main(String[] args) {
        BatchProcessor processor = new BatchProcessor();

        FeeAccount[] batch = {
                new HostelFeeAccount("HB001", 60000, "A-Block"),
                new HostelFeeAccount("HB002", 60000, "B-Block"),
                new FeeAccount("DA001", 60000),
                new FeeAccount("DA002", 60000)
        };

        double amount = 60000;

        // The whole batch is processed in a single pass
        for (int i = 0; i < batch.length; i++) {
            processor.processPayment(batch[i], amount);
        }

        processor.printCounts();
    }
}