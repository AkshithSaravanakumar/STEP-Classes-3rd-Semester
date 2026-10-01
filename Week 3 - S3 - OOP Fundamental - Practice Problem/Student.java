public class Student {

    private String name;
    private int attendance;

    /**
     * Shared by every student object, so it is declared static instead of being
     * stored separately on each instance.
     */
    private static String collegeName = "SRM Institute of Science and Technology";

    private static int studentCount;

    /**
     * Creates a student and increments the shared studentCount once per
     * constructed object.
     *
     * @param name       student name
     * @param attendance attendance percentage
     */
    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }

    public static String getCollegeName() {
        return collegeName;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    /**
     * Static method: it may only touch static members, never instance fields
     * such as name or attendance.
     */
    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        Student first = new Student("Ravi", 88);
        Student second = new Student("Anitha", 92);

        // Accessed through the class name, not through either object
        Student.printCollegeInfo();
    }
}