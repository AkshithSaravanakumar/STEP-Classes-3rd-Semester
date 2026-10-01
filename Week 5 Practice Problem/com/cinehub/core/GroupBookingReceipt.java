package com.cinehub.core;

/**
 * Group-booking variant of a receipt. The extra field is final too, so a group
 * receipt is as immutable as a regular one.
 */
public class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    /**
     * @param bookingId   receipt identifier
     * @param seatNumbers seats on the receipt
     * @param groupSize   number of people in the group
     */
    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public final int getGroupSize() {
        return groupSize;
    }

    @Override
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= getSeatNumbers().length) {
            return this;
        }

        String[] updated = getSeatNumbers();
        updated[index] = newSeat;

        return new GroupBookingReceipt(getBookingId(), updated, groupSize);
    }

    public static void main(String[] args) {
        GroupBookingReceipt g =
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2);

        System.out.println(g.getBookingId() + " | Seats: "
                + java.util.Arrays.toString(g.getSeatNumbers())
                + " | Group size: " + g.getGroupSize());
    }
}