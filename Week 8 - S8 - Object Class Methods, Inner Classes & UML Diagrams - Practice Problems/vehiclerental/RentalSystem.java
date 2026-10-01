package vehiclerental;

/**
 * Rental processing. All rules live here against the {@link Vehicle} base type,
 * so a new vehicle category requires no change to this class.
 */
public class RentalSystem {

    private RentalSystem() {
    }

    /**
     * Inner class holding the currently active rental per vehicle. Keeping this
     * map here means the "no double booking" rule and the rentals themselves are
     * owned by one object rather than scattered across the system.
     *
     * UML: RentalSystem o-- RentalRegistry (inner class)
     */
    public static class RentalRegistry {

        private final java.util.Map<String, Rental> activeRentals = new java.util.LinkedHashMap<>();

        /**
         * @param plateNumber the vehicle's registration plate
         * @return the active rental for that vehicle, or null when it is free
         */
        public Rental findActiveRental(String plateNumber) {
            return activeRentals.get(plateNumber);
        }

        /**
         * @param rental the rental to register as active
         */
        public void register(Rental rental) {
            activeRentals.put(rental.getVehicle().getPlateNumber(), rental);
        }

        /**
         * @param plateNumber the vehicle whose rental has ended
         */
        public void release(String plateNumber) {
            activeRentals.remove(plateNumber);
        }
    }

    private static final RentalRegistry REGISTRY = new RentalRegistry();

    /**
     * @param customer the renter
     * @param vehicle the requested vehicle
     * @param days the rental duration
     * @return the new rental, or null when the vehicle is already rented out
     */
    public static Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        Rental active = REGISTRY.findActiveRental(vehicle.getPlateNumber());
        if (active != null || !vehicle.isAvailable()) {
            System.out.println(vehicle + " is currently unavailable.");
            return null;
        }
        vehicle.setAvailable(false);
        Rental rental = new Rental(vehicle, customer, days);
        REGISTRY.register(rental);
        System.out.println(vehicle + " rented successfully by " + customer.getName() + ".");
        System.out.println("Rental charge: $" + rental.getTotalCharge());
        return rental;
    }

    /**
     * @param rental the rental being closed
     */
    public static void returnVehicle(Rental rental) {
        if (rental == null) {
            return;
        }
        Rental active = REGISTRY.findActiveRental(rental.getVehicle().getPlateNumber());
        if (active != rental) {
            System.out.println(rental.getVehicle() + " has no active rental to return.");
            return;
        }
        REGISTRY.release(rental.getVehicle().getPlateNumber());
        rental.getVehicle().setAvailable(true);
        System.out.println(rental.getVehicle() + " returned by " + rental.getCustomer().getName() + ".");
    }

    public static void main(String[] args) {
        Sedan sedanA = new Sedan("A");
        SUV suvB = new SUV("B");

        Customer customer1 = new Customer("C1", "Customer 1");
        Customer customer2 = new Customer("C2", "Customer 2");
        Customer customer3 = new Customer("C3", "Customer 3");

        Rental first = rentVehicle(customer1, sedanA, 3);
        rentVehicle(customer2, sedanA, 2);
        returnVehicle(first);
        rentVehicle(customer3, suvB, 5);
    }
}