package submission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Submission portal. Written against the {@link Assignment} base type, so a new
 * assignment type needs no change here.
 */
public class SubmissionPortal {

    /**
     * Inner class: the graded work already recorded, keyed by student and
     * assignment. This is what enforces "no resubmission once graded" without
     * the portal having to remember every rule itself.
     *
     * UML: SubmissionPortal o-- GradedRecordStore (inner class)
     */
    public static class GradedRecordStore {

        private final Map<String, Submission> graded = new HashMap<>();

        private static String key(Student student, Assignment assignment) {
            return student.getStudentId() + "@" + assignment.getTitle();
        }

        /**
         * @param student the student
         * @param assignment the assignment
         * @return true when this student already has a graded submission
         */
        public boolean isGraded(Student student, Assignment assignment) {
            return graded.containsKey(key(student, assignment));
        }

        /**
         * @param submission the submission now graded
         */
        public void markGraded(Submission submission) {
            graded.put(key(submission.getStudent(), submission.getAssignment()), submission);
        }
    }

    private final GradedRecordStore store = new GradedRecordStore();
    private final List<Submission> submissions = new ArrayList<>();

    /**
     * @param student who is submitting
     * @param assignment what is being submitted
     * @param submissionDate when it was handed in
     * @return the recorded submission, or null when the work was already graded
     */
    public Submission submit(Student student, Assignment assignment, LocalDate submissionDate) {
        if (store.isGraded(student, assignment)) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return null;
        }
        Submission submission = new Submission(student, assignment, submissionDate);
        submissions.add(submission);

        int lateDays = submission.getLateDays();
        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle()
                    + "' received (on time).");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle()
                    + "' received (" + lateDays + " days late).");
        }
        System.out.println("Status: " + submission.getStatus() + ".");
        return submission;
    }

    /**
     * @param submission the submission to grade
     * @param awardedMarks the marks awarded before any penalty
     * @return the final marks, or -1 when the submission was already graded
     */
    public double grade(Submission submission, double awardedMarks) {
        double finalMarks = submission.grade(awardedMarks);
        if (finalMarks >= 0) {
            store.markGraded(submission);
        }
        return finalMarks;
    }

    /**
     * @return every submission the portal has accepted
     */
    public List<Submission> getSubmissions() {
        return new ArrayList<>(submissions);
    }

    public static void main(String[] args) {
        SubmissionPortal portal = new SubmissionPortal();

        CodingAssignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        WrittenAssignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));
        System.out.println(coding.getTypeName() + " assignment '" + coding.getTitle() + "' created (max "
                + coding.getMaxMarks() + ", due " + label(coding.getDueDate()) + ").");
        System.out.println(written.getTypeName() + " assignment '" + written.getTitle() + "' created (max "
                + written.getMaxMarks() + ", due " + label(written.getDueDate()) + ").");

        Student asha = new Student("S1", "Asha");
        Student ravi = new Student("S2", "Ravi");

        Submission ashaSubmission = portal.submit(asha, coding, LocalDate.of(2026, 3, 10));
        Submission raviSubmission = portal.submit(ravi, written, LocalDate.of(2026, 3, 14));

        portal.grade(ashaSubmission, 45);
        portal.grade(raviSubmission, 40);

        portal.submit(asha, coding, LocalDate.of(2026, 3, 11));
    }

    /**
     * @param date the date to label
     * @return the date as e.g. "Mar 10"
     */
    private static String label(LocalDate date) {
        String month = date.getMonth().getDisplayName(
                java.time.format.TextStyle.SHORT, java.util.Locale.ENGLISH);
        return month + " " + date.getDayOfMonth();
    }
}