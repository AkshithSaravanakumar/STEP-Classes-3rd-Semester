package exam;

/**
 * One sitting of an examination by one student.
 *
 * UML (state diagram):
 * <pre>
 *   +------------+  submit()  +------------+
 *   | InProgress | ---------> | SUBMITTED  |
 *   +------------+            +------------+
 *        |                          (answers frozen from here on)
 *        v
 *   +------------+
 *   |  ABANDONED |
 *   +------------+
 * </pre>
 *
 * Attempt "1" o-- "0..*" Answer (composition: answers live and die with the
 * attempt). The attempted state is modelled with an inner enum so the states
 * and the transitions that use them are declared together.
 */
public class Attempt {

    /**
     * Inner enum: the attempt lifecycle. PENDING-free by design, an attempt
     * starts IN_PROGRESS and can only move forward.
     */
    public enum AttemptStatus {
        IN_PROGRESS,
        SUBMITTED,
        ABANDONED
    }

    private final Student student;
    private final Examination examination;
    private final java.util.List<Answer> answers = new java.util.ArrayList<>();

    private AttemptStatus status = AttemptStatus.IN_PROGRESS;

    private int score;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public Student getStudent() {
        return student;
    }

    public Examination getExamination() {
        return examination;
    }

    public AttemptStatus getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    /**
     * A submitted attempt is frozen, so no further answer may be recorded.
     *
     * @return true when answers may still be changed
     */
    public boolean isEditable() {
        return status == AttemptStatus.IN_PROGRESS;
    }

    /**
     * @return the answers recorded on this attempt
     */
    public java.util.List<Answer> getAnswers() {
        return java.util.Collections.unmodifiableList(answers);
    }

    /**
     * Records an answer and keeps it in this attempt's own list, which is what
     * the UML composition says: an answer has no life outside its attempt.
     *
     * @param questionId the question being answered
     * @param response the student's response
     * @return the recorded answer, or null when the attempt is frozen or the
     *         question is not part of this examination
     */
    public Answer recordAnswer(String questionId, String response) {
        if (!isEditable()) {
            System.out.println("Cannot change answers for a submitted examination.");
            return null;
        }
        Question question = examination.findQuestion(questionId);
        if (question == null) {
            System.out.println("Question " + questionId + " is not part of " + examination.getExamCode() + ".");
            return null;
        }
        Answer answer = new Answer(question, response);
        answer.attachTo(this);
        answers.add(answer);
        System.out.println("Answer recorded for " + questionId + ".");
        return answer;
    }

    /**
     * Scores every answer this attempt holds by asking each question to
     * evaluate itself, so this method is unaware of concrete question types.
     *
     * @return the final score
     */
    public int submit() {
        if (!isEditable()) {
            System.out.println("Cannot submit " + examination.getExamCode() + " again.");
            return score;
        }
        System.out.println(examination.getExamCode() + " submitted by " + student.getName() + ".");

        int total = 0;
        for (Answer answer : answers) {
            boolean correct = answer.isCorrect();
            int earned = correct ? answer.getQuestion().getPoints() : 0;
            total += earned;
            System.out.println(answer.getQuestion().getQuestionId() + ": "
                    + (correct ? "Correct" : "Incorrect") + " (" + earned + " points)");
        }
        this.score = total;
        this.status = AttemptStatus.SUBMITTED;
        System.out.println("Total score: " + total + "/" + examination.getTotalPoints());
        return total;
    }
}