package cinema;

/**
 * Regular seat: ₹150.
 */
public class RegularSeat extends Seat {

    public static final double PRICE = 150.0;

    public RegularSeat(String seatId) {
        super(seatId);
    }

    @Override
    public String getCategoryName() {
        return "Regular";
    }

    @Override
    public double getPrice() {
        return PRICE;
    }
}