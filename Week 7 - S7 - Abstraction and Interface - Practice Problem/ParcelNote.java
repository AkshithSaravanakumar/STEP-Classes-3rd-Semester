public class ParcelNote extends DeliveryNote {

    /**
     * @param trackingId the parcel's tracking id
     */
    public ParcelNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + getTrackingId() + " delivered";
    }
}