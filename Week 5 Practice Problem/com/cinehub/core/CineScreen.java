package com.cinehub.core;

/**
 * Seat counts only ever move through validated methods. seatsAvailable can
 * never be set from outside, and the one relationship that must always hold is
 *
 * 0 <= seatsAvailable <= seatsTotal
 *
 * which is checked before every change.
 */
public class CineScreen {

    private int seatsTotal;
    private int seatsAvailable;

    /**
     * Rejects a nonsensical seat count instead of letting the object exist in
     * an invalid state.
     *
     * @param seatsTotal number of seats in the screen
     */
    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            System.out.println("construction rejected");
            this.seatsTotal = 0;
            this.seatsAvailable = 0;
            return;
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    /**
     * Books one seat, silently rejecting the transition if no seat is left.
     */
    public void bookSeat() {
        if (seatsAvailable - 1 < 0 || seatsAvailable - 1 > seatsTotal) {
            return;
        }

        seatsAvailable--;
    }

    /**
     * Cancels one booking, silently rejecting the transition if the screen is
     * already full.
     */
    public void cancelBooking() {
        if (seatsAvailable + 1 > seatsTotal || seatsAvailable + 1 < 0) {
            return;
        }

        seatsAvailable++;
    }

    /**
     * Read-only access to the available seat count.
     *
     * @return seats still available
     */
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }

    public static void main(String[] args) {
        new CineScreen(0);
        new CineScreen(-4);

        CineScreen c = new CineScreen(2);

        c.bookSeat();
        c.bookSeat();
        c.bookSeat();
        System.out.println(c.getSeatsAvailable());

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();
        System.out.println(c.getSeatsAvailable());
    }
}