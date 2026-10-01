package submission;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * An assignment owns its own late-penalty rule. The grading workflow never
 * branches on assignment type, it just asks the assignment to apply its
 * penalty, so a new type is one new subclass.
 *
 * UML:
 * <pre>
 * Assignment (abstract)
 *  - title : String
 *  - maxMarks : int
 *  - dueDate : LocalDate
 *  + getTypeName() : String {abstract}
 *  + lateDays(submissionDate : LocalDate) : int
 *  + penaltyPerLateDay() : double {abstract}
 *  + applyLatePenalty(awardedMarks : double) : double
 *
 * Assignment &lt;|-- CodingAssignment
 * Assignment &lt;|-- WrittenAssignment
 * </pre>
 */
public abstract class Assignment {

    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    /**
     * @param title the assignment title
     * @param maxMarks the maximum marks available
     * @param dueDate the submission deadline
     */
    protected Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * @return the display name of this assignment type
     */
    public abstract String getTypeName();

    /**
     * @return the fraction of the awarded marks lost per day late
     */
    public abstract double penaltyPerLateDay();

    /**
     * The late-day count belongs to the assignment because it owns the due date.
     *
     * @param submissionDate when the work was handed in
     * @return whole days late, never negative
     */
    public int lateDays(LocalDate submissionDate) {
        if (submissionDate == null) {
            return 0;
        }
        long days = ChronoUnit.DAYS.between(dueDate, submissionDate);
        return days > 0 ? (int) days : 0;
    }

    /**
     * Applies this type's own penalty rule to the awarded marks. The penalty is
     * proportional to the marks awarded, not to the maximum, which is why a
     * 40% penalty on 40 awarded marks gives 24.
     *
     * @param awardedMarks the marks faculty awarded before any penalty
     * @return the final marks after the late penalty
     */
    public double applyLatePenalty(double awardedMarks, LocalDate submissionDate) {
        int lateDays = lateDays(submissionDate);
        if (lateDays == 0) {
            return awardedMarks;
        }
        double penalty = penaltyPerLateDay() * lateDays;
        double finalMarks = awardedMarks * (1 - penalty);
        return Math.max(0, Math.min(maxMarks, finalMarks));
    }
}