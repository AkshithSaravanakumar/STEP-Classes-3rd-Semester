/**
 * A scout drone shares the Drone parent with DeliveryDrone but not its
 * capability: choosing not to implement Trackable is a valid design, and this
 * class is deliberately not castable to Trackable.
 */
public class ScoutDrone extends Drone {

    private final String id;

    /**
     * @param id the drone's fleet id
     */
    public ScoutDrone(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String fly() {
        return "Scout drone " + id + " surveying the area";
    }
}