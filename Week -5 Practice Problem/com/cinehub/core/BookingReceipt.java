package com.cinehub.core;

/**
 * Genuinely immutable: every field is final and the seat array is defensively
 * copied on the way in and on the way out, so neither side can reach in and
 * change an issued receipt.
 *
 * The class itself cannot be final, because GroupBookingReceipt has to inherit
 * from it. Immutability is instead locked down with final fields and final
 * methods, which a subclass cannot override or reassign.
 */
public class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    /**
     * @param bookingId   receipt identifier
     * @param seatNumbers seats on the receipt, copied so later changes to the
     *                    caller's array cannot affect this receipt
     */
    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public final String getBookingId() {
        return bookingId;
    }

    /**
     * Copies on the way out, so mutating the returned array leaves the
     * receipt untouched.
     *
     * @return a fresh copy of the seats
     */
    public final String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    /**
     * "Changing" a receipt returns a brand-new object, because the issued one
     * can never change.
     *
     * @param index   seat position to correct
     * @param newSeat corrected seat label
     * @return a new receipt with the correction applied
     */
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= seatNumbers.length) {
            return this;
        }

        String[] updated = seatNumbers.clone();
        updated[index] = newSeat;

        return new BookingReceipt(bookingId, updated);
    }

    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});

        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println(b.getSeatNumbers()[0]);

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(java.util.Arrays.toString(b.getSeatNumbers()));
        System.out.println(java.util.Arrays.toString(updated.getSeatNumbers()));
    }
}