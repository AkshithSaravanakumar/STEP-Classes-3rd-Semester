package payment;

/**
 * Order status. Only a successful payment moves an order to PAID.
 */
public enum OrderStatus {

    PENDING,
    PAID;

    /**
     * @param successful whether the payment transaction succeeded
     * @return PAID on success, otherwise the unchanged PENDING status
     */
    public OrderStatus afterPayment(boolean successful) {
        return successful ? PAID : PENDING;
    }
}