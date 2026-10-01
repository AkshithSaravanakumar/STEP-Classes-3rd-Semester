package submission;

import java.time.LocalDate;

/**
 * Written assignment: 20% of the awarded marks lost per day late, double the
 * coding penalty.
 */
public class WrittenAssignment extends Assignment {

    public static final double PENALTY_PER_LATE_DAY = 0.20;

    /**
     * @param title the assignment title
     * @param maxMarks the maximum marks available
     * @param dueDate the submission deadline
     */
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public String getTypeName() {
        return "Written";
    }

    @Override
    public double penaltyPerLateDay() {
        return PENALTY_PER_LATE_DAY;
    }
}