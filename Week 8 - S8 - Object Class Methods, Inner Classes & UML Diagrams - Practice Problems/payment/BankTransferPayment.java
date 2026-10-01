package payment;

/**
 * Bank transfers succeed once the pending settlement flag is cleared, so the
 * caller can demonstrate both outcomes without a live banking rail.
 */
public class BankTransferPayment implements PaymentMethod {

    private static int referenceCounter;

    private boolean pendingSettlement = true;

    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }

    /**
     * @param order the order being paid for
     * @return failure while settlement is still pending, then success
     */
    @Override
    public PaymentResult processPayment(Order order) {
        if (pendingSettlement) {
            return PaymentResult.failure("transfer not yet settled");
        }
        referenceCounter++;
        return PaymentResult.success("BT-" + String.format("%05d", referenceCounter));
    }

    /**
     * Marks the transfer as settled so the next attempt can succeed.
     */
    public void confirmSettlement() {
        this.pendingSettlement = false;
    }
}