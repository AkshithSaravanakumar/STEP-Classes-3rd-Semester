package submission;

import java.time.LocalDate;

/**
 * One student's submission of one assignment. The status is private with no
 * public setter, so it can only change through {@link #grade}, which refuses to
 * run twice.
 *
 * UML:
 * <pre>
 * Submission
 *  - student : Student
 *  - assignment : Assignment
 *  - submissionDate : LocalDate
 *  - status : SubmissionStatus
 *  - awardedMarks : double
 *  - finalMarks : double
 *  + getStatus() : SubmissionStatus
 *  + isGraded() : boolean
 *  + grade(awardedMarks : double) : double
 * </pre>
 *
 * Submission "0..1" -- "1" Assignment (aggregation)
 * Submission "*" -- "1" Student
 */
public class Submission {

    private final Student student;
    private final Assignment assignment;
    private final LocalDate submissionDate;

    private SubmissionStatus status = SubmissionStatus.SUBMITTED;
    private double awardedMarks;
    private double finalMarks;

    /**
     * @param student who submitted the work
     * @param assignment what was submitted
     * @param submissionDate when it was handed in
     */
    public Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public double getAwardedMarks() {
        return awardedMarks;
    }

    public double getFinalMarks() {
        return finalMarks;
    }

    /**
     * @return true when faculty have graded this submission
     */
    public boolean isGraded() {
        return status == SubmissionStatus.GRADED;
    }

    /**
     * @return whole days this submission is past the due date
     */
    public int getLateDays() {
        return assignment.lateDays(submissionDate);
    }

    /**
     * Grades once, delegating the penalty to the assignment so this class never
     * knows the assignment type.
     *
     * @param awardedMarks the marks awarded before any penalty
     * @return the final marks, or -1 when the submission was already graded
     */
    public double grade(double awardedMarks) {
        if (!status.isMutable()) {
            System.out.println("Cannot grade: '" + assignment.getTitle() + "' has already been graded.");
            return -1;
        }
        this.awardedMarks = awardedMarks;
        this.finalMarks = assignment.applyLatePenalty(awardedMarks, submissionDate);
        this.status = SubmissionStatus.GRADED;

        int lateDays = getLateDays();
        if (lateDays == 0) {
            System.out.println(student.getName() + " graded: " + format(finalMarks)
                    + "/" + assignment.getMaxMarks() + ".");
        } else {
            double penalty = assignment.penaltyPerLateDay() * lateDays;
            System.out.println(student.getName() + " graded: " + format(finalMarks)
                    + "/" + assignment.getMaxMarks() + " after " + Math.round(penalty * 100)
                    + "% late penalty.");
        }
        System.out.println("Status: " + status + ".");
        return finalMarks;
    }

    /**
     * @return the marks as a plain number with no trailing zeros
     */
    private static String format(double marks) {
        if (marks == Math.floor(marks)) {
            return String.valueOf((long) marks);
        }
        return String.valueOf(Math.round(marks * 100) / 100.0);
    }
}