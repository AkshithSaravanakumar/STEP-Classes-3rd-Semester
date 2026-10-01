package vehiclerental;

/**
 * Abstract base for every vehicle category. Common state and behaviour live
 * here; each category supplies only its own pricing rule through
 * {@link #calculateCharge(int)}.
 *
 * UML:
 * <pre>
 * Vehicle (abstract)
 *  - plateNumber : String
 *  - available : boolean
 *  + getPlateNumber() : String
 *  + isAvailable() : boolean
 *  + calculateCharge(days : int) : double {abstract}
 *
 * Vehicle <|-- Sedan
 * Vehicle <|-- SUV
 * Vehicle <|-- Truck
 * </pre>
 *
 * Adding a new category means adding one new subclass and nothing else: the
 * rental processing logic in {@link RentalSystem} never changes.
 */
public abstract class Vehicle {

    private final String plateNumber;
    private boolean available;

    /**
     * @param plateNumber the vehicle's registration plate
     */
    protected Vehicle(String plateNumber) {
        this.plateNumber = plateNumber;
        this.available = true;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    /**
     * Availability is only ever changed through these two guarded operations,
     * never by direct field assignment from outside.
     *
     * @param available the new availability state
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @param days how many days the vehicle is rented for
     * @return the total rental charge for this category
     */
    public abstract double calculateCharge(int days);

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + plateNumber;
    }
}