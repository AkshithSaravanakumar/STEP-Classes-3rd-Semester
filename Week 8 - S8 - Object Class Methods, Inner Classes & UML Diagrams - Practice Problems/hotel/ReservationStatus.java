package hotel;

/**
 * Reservation lifecycle: confirmed reservations can be cancelled, cancelled
 * ones cannot be revived.
 *
 * UML (state diagram):
 * <pre>
 *   +-----------+  cancel()  +------------+
 *   | CONFIRMED | ---------> | CANCELLED  |
 *   +-----------+            +------------+
 *         |                        (terminal)
 *         v
 *   +-----------+
 *   |  STAYED   |
 *   +-----------+
 * </pre>
 */
public enum ReservationStatus {

    CONFIRMED,
    CANCELLED,
    STAYED;

    /**
     * @return true when this reservation may still be cancelled
     */
    public boolean isCancellable() {
        return this == CONFIRMED;
    }
}