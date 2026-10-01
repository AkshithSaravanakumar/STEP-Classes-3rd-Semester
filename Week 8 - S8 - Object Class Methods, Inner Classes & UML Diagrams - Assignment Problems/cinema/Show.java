package cinema;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A show in the auditorium. The show owns its own seat inventory, which is what
 * makes a seat "already booked for this show" rather than booked in general: a
 * different show has its own {@code Show} object and its own map.
 *
 * UML:
 * <pre>
 * Show
 *  - showName : String
 *  - startTime : LocalTime
 *  - seats : Map&lt;String, Seat&gt;
 *  - bookedSeats : Map&lt;String, Booking&gt;
 *  + addSeat(seat) : void
 *  + isSeatBooked(seatId) : boolean
 *  + isShowStarted(now) : boolean
 *
 * Show "1" *-- "0..*" Seat (composition)
 * Show "1" -- "0..*" Booking
 * </pre>
 */
public class Show {

    private final String showName;
    private final LocalTime startTime;

    private final Map<String, Seat> seats = new LinkedHashMap<>();
    private final Map<String, Booking> bookedSeats = new LinkedHashMap<>();

    /**
     * @param showName the show's name
     * @param startTime when the show starts
     */
    public Show(String showName, LocalTime startTime) {
        this.showName = showName;
        this.startTime = startTime;
    }

    public String getShowName() {
        return showName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param seat a seat in this auditorium
     */
    public void addSeat(Seat seat) {
        seats.put(seat.getSeatId(), seat);
    }

    /**
     * @param seatId the seat's id
     * @return the seat, or null when it is not in this show
     */
    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }

    /**
     * @return the seats currently held by a booking, in booking order
     */
    public Set<String> getBookedSeatIds() {
        return Collections.unmodifiableSet(bookedSeats.keySet());
    }

    /**
     * @param seatId the seat to check
     * @return true when this seat is taken for this show
     */
    public boolean isSeatBooked(String seatId) {
        return bookedSeats.containsKey(seatId);
    }

    /**
     * @param now the current time
     * @return true once the show has started
     */
    public boolean isShowStarted(LocalTime now) {
        return !now.isBefore(startTime);
    }

    /**
     * Marks seats as taken. Called by {@link TicketCounter} only after a booking
     * has been fully accepted, so a failed booking never holds seats.
     *
     * @param booking the booking holding the seats
     */
    void holdSeats(Booking booking) {
        for (Seat seat : booking.getSeats()) {
            bookedSeats.put(seat.getSeatId(), booking);
        }
    }

    /**
     * Releases a cancelled booking's seats so other customers can take them.
     *
     * @param booking the cancelled booking
     */
    void releaseSeats(Booking booking) {
        for (Seat seat : booking.getSeats()) {
            bookedSeats.remove(seat.getSeatId());
        }
    }
}