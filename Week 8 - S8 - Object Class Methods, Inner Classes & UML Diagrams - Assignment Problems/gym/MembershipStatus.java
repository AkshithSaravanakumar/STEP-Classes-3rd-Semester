package gym;

/**
 * Membership lifecycle.
 *
 * UML (state diagram):
 * <pre>
 *   +--------+  freeze()   +--------+  unfreeze()  +--------+
 *   | ACTIVE | ----------> | FROZEN | ------------> | ACTIVE |
 *   +--------+             +--------+              +--------+
 *        |                      | expire()              | expire()
 *        | expire()             v                       v
 *        +------------------&gt; +--------+
 *                              |EXPIRED |
 *                              +--------+
 *                                 (terminal)
 * </pre>
 */
public enum MembershipStatus {

    ACTIVE,
    FROZEN,
    EXPIRED;

    /**
     * @return true when this membership permits check-in
     */
    public boolean allowsCheckIn() {
        return this == ACTIVE;
    }

    /**
     * @return true when this membership may still be frozen
     */
    public boolean canFreeze() {
        return this == ACTIVE;
    }

    /**
     * @return true when this membership may be unfrozen
     */
    public boolean canUnfreeze() {
        return this == FROZEN;
    }

    /**
     * @return true when this membership may still expire
     */
    public boolean canExpire() {
        return this != EXPIRED;
    }
}