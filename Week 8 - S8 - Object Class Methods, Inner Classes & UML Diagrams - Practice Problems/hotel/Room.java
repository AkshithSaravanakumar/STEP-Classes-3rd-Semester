package hotel;

/**
 * Base type for every room category. Common state lives here; each category
 * supplies its own pricing through {@link #calculatePrice(int)}.
 *
 * UML:
 * <pre>
 * Room (abstract)
 *  - roomNumber : String
 *  + getRoomNumber() : String
 *  + getCategory() : String
 *  + calculatePrice(nights : int) : double {abstract}
 *
 * Room <|-- StandardRoom
 * Room <|-- DeluxeRoom
 * Room <|-- Suite
 * </pre>
 */
public abstract class Room {

    private final String roomNumber;

    protected Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @return a human-readable category name, e.g. "Standard Room"
     */
    public abstract String getCategory();

    /**
     * @param nights how many nights the room is booked for
     * @return the total booking price for this category
     */
    public abstract double calculatePrice(int nights);

    @Override
    public String toString() {
        return getCategory() + " " + roomNumber;
    }
}