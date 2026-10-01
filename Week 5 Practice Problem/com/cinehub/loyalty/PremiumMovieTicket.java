package com.cinehub.loyalty;

import com.cinehub.core.MovieTicket;

/**
 * Lives in a different package from MovieTicket, so the only way it can reach
 * the protected ticketPrice is by inheriting from it.
 */
public class PremiumMovieTicket extends MovieTicket {

    private double loyaltyPoints;

    public PremiumMovieTicket(int seatNumber, int screenId, double ticketPrice,
                              String movieTitle, double loyaltyPoints) {
        super(seatNumber, screenId, ticketPrice, movieTitle);
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * OWN_TYPE case: the reference below is a PremiumMovieTicket, so reading the
     * inherited protected field compiles.
     *
     * @return the ticket price read through this class's own type
     */
    public double readPriceThroughOwnType() {
        return ticketPrice;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * PARENT_TYPE case: the same field reached through a MovieTicket-typed
     * reference is denied, because Java goes by the reference's declared type.
     * This is why the line below does not compile:
     *
     * double price = parentTypedRef.ticketPrice;
     *
     * @param parentTypedRef a reference declared as MovieTicket
     * @return why the access is denied
     */
    public String explainParentTypeAccess(MovieTicket parentTypedRef) {
        return "MovieTicket-typed reference to a "
                + parentTypedRef.getClass().getSimpleName()
                + ": DENIED (Java goes by the reference's declared type, "
                + "not the object's real type)";
    }

    public static void main(String[] args) {
        PremiumMovieTicket premium =
                new PremiumMovieTicket(7, 1, 320.0, "Dune Part Two", 150.0);

        System.out.println("Ticket price via own type: Rs " + premium.readPriceThroughOwnType());

        MovieTicket parentTypedRef = premium;
        System.out.println(premium.explainParentTypeAccess(parentTypedRef));
    }
}