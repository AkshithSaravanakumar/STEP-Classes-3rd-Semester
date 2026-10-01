public class ParkingTicket {

    private String vehicleNo;
    private double ratePerMinute;

    /**
     * @param vehicleNo      vehicle registration number
     * @param ratePerMinute  fine rate charged per overstay minute
     */
    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public double getRatePerMinute() {
        return ratePerMinute;
    }

    /**
     * The fine formula. Declared final so no future "VIP parking" subclass can
     * quietly charge a different rate.
     *
     * @param overstayMinutes minutes the vehicle overstayed
     * @return the fine amount
     */
    final double calculateFine(int overstayMinutes) {
        return overstayMinutes * ratePerMinute;
    }

    /**
     * Prints the receipt for a ticket that owes a fine. Declared final so the
     * locked formula cannot be overridden.
     *
     * @param overstayMinutes minutes the vehicle overstayed
     */
    final void printReceipt(int overstayMinutes) {
        System.out.println(vehicleNo + " - Fine: Rs " + calculateFine(overstayMinutes));
    }

    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"};
        double[] ratePerMinute = {2, 2, 3, 2};
        int[] overstayMinutes = {15, 0, -5, 8};

        ParkingTicket[] tickets = new ParkingTicket[vehicleNos.length];

        for (int i = 0; i < tickets.length; i++) {
            tickets[i] = new ParkingTicket(vehicleNos[i], ratePerMinute[i]);

            // Only tickets with genuine overstay minutes owe a fine
            if (overstayMinutes[i] > 0) {
                tickets[i].printReceipt(overstayMinutes[i]);
            } else {
                System.out.println(vehicleNos[i] + " - No fine, within allotted time");
            }
        }
    }
}