package leave;

/**
 * A reviewer is any manager who can approve or reject a request. The workflow
 * depends on this interface, not on a concrete manager class.
 */
public interface Reviewer {

    /**
     * @param name the reviewer's name
     * @return a reviewer by name
     */
    static Reviewer named(String name) {
        return new Manager(name);
    }

    /**
     * @return the reviewer's name
     */
    String getName();

    /**
     * @param request the request under review
     * @return true when approved
     */
    boolean review(LeaveRequest request);

    /**
     * @param request the request under review
     * @param reason why the request is refused
     * @return true when rejected
     */
    boolean reject(LeaveRequest request, String reason);
}