package exam;

/**
 * A single recorded answer. It keeps a reference to its question so evaluation
 * can be delegated back to the question that owns the rules.
 *
 * UML: Answer "*" -- "1" Question
 *      Answer "0..1" -- "0..1" Attempt
 */
public class Answer {

    private final Question question;
    private String response;
    private Attempt attempt;

    /**
     * @param question the question being answered
     * @param response the student's response
     */
    public Answer(Question question, String response) {
        this.question = question;
        this.response = response;
    }

    public Question getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    /**
     * The owning attempt registers itself here on creation.
     *
     * @param attempt the attempt this answer belongs to
     */
    void attachTo(Attempt attempt) {
        this.attempt = attempt;
    }

    /**
     * @return true when the recorded response is correct
     */
    public boolean isCorrect() {
        return question.evaluate(this);
    }

    @Override
    public String toString() {
        return question.getQuestionId() + " -> " + response;
    }
}