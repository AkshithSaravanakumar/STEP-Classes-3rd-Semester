/**
 * A delivery drone carries parcels and, unlike its scout sibling, reports its
 * position.
 */
public class DeliveryDrone extends Drone implements Trackable {

    private static final String CURRENT_SECTOR = "Sector 4";

    private final String id;

    /**
     * @param id the drone's fleet id
     */
    public DeliveryDrone(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String fly() {
        return "Delivery drone " + id + " carrying a parcel";
    }

    @Override
    public String getLocation() {
        return id + " at " + CURRENT_SECTOR;
    }
}