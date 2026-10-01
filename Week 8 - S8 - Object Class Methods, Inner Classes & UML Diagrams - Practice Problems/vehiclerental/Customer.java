package vehiclerental;

/**
 * A person who rents vehicles. Each customer may hold many rentals over time.
 *
 * UML: Customer "1" -- "0..*" Rental
 */
public class Customer {

    private final String customerId;
    private final String name;

    /**
     * @param customerId the customer's unique id
     * @param name the customer's display name
     */
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
        return name + " (" + customerId + ")";
    }
}