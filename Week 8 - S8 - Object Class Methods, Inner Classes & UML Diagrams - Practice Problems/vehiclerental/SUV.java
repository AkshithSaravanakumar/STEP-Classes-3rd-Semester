package vehiclerental;

/**
 * SUV pricing: higher daily rate plus a one-time insurance premium.
 */
public class SUV extends Vehicle {

    public static final double DAILY_RATE = 80.0;
    public static final double INSURANCE_PREMIUM = 40.0;

    public SUV(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public double calculateCharge(int days) {
        return days * DAILY_RATE + INSURANCE_PREMIUM;
    }
}