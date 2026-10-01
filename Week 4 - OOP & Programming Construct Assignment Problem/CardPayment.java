public class CardPayment extends Payment {

    private static final double PROCESSING_FEE_PERCENT = 2.0;

    /**
     * Adds the processing fee to the amount before printing the total charged.
     *
     * @param amount amount paid by card
     */
    public void payWithProcessingFee(double amount) {
        double total = amount + amount * (PROCESSING_FEE_PERCENT / 100.0);
        System.out.println("Charged (card, incl. fee): Rs " + total);
    }
}