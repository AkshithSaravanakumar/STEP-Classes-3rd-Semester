package laundry;

/**
 * A hostel resident who can use the machines.
 *
 * UML: Student "1" -- "0..*" WashCycle
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