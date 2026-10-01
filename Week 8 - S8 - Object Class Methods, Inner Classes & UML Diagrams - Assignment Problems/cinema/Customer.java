package cinema;

/**
 * Someone who books seats.
 *
 * UML: Customer "1" -- "0..*" Booking
 */
public class Customer {

    private final String customerId;
    private final String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}