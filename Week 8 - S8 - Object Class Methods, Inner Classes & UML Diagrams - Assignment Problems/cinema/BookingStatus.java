package cinema;

/**
 * Booking lifecycle. A cancelled booking is terminal, and cancelling also has to
 * release the seats.
 *
 * UML (state diagram):
 * <pre>
 *   +-----------+  cancel()  +------------+
 *   | CONFIRMED | --------&gt; | CANCELLED  |
 *   +-----------+            +------------+
 *                                 (terminal)
 * </pre>
 */
public enum BookingStatus {

    CONFIRMED,
    CANCELLED;

    /**
     * @return true when this booking may still be cancelled
     */
    public boolean isCancellable() {
        return this == CONFIRMED;
    }
}