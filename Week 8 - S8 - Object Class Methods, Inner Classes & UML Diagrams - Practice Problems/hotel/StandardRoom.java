package hotel;

/**
 * Standard room: a flat nightly rate.
 */
public class StandardRoom extends Room {

    public static final double NIGHTLY_RATE = 90.0;

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public String getCategory() {
        return "Standard Room";
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * NIGHTLY_RATE;
    }
}