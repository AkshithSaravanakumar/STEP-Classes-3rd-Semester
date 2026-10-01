/**
 * Root of the drone family. fly() is abstract because flying is what every drone
 * does, but no single description fits all of them.
 */
public abstract class Drone {

    /**
     * @return what this drone does while flying
     */
    public abstract String fly();
}