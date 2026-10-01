public class CanteenPos {

    private static final double PROCESSING_FEE_PERCENT = 2.0;

    private double lastCharged;

    public double getLastCharged() {
        return lastCharged;
    }

    /**
     * Dispatches one transaction using an instanceof type check instead of a
     * number comparison, then records the amount actually charged.
     *
     * @param payment the payment reference to process
     * @param amount  the base transaction amount
     */
    void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment card = (CardPayment) payment;
            lastCharged = amount + amount * (PROCESSING_FEE_PERCENT / 100.0);
            card.payWithProcessingFee(amount);
        } else {
            lastCharged = amount;
            payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        CanteenPos pos = new CanteenPos();

        // Card and cash references stored together in one array
        Payment[] payments = {
                new CardPayment(),
                new Payment(),
                new CardPayment(),
                new Payment(),
                new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};

        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {
            pos.processTransaction(payments[i], amounts[i]);
            totalCollected += pos.getLastCharged();
        }

        System.out.println("Total Collected: Rs " + totalCollected);
    }
}