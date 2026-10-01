package submission;

/**
 * Submission lifecycle. A submission can be graded only once, and a graded
 * submission is terminal.
 *
 * UML (state diagram):
 * <pre>
 *   +-----------+  grade()  +--------+
 *   | SUBMITTED | --------&gt; | GRADED |
 *   +-----------+            +--------+
 *                                 (terminal: no resubmission allowed)
 * </pre>
 */
public enum SubmissionStatus {

    SUBMITTED,
    GRADED;

    /**
     * @return true when this submission may still change
     */
    public boolean isMutable() {
        return this == SUBMITTED;
    }
}