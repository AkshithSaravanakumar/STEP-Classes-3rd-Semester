package com.cinehub.core;

/**
 * Each field carries the access level that matches who genuinely needs it:
 *
 * seatNumber  -> private   : only this object reads its own seat
 * screenId    -> default   : only classes in this package (the booking engine)
 * ticketPrice -> protected : also read by a subclass in another package
 * movieTitle  -> public    : displayed to anyone holding a reference
 */
public class MovieTicket {

    private int seatNumber;
    int screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(int seatNumber, int screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    /**
     * Private field reached only from inside this class.
     *
     * @return the seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Protected field read by this class; the subclass reads it through
     * its own type.
     *
     * @return the ticket price
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    public static void main(String[] args) {
        MovieTicket ticket = new MovieTicket(12, 3, 250.0, "Interstellar");

        System.out.println("Seat Number: " + ticket.getSeatNumber());
        System.out.println("Movie Title: " + ticket.movieTitle);
        System.out.println("Ticket Price: Rs " + ticket.getTicketPrice());
    }
}