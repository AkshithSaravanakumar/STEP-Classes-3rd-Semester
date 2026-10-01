package cinema;

/**
 * A seat defines its own category and price, so the booking logic never checks
 * which row a seat is in.
 *
 * UML:
 * <pre>
 * Seat (abstract)
 *  - seatId : String
 *  + getSeatId() : String
 *  + getCategoryName() : String {abstract}
 *  + getPrice() : double {abstract}
 *
 * Seat &lt;|-- RegularSeat
 * Seat &lt;|-- PremiumSeat
 * Seat &lt;|-- ReclinerSeat
 * </pre>
 */
public abstract class Seat {

    private final String seatId;

    /**
     * @param seatId the seat's id, e.g. A1
     */
    protected Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    /**
     * @return the category name used in printed messages
     */
    public abstract String getCategoryName();

    /**
     * @return the price of this seat, in rupees
     */
    public abstract double getPrice();

    @Override
    public String toString() {
        return seatId;
    }
}