package vehiclerental;

/**
 * Sedan pricing: a flat base rate per day with no premium.
 */
public class Sedan extends Vehicle {

    public static final double DAILY_RATE = 50.0;

    public Sedan(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public double calculateCharge(int days) {
        return days * DAILY_RATE;
    }
}