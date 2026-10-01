/**
 * Operating a classroom device is the abstract concern shared by every device
 * in the room.
 */
public abstract class ClassroomDevice {

    /**
     * @return what this device is currently doing
     */
    public abstract String operate();
}