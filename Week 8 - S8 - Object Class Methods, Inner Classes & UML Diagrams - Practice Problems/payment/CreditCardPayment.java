package payment;

/**
 * Credit card payments always authorise in this simulation.
 */
public class CreditCardPayment implements PaymentMethod {

    private static int referenceCounter;

    /**
     * Inner class issuing the transaction reference, so reference generation
     * stays private to the card rail.
     */
    private static class ReferenceIssuer {

        private static synchronized String next() {
            referenceCounter++;
            return "CC-" + String.format("%05d", referenceCounter);
        }
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }

    @Override
    public PaymentResult processPayment(Order order) {
        return PaymentResult.success(ReferenceIssuer.next());
    }
}