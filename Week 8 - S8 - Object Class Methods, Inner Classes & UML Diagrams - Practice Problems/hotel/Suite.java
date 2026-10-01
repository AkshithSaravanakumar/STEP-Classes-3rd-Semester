package hotel;

/**
 * Suite: size-based pricing with a one-time cleaning fee.
 */
public class Suite extends Room {

    public static final double NIGHTLY_RATE = 320.0;
    public static final double CLEANING_FEE = 120.0;

    public Suite(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public String getCategory() {
        return "Suite";
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * NIGHTLY_RATE + CLEANING_FEE;
    }
}