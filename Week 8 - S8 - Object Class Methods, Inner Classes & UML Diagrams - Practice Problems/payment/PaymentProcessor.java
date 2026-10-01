package payment;

/**
 * Payment initiation. It depends only on the {@link PaymentMethod} interface,
 * so new methods plug in without any change here.
 */
public class PaymentProcessor {

    private PaymentProcessor() {
    }

    /**
     * @param order the order to pay
     * @param method the chosen payment method
     * @return the transaction outcome
     */
    public static PaymentResult pay(Order order, PaymentMethod method) {
        if (order.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return PaymentResult.failure("order has no items");
        }
        if (order.isPaid()) {
            System.out.println(order + " is already paid.");
            return PaymentResult.success(null);
        }

        System.out.println("Payment initiated via " + method.getMethodName()
                + " for " + order + " ($" + order.getTotal() + ").");

        PaymentResult result = method.processPayment(order);

        if (result.isSuccess()) {
            order.markPaid();
            System.out.println("Payment for " + order + " successful.");
        } else {
            System.out.println("Payment for " + order + " failed.");
        }
        System.out.println("Order status: " + order.getStatus() + ".");
        return result;
    }

    public static void main(String[] args) {
        Product productA = new Product("PA", "Product A", 25.0);
        Product productB = new Product("PB", "Product B", 60.0);
        Product productC = new Product("PC", "Product C", 40.0);

        Customer customerX = new Customer("X", "Customer X");
        Customer customerY = new Customer("Y", "Customer Y");
        Customer customerZ = new Customer("Z", "Customer Z");

        Order orderX = new Order("X", customerX);
        orderX.addItem(productA, 2);
        orderX.addItem(productB, 1);
        System.out.println("Order created for " + customerX + " (" + orderX + ").");
        pay(orderX, new CreditCardPayment());

        Order orderY = new Order("Y", customerY);
        System.out.println("Order created for " + customerY + " (" + orderY + ").");
        pay(orderY, new CreditCardPayment());

        Order orderZ = new Order("Z", customerZ);
        orderZ.addItem(productC, 1);
        System.out.println("Order created for " + customerZ + " (" + orderZ + ").");
        PayPalPayment paypal = new PayPalPayment();
        paypal.suspendAccount();
        pay(orderZ, paypal);
    }
}