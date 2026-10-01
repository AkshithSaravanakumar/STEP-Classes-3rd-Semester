package hotel;

/**
 * Deluxe room: a higher nightly rate because of the added amenities.
 */
public class DeluxeRoom extends Room {

    public static final double NIGHTLY_RATE = 160.0;

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public String getCategory() {
        return "Deluxe Room";
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * NIGHTLY_RATE;
    }
}