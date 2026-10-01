package payment;

/**
 * Outcome of one payment transaction. A failed transaction is a normal
 * result, not an exception, so callers must inspect it before marking an order
 * paid.
 *
 * UML: PaymentResult
 *  - success : boolean
 *  - reference : String
 *  - message : String
 *  + isSuccess() : boolean
 *  + getReference() : String
 *  + getMessage() : String
 */
public class PaymentResult {

    private final boolean success;
    private final String reference;
    private final String message;

    private PaymentResult(boolean success, String reference, String message) {
        this.success = success;
        this.reference = reference;
        this.message = message;
    }

    /**
     * @param reference the transaction reference
     * @return a successful result
     */
    public static PaymentResult success(String reference) {
        return new PaymentResult(true, reference, "Payment successful.");
    }

    /**
     * @param reason why the transaction was declined
     * @return a failed result
     */
    public static PaymentResult failure(String reason) {
        return new PaymentResult(false, null, "Payment failed: " + reason);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getReference() {
        return reference;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message + (reference == null ? "" : " Ref: " + reference);
    }
}