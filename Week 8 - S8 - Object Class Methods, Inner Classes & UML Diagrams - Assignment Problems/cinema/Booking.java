package cinema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * One customer's seats for one show, up to the six-seat limit. The total is the
 * sum of the seats' own prices.
 *
 * UML:
 * <pre>
 * Booking
 *  - customer : Customer
 *  - show : Show
 *  - seats : List&lt;Seat&gt;
 *  - total : double
 *  - status : BookingStatus
 *  + getSeats() : List&lt;Seat&gt;
 *  + getTotal() : double
 *  + getSeatIds() : String
 * </pre>
 *
 * Booking "0..1" -- "1" Show (aggregation)
 * Booking "*" -- "1" Customer
 * Booking "1" *-- "1..6" Seat (composition while the booking is active)
 */
public class Booking {

    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;

    private BookingStatus status = BookingStatus.CONFIRMED;
    private final double total;

    /**
     * @param customer who is booking
     * @param show which show
     * @param seats the seats chosen
     */
    public Booking(Customer customer, Show show, List<Seat> seats) {
        if (seats.isEmpty()) {
            throw new IllegalArgumentException("A booking needs at least one seat.");
        }
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);

        double sum = 0;
        for (Seat seat : this.seats) {
            sum += seat.getPrice();
        }
        this.total = sum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public BookingStatus getStatus() {
        return status;
    }

    /**
     * @return the seats in this booking
     */
    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    /**
     * @return the booking total, the sum of the seats' own prices
     */
    public double getTotal() {
        return total;
    }

    /**
     * @return true when this booking can still be cancelled
     */
    public boolean isCancellable() {
        return status == BookingStatus.CONFIRMED;
    }

    void markCancelled() {
        this.status = BookingStatus.CANCELLED;
    }

    /**
     * @return the seat ids joined with ", " for printed messages
     */
    public String getSeatIds() {
        StringBuilder ids = new StringBuilder();
        for (Seat seat : seats) {
            if (ids.length() > 0) {
                ids.append(", ");
            }
            ids.append(seat.getSeatId());
        }
        return ids.toString();
    }
}