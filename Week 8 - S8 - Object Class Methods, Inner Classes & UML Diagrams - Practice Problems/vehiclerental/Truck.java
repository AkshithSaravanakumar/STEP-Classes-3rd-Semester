package vehiclerental;

/**
 * Truck pricing: flat daily rate plus a per-day load capacity surcharge.
 */
public class Truck extends Vehicle {

    public static final double DAILY_RATE = 120.0;
    public static final double LOAD_SURCHARGE_PER_DAY = 15.0;

    public Truck(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public double calculateCharge(int days) {
        return days * (DAILY_RATE + LOAD_SURCHARGE_PER_DAY);
    }
}