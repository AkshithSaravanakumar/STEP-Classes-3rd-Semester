package payment;

/**
 * A purchasable item.
 *
 * UML: Order "1" *-- "1..*" Product (composition: an order's lines die with it)
 */
public class Product {

    private final String productId;
    private final String name;
    private final double unitPrice;

    /**
     * @param productId the product's unique id
     * @param name the product's display name
     * @param unitPrice the price for a single unit
     */
    public Product(String productId, String name, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return name + " (" + productId + ") at $" + unitPrice;
    }
}