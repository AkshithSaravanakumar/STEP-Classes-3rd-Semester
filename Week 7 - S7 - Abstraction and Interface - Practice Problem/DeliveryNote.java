/**
 * Confirmation works with or without a signature, so the base class declares
 * the no-argument form as abstract and provides the overloaded form that reuses
 * it, keeping the core message in one place.
 */
public abstract class DeliveryNote {

    private final String trackingId;

    /**
     * @param trackingId the delivery's tracking id
     */
    public DeliveryNote(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    /**
     * @return the confirmation message for this delivery type
     */
    public abstract String confirmDelivery();

    /**
     * @param signature the signer's name
     * @return the confirmation message with the signature appended
     */
    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }

    /**
     * @param notes a mixed array of delivery notes
     */
    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;
        logAll(new DeliveryNote[]{ ref, new LetterNote("TRK-2") });
    }
}