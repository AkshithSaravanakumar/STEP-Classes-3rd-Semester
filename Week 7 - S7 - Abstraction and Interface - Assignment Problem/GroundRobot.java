/**
 * A ground robot is trackable but is not a drone in any way, so it relates to
 * Drone not at all. It still works with the same instanceof check because
 * Trackable is what matters, not ancestry.
 */
public class GroundRobot implements Trackable {

    private static final String CURRENT_SECTOR = "Sector 4";

    private final String id;

    /**
     * @param id the robot's fleet id
     */
    public GroundRobot(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getLocation() {
        return id + " at " + CURRENT_SECTOR;
    }
}