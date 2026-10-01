public class LetterNote extends DeliveryNote {

    /**
     * @param trackingId the letter's tracking id
     */
    public LetterNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + getTrackingId() + " delivered";
    }
}