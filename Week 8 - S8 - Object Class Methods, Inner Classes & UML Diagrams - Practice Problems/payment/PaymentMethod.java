package payment;

/**
 * The payment abstraction the order-processing logic depends on. Adding a new
 * method means implementing this one interface; no existing code changes.
 *
 * UML: PaymentMethod (interface)
 *        + getMethodName() : String
 *        + processPayment(order : Order) : PaymentResult
 *
 * PaymentMethod <|.. CreditCardPayment
 * PaymentMethod <|.. PayPalPayment
 * PaymentMethod <|.. BankTransferPayment
 */
public interface PaymentMethod {

    /**
     * @return the method's display name
     */
    String getMethodName();

    /**
     * @param order the order being paid for
     * @return the transaction outcome, successful or not
     */
    PaymentResult processPayment(Order order);
}