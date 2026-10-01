public class PackageBox implements Printable {

    private final String trackingId;

    /**
     * @param trackingId the parcel's tracking id
     */
    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}