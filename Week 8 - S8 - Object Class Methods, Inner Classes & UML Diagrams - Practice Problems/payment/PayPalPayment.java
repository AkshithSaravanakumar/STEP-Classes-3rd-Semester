package payment;

import java.util.HashSet;
import java.util.Set;

/**
 * PayPal payments decline when the account is suspended, and also reject a
 * second charge for an order that has already been paid through this method.
 */
public class PayPalPayment implements PaymentMethod {

    private static int referenceCounter;

    private final Set<String> alreadyCharged = new HashSet<>();
    private boolean accountSuspended;

    @Override
    public String getMethodName() {
        return "PayPal";
    }

    /**
     * Marks the linked PayPal account as unable to take payments, so the
     * failure path can be demonstrated without a live service.
     */
    public void suspendAccount() {
        this.accountSuspended = true;
    }

    /**
     * @param order the order being paid for
     * @return success for a valid charge, failure when suspended or duplicated
     */
    @Override
    public PaymentResult processPayment(Order order) {
        if (accountSuspended) {
            return PaymentResult.failure("PayPal account is suspended");
        }
        if (!alreadyCharged.add(order.getOrderId())) {
            return PaymentResult.failure("duplicate PayPal charge for " + order.getOrderId());
        }
        referenceCounter++;
        return PaymentResult.success("PP-" + String.format("%05d", referenceCounter));
    }
}