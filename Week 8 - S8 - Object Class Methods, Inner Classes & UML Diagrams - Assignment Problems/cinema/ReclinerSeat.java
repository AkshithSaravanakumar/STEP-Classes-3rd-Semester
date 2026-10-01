package cinema;

/**
 * Recliner seat: ₹400.
 */
public class ReclinerSeat extends Seat {

    public static final double PRICE = 400.0;

    public ReclinerSeat(String seatId) {
        super(seatId);
    }

    @Override
    public String getCategoryName() {
        return "Recliner";
    }

    @Override
    public double getPrice() {
        return PRICE;
    }
}