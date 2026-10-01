package exam;

/**
 * Base type for every question. Each concrete type answers its own evaluation
 * question, so a new question type needs no change to the attempt or scoring
 * logic.
 *
 * UML:
 * <pre>
 * Question (abstract)
 *  - questionId : String
 *  - text : String
 *  - points : int
 *  + getQuestionId() : String
 *  + getText() : String
 *  + getPoints() : int
 *  + evaluate(answer : Answer) : boolean {abstract}
 *
 * Question <|-- MultipleChoiceQuestion
 * Question <|-- TrueFalseQuestion
 * Question <|-- ShortAnswerQuestion
 * </pre>
 */
public abstract class Question {

    private final String questionId;
    private final String text;
    private final int points;

    /**
     * @param questionId the question's unique id
     * @param text the question prompt
     * @param points how many points a correct answer earns
     */
    protected Question(String questionId, String text, int points) {
        this.questionId = questionId;
        this.text = text;
        this.points = points;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Self-evaluation: the question decides whether the recorded answer is
     * right, using its own rules.
     *
     * @param answer the student's recorded answer
     * @return true when the answer is correct
     */
    public abstract boolean evaluate(Answer answer);

    @Override
    public String toString() {
        return questionId + ": " + text;
    }
}