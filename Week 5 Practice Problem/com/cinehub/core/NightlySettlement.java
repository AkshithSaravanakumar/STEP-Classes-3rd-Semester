package com.cinehub.core;

public class NightlySettlement {

    /**
     * Reconciles a night's receipts. Group bookings are handled differently
     * from regular ones via an instanceof check, and a null entry is counted
     * and skipped rather than crashing the run.
     *
     * @param receipts the night's receipts, possibly containing nulls
     * @return settlement summary
     */
    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;
        int totalSeats = 0;

        for (int i = 0; i < receipts.length; i++) {
            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            totalSeats += receipts[i].getSeatNumbers().length;

            if (receipts[i] instanceof GroupBookingReceipt) {
                groupCount++;
                GroupBookingReceipt group = (GroupBookingReceipt) receipts[i];
                System.out.println(group.getBookingId() + " | Group booking of "
                        + group.getGroupSize() + " | Seats: "
                        + receipts[i].getSeatNumbers().length);
            } else {
                individualCount++;
                System.out.println(receipts[i].getBookingId() + " | Individual | Seats: "
                        + receipts[i].getSeatNumbers().length);
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + groupCount + " group | " + individualCount + " individual"
                + " | Seats settled: " + totalSeats;
    }

    public static void main(String[] args) {
        BookingReceipt[] receipts = {
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
                null,
                new BookingReceipt("CH-3003", new String[]{"C1"})
        };

        System.out.println(processNightlySettlement(receipts));
    }
}