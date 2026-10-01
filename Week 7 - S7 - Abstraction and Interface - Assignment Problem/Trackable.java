/**
 * Being trackable is independent of ancestry: a delivery drone and a ground
 * robot both report a location, while a scout drone deliberately does not
 * implement this interface at all.
 */
public interface Trackable {

    /**
     * @return this object's current location
     */
    String getLocation();
}