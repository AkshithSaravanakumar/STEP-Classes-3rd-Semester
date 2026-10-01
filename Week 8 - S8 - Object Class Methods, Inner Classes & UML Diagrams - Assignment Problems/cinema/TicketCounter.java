package cinema;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Ticket booking. It talks only to {@link Seat} and {@link Show}, so a new seat
 * category needs no change here.
 */
public class TicketCounter {

    public static final int MAX_SEATS_PER_BOOKING = 6;

    private final List<Booking> bookings = new ArrayList<>();

    /**
     * @param seatId the seat to check
     * @param show the show to check it for
     * @return true when the seat exists and is free for that show
     */
    public boolean isSeatAvailable(Show show, String seatId) {
        Seat seat = show.getSeat(seatId);
        return seat != null && !show.isSeatBooked(seatId);
    }

    /**
     * @param show the show the seats belong to
     * @param seatIds the seat ids requested
     * @return the seats, or null when any of them is unavailable
     */
    private List<Seat> resolveSeats(Show show, List<String> seatIds) {
        List<Seat> seats = new ArrayList<>();
        Set<String> requested = new LinkedHashSet<>();
        for (String seatId : seatIds) {
            if (!requested.add(seatId)) {
                System.out.println("Seat " + seatId + " is listed twice in the same request.");
                return null;
            }
            Seat seat = show.getSeat(seatId);
            if (seat == null) {
                System.out.println("Seat " + seatId + " does not exist for this show.");
                return null;
            }
            if (show.isSeatBooked(seatId)) {
                System.out.println("Seat " + seatId + " is already booked for this show.");
                return null;
            }
            seats.add(seat);
        }
        return seats;
    }

    /**
     * @param customer who is booking
     * @param show which show
     * @param seatIds the seat ids requested
     * @return the confirmed booking, or null when the request is invalid
     */
    public Booking book(Customer customer, Show show, List<String> seatIds) {
        if (seatIds.isEmpty()) {
            System.out.println("Cannot create booking: at least one seat is required.");
            return null;
        }
        if (seatIds.size() > MAX_SEATS_PER_BOOKING) {
            System.out.println("Cannot create booking: maximum " + MAX_SEATS_PER_BOOKING
                    + " seats per booking.");
            return null;
        }
        List<Seat> seats = resolveSeats(show, seatIds);
        if (seats == null) {
            return null;
        }

        Booking booking = new Booking(customer, show, seats);
        show.holdSeats(booking);
        bookings.add(booking);
        System.out.println("Booking confirmed for " + customer.getName() + ": " + booking.getSeatIds() + ".");
        System.out.println("Total: ₹" + String.format("%.2f", booking.getTotal()) + ".");
        return booking;
    }

    /**
     * Cancels before the show starts and releases the seats. Releasing is what
     * lets another customer take them, so it happens on the same path as the
     * cancellation itself.
     *
     * @param booking the booking to cancel
     * @param now the current time
     * @return true when the cancellation was allowed and applied
     */
    public boolean cancel(Booking booking, LocalTime now) {
        if (!booking.isCancellable()) {
            System.out.println("Cannot cancel: booking is already " + booking.getStatus() + ".");
            return false;
        }
        if (booking.getShow().isShowStarted(now)) {
            System.out.println("Cannot cancel: the show has already started.");
            return false;
        }
        booking.markCancelled();
        booking.getShow().releaseSeats(booking);
        System.out.println(booking.getCustomer().getName() + "'s booking cancelled.");
        System.out.println("Seats " + booking.getSeatIds() + " released.");
        return true;
    }

    /**
     * @return every booking this counter has made
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        Show show = new Show("7 PM Show", LocalTime.of(19, 0));
        show.addSeat(new RegularSeat("A1"));
        show.addSeat(new RegularSeat("A2"));
        show.addSeat(new PremiumSeat("F5"));
        show.addSeat(new ReclinerSeat("R1"));

        Customer asha = new Customer("C1", "Asha");
        Customer ravi = new Customer("C2", "Ravi");
        Customer neha = new Customer("C3", "Neha");

        Booking ashaBooking = counter.book(asha, show, Arrays.asList("A1", "A2", "F5"));
        counter.book(ravi, show, Arrays.asList("A2"));
        counter.book(ravi, show, Arrays.asList("R1"));

        counter.cancel(ashaBooking, LocalTime.of(18, 0));

        counter.book(neha, show, Arrays.asList("A2"));
    }
}