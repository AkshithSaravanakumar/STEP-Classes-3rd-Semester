package hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Booking workflow. All logic is written against {@link Room}, so a new room
 * category requires no change here.
 *
 * UML: BookingSystem "1" *-- "0..*" Reservation
 */
public class BookingSystem {

    private final List<Reservation> reservations = new ArrayList<>();

    /**
     * @param room the room to check
     * @param startDay first night requested
     * @param endDay last night requested
     * @return true when no active reservation covers those nights
     */
    public boolean isAvailable(Room room, int startDay, int endDay) {
        for (Reservation reservation : reservations) {
            if (reservation.getStatus() == ReservationStatus.CANCELLED) {
                continue;
            }
            if (reservation.getRoom().getRoomNumber().equals(room.getRoomNumber())
                    && reservation.overlaps(startDay, endDay)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param customer the guest
     * @param room the room to book
     * @param startDay first night
     * @param endDay last night
     * @param month the month label used when printing the period
     * @param cancellationDeadline last day a free cancellation is allowed
     * @return the confirmed reservation, or null when the room is unavailable
     */
    public Reservation reserve(Customer customer, Room room, int startDay, int endDay, String month,
                               int cancellationDeadline) {
        if (!isAvailable(room, startDay, endDay)) {
            System.out.println(room + " is not available for the requested period.");
            return null;
        }
        Reservation reservation = new Reservation(room, customer, startDay, endDay, month, cancellationDeadline);
        reservations.add(reservation);
        System.out.println("Reservation confirmed for " + reservation + ". Price: $" + reservation.getPrice() + ".");
        return reservation;
    }

    /**
     * @param reservation the reservation to cancel
     * @param today the current day number
     * @return true when the cancellation was allowed and applied
     */
    public boolean cancel(Reservation reservation, int today) {
        if (!reservation.cancel(today)) {
            return false;
        }
        System.out.println("Reservation for " + reservation.getCustomer() + ", " + reservation.getRoom()
                + " (" + reservation.getMonth() + " " + reservation.getStartDay()
                + "-" + reservation.getEndDay() + ") cancelled successfully.");
        return true;
    }

    /**
     * @return every reservation this system has recorded
     */
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();

        StandardRoom room101 = new StandardRoom("101");
        DeluxeRoom room201 = new DeluxeRoom("201");

        Customer customerA = new Customer("A", "Customer A");
        Customer customerB = new Customer("B", "Customer B");
        Customer customerC = new Customer("C", "Customer C");

        if (system.isAvailable(room101, 1, 5)) {
            System.out.println(room101 + " is available from Jan 1 to Jan 5.");
        } else {
            System.out.println(room101 + " is not available from Jan 1 to Jan 5.");
        }
        Reservation aReservation = system.reserve(customerA, room101, 1, 5, "Jan", 1);
        system.reserve(customerB, room101, 3, 7, "Jan", 3);
        if (aReservation != null) {
            system.cancel(aReservation, 1);
        }
        system.reserve(customerC, room201, 10, 12, "Feb", 10);
    }
}