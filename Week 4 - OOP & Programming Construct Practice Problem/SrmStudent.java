public class SrmStudent {

    private static String collegeName;
    private static String academicYear;

    /**
     * Static initialisation block: runs exactly once when the class is first
     * loaded, no matter how many students are created afterwards.
     */
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2025-2026";
        System.out.println("College info loaded");
    }

    private String name;

    /**
     * @param name student name
     */
    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + name);
    }

    public String getName() {
        return name;
    }

    public static String getCollegeName() {
        return collegeName;
    }

    public static String getAcademicYear() {
        return academicYear;
    }

    public static void printCollegeInfo() {
        System.out.println("College: " + collegeName);
        System.out.println("Academic Year: " + academicYear);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        SrmStudent[] batch = new SrmStudent[names.length];

        // Creating a whole batch of students does not re-run the static block
        for (int i = 0; i < names.length; i++) {
            batch[i] = new SrmStudent(names[i]);
        }
    }
}