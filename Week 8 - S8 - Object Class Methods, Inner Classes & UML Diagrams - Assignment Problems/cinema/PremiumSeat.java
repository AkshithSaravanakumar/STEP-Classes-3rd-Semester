package cinema;

/**
 * Premium seat: ₹250.
 */
public class PremiumSeat extends Seat {

    public static final double PRICE = 250.0;

    public PremiumSeat(String seatId) {
        super(seatId);
    }

    @Override
    public String getCategoryName() {
        return "Premium";
    }

    @Override
    public double getPrice() {
        return PRICE;
    }
}