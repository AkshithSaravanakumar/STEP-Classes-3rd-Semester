package hotel;

/**
 * A booking of one room for one customer over a date range. The price is
 * computed once from the room's own pricing rule and then frozen.
 *
 * UML:
 * <pre>
 * Reservation
 *  - room : Room
 *  - customer : Customer
 *  - startDay : int
 *  - endDay : int
 *  - price : double
 *  - cancellationDeadline : int
 *  - status : ReservationStatus
 *  + getNights() : int
 *  + overlaps(startDay, endDay) : boolean
 *  + cancel(today : int) : boolean
 * </pre>
 *
 * Reservation "0..1" -- "1" Room (aggregation)
 * Reservation "*" -- "1" Customer
 */
public class Reservation {

    private final Room room;
    private final Customer customer;
    private final int startDay;
    private final int endDay;
    private final String month;
    private final double price;
    private final int cancellationDeadline;

    private ReservationStatus status = ReservationStatus.CONFIRMED;

    /**
     * @param room the booked room
     * @param customer the guest
     * @param startDay first night, as a day number
     * @param endDay last night, as a day number
     * @param month the month label used when printing the period
     * @param cancellationDeadline last day on which a free cancellation is still
     *        allowed; must not be later than the first night
     */
    public Reservation(Room room, Customer customer, int startDay, int endDay, String month,
                       int cancellationDeadline) {
        if (endDay < startDay) {
            throw new IllegalArgumentException("End day cannot be before start day.");
        }
        if (cancellationDeadline > startDay) {
            throw new IllegalArgumentException("Cancellation deadline cannot be after the first night.");
        }
        this.room = room;
        this.customer = customer;
        this.startDay = startDay;
        this.endDay = endDay;
        this.month = month;
        this.cancellationDeadline = cancellationDeadline;
        this.price = room.calculatePrice(getNights());
    }

    public String getMonth() {
        return month;
    }

    public int getCancellationDeadline() {
        return cancellationDeadline;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public double getPrice() {
        return price;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * @return the number of nights, counting the start night
     */
    public int getNights() {
        return endDay - startDay + 1;
    }

    /**
     * Half-open interval overlap: two ranges clash only when they actually
     * share a night, so back-to-back bookings do not conflict.
     *
     * @param start the other range's first night
     * @param end the other range's last night
     * @return true when this reservation covers any of those nights
     */
    public boolean overlaps(int start, int end) {
        return start <= endDay && end >= startDay;
    }

    /**
     * @param today the current day number
     * @return true when the cancellation is allowed and applied
     */
    public boolean cancel(int today) {
        if (!status.isCancellable()) {
            System.out.println("Reservation for " + room + " cannot be cancelled; status is " + status + ".");
            return false;
        }
        if (today > cancellationDeadline) {
            System.out.println("Reservation for " + room + " cannot be cancelled after day "
                    + cancellationDeadline + ".");
            return false;
        }
        this.status = ReservationStatus.CANCELLED;
        return true;
    }

    @Override
    public String toString() {
        return customer + ", " + room + " (" + month + " " + startDay + "-" + endDay + ")";
    }
}