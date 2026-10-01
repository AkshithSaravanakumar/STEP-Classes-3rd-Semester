/**
 * Accepts any Object and reports a location only when that object implements
 * Trackable, which is why the ground robot works here too.
 */
public class FleetTracker {

    /**
     * @param o any object, trackable or not
     * @return the object's location, or a message that tracking is unavailable
     */
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackable = (Trackable) o;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));

        Drone[] fleet = { d, s };
        for (Drone drone : fleet) {
            System.out.println(drone.fly());
        }
    }
}