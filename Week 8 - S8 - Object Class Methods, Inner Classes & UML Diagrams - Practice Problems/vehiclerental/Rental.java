package vehiclerental;

/**
 * One rental agreement, tracking the rented vehicle, the customer, the rental
 * period and the charge.
 *
 * UML: Rental "0..1" -- "1" Vehicle (aggregation: the vehicle outlives the rental)
 *      Rental "*" -- "1" Customer
 *      Rental "0..1" -- "1" RentalStatus
 */
public class Rental {

    private final Vehicle vehicle;
    private final Customer customer;
    private final int days;
    private final double charge;

    /**
     * @param vehicle the rented vehicle
     * @param customer the renter
     * @param days the rental duration in days
     */
    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.charge = vehicle.calculateCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getCharge() {
        return charge;
    }

    /**
     * The charge is computed once in the constructor from the vehicle's own
     * pricing rule and never recalculated, so the agreement is a stable record.
     *
     * @return the amount charged for this rental
     */
    public double getTotalCharge() {
        return charge;
    }
}