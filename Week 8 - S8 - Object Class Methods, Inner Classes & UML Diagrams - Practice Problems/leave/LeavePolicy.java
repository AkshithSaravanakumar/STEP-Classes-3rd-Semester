package leave;

/**
 * Policy enforcement is separated from request management: an Employee decides
 * whether a request is even admissible, and only a valid request is handed to
 * the review workflow.
 */
public interface LeavePolicy {

    /**
     * @param request the request to evaluate
     * @return true when this policy permits the request
     */
    boolean isEligible(LeaveRequest request);

    /**
     * @return a human-readable summary of what this policy allows
     */
    String describe();
}
