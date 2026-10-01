package exam;

/**
 * Multiple-choice question: a single letter option must be selected.
 */
public class MultipleChoiceQuestion extends Question {

    private final String[] options;
    private final char correctOption;

    /**
     * @param questionId the question's unique id
     * @param text the question prompt
     * @param points how many points a correct answer earns
     * @param options the selectable options
     * @param correctOption the correct option letter
     */
    public MultipleChoiceQuestion(String questionId, String text, int points,
                                  String[] options, char correctOption) {
        super(questionId, text, points);
        this.options = options.clone();
        this.correctOption = Character.toUpperCase(correctOption);
    }

    public String[] getOptions() {
        return options.clone();
    }

    public char getCorrectOption() {
        return correctOption;
    }

    /**
     * Case-insensitive so "c" and "C" both count as selecting option C.
     *
     * @param answer the recorded answer
     * @return true when the selected option is the correct one
     */
    @Override
    public boolean evaluate(Answer answer) {
        if (answer == null || answer.getResponse() == null) {
            return false;
        }
        String given = answer.getResponse().trim();
        return given.length() == 1
                && Character.toUpperCase(given.charAt(0)) == correctOption;
    }
}