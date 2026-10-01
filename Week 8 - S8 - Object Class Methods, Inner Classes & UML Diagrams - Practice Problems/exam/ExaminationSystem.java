package exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Examination workflow. It only ever talks to {@link Question}, so a new
 * question type is added by implementing one class.
 */
public class ExaminationSystem {

    /**
     * Inner class enforcing the "one submitted attempt per examination per
     * student" rule. An inner class keeps the rule and the records it guards in
     * the same file.
     *
     * UML: ExaminationSystem o-- AttemptRegistry (inner class)
     */
    public static class AttemptRegistry {

        private final Map<String, Attempt> submitted = new LinkedHashMap<>();

        private static String key(Student student, Examination examination) {
            return student.getStudentId() + "@" + examination.getExamCode();
        }

        /**
         * @param student the student attempting the exam
         * @param examination the exam being attempted
         * @return true when a submitted attempt already exists
         */
        public boolean hasSubmitted(Student student, Examination examination) {
            return submitted.containsKey(key(student, examination));
        }

        /**
         * @param attempt the attempt now marked submitted
         */
        public void markSubmitted(Attempt attempt) {
            submitted.put(key(attempt.getStudent(), attempt.getExamination()), attempt);
        }
    }

    private static final AttemptRegistry REGISTRY = new AttemptRegistry();

    private ExaminationSystem() {
    }

    /**
     * @param student the student starting the exam
     * @param examination the exam to start
     * @return a new in-progress attempt
     */
    public static Attempt startExamination(Student student, Examination examination) {
        System.out.println(examination.getExamCode() + " started by " + student.getName() + ".");
        return new Attempt(student, examination);
    }

    /**
     * @param attempt the attempt to close
     * @return the final score, or -1 when the student already submitted
     */
    public static int submitExamination(Attempt attempt) {
        if (REGISTRY.hasSubmitted(attempt.getStudent(), attempt.getExamination())) {
            System.out.println(attempt.getStudent().getName() + " has already submitted "
                    + attempt.getExamination().getExamCode() + ".");
            return -1;
        }
        int score = attempt.submit();
        REGISTRY.markSubmitted(attempt);
        return score;
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>(Arrays.asList(
                new MultipleChoiceQuestion("Question 1", "Which is a primitive type?", 5,
                        new String[]{ "Integer", "Object", "int", "String" }, 'C'),
                new TrueFalseQuestion("Question 2", "Java is platform independent.", 5, false)));

        Examination examA = new Examination("Exam A", questions);
        Student student1 = new Student("S1", "Student 1");

        Attempt attempt = startExamination(student1, examA);
        attempt.recordAnswer("Question 1", "C");
        attempt.recordAnswer("Question 2", "True");
        submitExamination(attempt);

        attempt.recordAnswer("Question 1", "A");
        submitExamination(attempt);
    }
}