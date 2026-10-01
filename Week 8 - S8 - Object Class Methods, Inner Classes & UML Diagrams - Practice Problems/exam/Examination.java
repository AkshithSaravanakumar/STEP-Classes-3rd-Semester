package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An examination is an ordered, fixed set of questions. The set never changes
 * once the examination exists.
 *
 * UML: Examination "1" *-- "1..*" Question (composition)
 *      Examination "1" -- "0..1" Attempt (a student may hold at most one
 *      submitted attempt per examination)
 */
public class Examination {

    private final String examCode;
    private final List<Question> questions;

    public Examination(String examCode, List<Question> questions) {
        if (questions.isEmpty()) {
            throw new IllegalArgumentException("An examination needs at least one question.");
        }
        this.examCode = examCode;
        this.questions = new ArrayList<>(questions);
    }

    public String getExamCode() {
        return examCode;
    }

    /**
     * @return the questions, unmodifiable from the caller's side
     */
    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    /**
     * @param questionId the question's id
     * @return the matching question, or null when the id is not in this exam
     */
    public Question findQuestion(String questionId) {
        for (Question question : questions) {
            if (question.getQuestionId().equals(questionId)) {
                return question;
            }
        }
        return null;
    }

    /**
     * @return the total points available in this examination
     */
    public int getTotalPoints() {
        int total = 0;
        for (Question question : questions) {
            total += question.getPoints();
        }
        return total;
    }
}