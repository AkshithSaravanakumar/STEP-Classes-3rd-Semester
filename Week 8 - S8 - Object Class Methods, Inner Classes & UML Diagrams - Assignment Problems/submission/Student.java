package submission;

import java.time.LocalDate;

/**
 * A student who can submit work.
 *
 * UML: Student "1" -- "0..*" Submission
 */
public class Student {

    private final String studentId;
    private final String name;

    /**
     * @param studentId the student's unique id
     * @param name the student's display name
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}