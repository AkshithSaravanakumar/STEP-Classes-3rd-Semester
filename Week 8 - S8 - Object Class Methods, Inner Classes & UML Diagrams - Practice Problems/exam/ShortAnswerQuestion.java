package exam;

import java.util.Locale;

/**
 * Short-answer question: graded by comparing the trimmed, case-insensitive
 * response against the expected text. Keeping the comparison rule in one place
 * means all short answers are graded consistently.
 */
public class ShortAnswerQuestion extends Question {

    /**
     * Inner class: the grading rule itself, so the comparison logic is
     * declared next to the question type that uses it.
     *
     * UML: ShortAnswerQuestion o-- GradingRule (inner class)
     */
    public static class GradingRule {

        private final boolean ignoreCase;

        public GradingRule(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        /**
         * @param given the student's response
         * @param expected the expected answer
         * @return true when the response matches
         */
        public boolean matches(String given, String expected) {
            if (given == null || expected == null) {
                return false;
            }
            String left = given.trim();
            String right = expected.trim();
            return ignoreCase ? left.equalsIgnoreCase(right) : left.equals(right);
        }
    }

    private final String expectedAnswer;
    private final GradingRule rule;

    public ShortAnswerQuestion(String questionId, String text, int points, String expectedAnswer) {
        super(questionId, text, points);
        this.expectedAnswer = expectedAnswer;
        this.rule = new GradingRule(true);
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    @Override
    public boolean evaluate(Answer answer) {
        if (answer == null) {
            return false;
        }
        return rule.matches(answer.getResponse(), expectedAnswer);
    }

    /**
     * @return the expected answer in upper case, for display convenience
     */
    public String getExpectedAnswerUpperCase() {
        return expectedAnswer == null ? null : expectedAnswer.toUpperCase(Locale.ROOT);
    }
}