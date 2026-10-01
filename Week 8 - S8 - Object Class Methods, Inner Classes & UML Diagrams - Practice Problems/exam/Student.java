package exam;

/**
 * A student who may sit many examinations.
 *
 * UML: Student "1" -- "0..*" Attempt
 */
public class Student {

    private final String studentId;
    private final String name;

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
        return name + " (" + studentId + ")";
    }
}