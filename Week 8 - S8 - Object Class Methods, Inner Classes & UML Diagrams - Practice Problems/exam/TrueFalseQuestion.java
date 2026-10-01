package exam;

/**
 * True/false question: only the two literals "true" and "false" are accepted,
 * in any case.
 */
public class TrueFalseQuestion extends Question {

    private final boolean correctValue;

    public TrueFalseQuestion(String questionId, String text, int points, boolean correctValue) {
        super(questionId, text, points);
        this.correctValue = correctValue;
    }

    public boolean getCorrectValue() {
        return correctValue;
    }

    @Override
    public boolean evaluate(Answer answer) {
        if (answer == null || answer.getResponse() == null) {
            return false;
        }
        String given = answer.getResponse().trim();
        if (given.equalsIgnoreCase("true")) {
            return correctValue;
        }
        if (given.equalsIgnoreCase("false")) {
            return !correctValue;
        }
        return false;
    }
}