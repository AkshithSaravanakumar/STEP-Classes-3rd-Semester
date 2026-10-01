package payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A customer order holding one or more line items. An order starts PENDING and
 * only becomes PAID through a successful payment.
 *
 * UML:
 * <pre>
 * Order
 *  - orderId : String
 *  - customer : Customer
 *  - items : List&lt;OrderItem&gt;
 *  - status : OrderStatus
 *  + addItem(product, quantity) : void
 *  + isEmpty() : boolean
 *  + getTotal() : double
 *  + markPaid() : void
 * </pre>
 *
 * Order "1" *-- "0..*" OrderItem (composition)
 */
public class Order {

    /**
     * Inner class for one order line, so quantity and the product it refers to
     * are always recorded together.
     *
     * UML: Order o-- OrderItem (inner class)
     */
    public static class OrderItem {

        private final Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive.");
            }
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getLineTotal() {
            return product.getUnitPrice() * quantity;
        }

        @Override
        public String toString() {
            return quantity + " x " + product;
        }
    }

    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();

    private OrderStatus status = OrderStatus.PENDING;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @return the order's line items, unmodifiable from the caller's side
     */
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * @param product the product to add
     * @param quantity how many units, must be positive
     */
    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    /**
     * @return true when the order has no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * @return the order total across all line items
     */
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }

    /**
     * @return true when the order is already paid
     */
    public boolean isPaid() {
        return status == OrderStatus.PAID;
    }

    /**
     * Flips the order to PAID. The caller is responsible for only invoking this
     * after a successful transaction.
     */
    public void markPaid() {
        this.status = OrderStatus.PAID;
    }

    @Override
    public String toString() {
        return "Order " + orderId;
    }
}