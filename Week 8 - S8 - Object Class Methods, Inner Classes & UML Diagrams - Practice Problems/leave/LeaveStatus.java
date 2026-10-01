package leave;

/**
 * Leave request lifecycle. PENDING is reachable once, then the request moves
 * forward to a final state and can never return.
 *
 * UML (state diagram):
 * <pre>
 *   +-----------+  approve()  +-----------+
 *   |  PENDING  | ----------> | APPROVED  |
 *   +-----------+             +-----------+
 *        | reject()                |
 *        v                          (terminal, cannot revert to PENDING)
 *   +-----------+
 *   | REJECTED  |
 *   +-----------+
 * </pre>
 */
public enum LeaveStatus {

    PENDING,
    APPROVED,
    REJECTED;

    /**
     * A request is only mutable while it is still pending.
     *
     * @return true when this status may still change
     */
    public boolean isMutable() {
        return this == PENDING;
    }
}