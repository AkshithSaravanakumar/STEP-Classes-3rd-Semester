package submission;

/**
 * Coding assignment: 10% of the awarded marks lost per day late.
 */
public class CodingAssignment extends Assignment {

    public static final double PENALTY_PER_LATE_DAY = 0.10;

    /**
     * @param title the assignment title
     * @param maxMarks the maximum marks available
     * @param dueDate the submission deadline
     */
    public CodingAssignment(String title, int maxMarks, java.time.LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public String getTypeName() {
        return "Coding";
    }

    @Override
    public double penaltyPerLateDay() {
        return PENALTY_PER_LATE_DAY;
    }
}