public class Course {

    private String code;
    private String title;
    private int credits;
    private int labCredits;

    /**
     * Full constructor used by courses that have a separate lab component.
     *
     * @param code       course code
     * @param title      course title
     * @param credits    theory credits
     * @param labCredits lab credits
     */
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    /**
     * Overloaded constructor for theory-only courses. Chains to the
     * four-argument constructor via this(...) so the initialization logic is
     * written only once.
     *
     * @param code    course code
     * @param title   course title
     * @param credits theory credits
     */
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public int getLabCredits() {
        return labCredits;
    }

    /**
     * @return total credits, i.e. theory credits plus lab credits
     */
    public int totalCredits() {
        return credits + labCredits;
    }

    public static void main(String[] args) {
        Course theoryOnly = new Course("21CSC201J", "Data Structures", 4);
        Course withLab = new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(theoryOnly.getCode() + " total credits: " + theoryOnly.totalCredits());
        System.out.println(withLab.getCode() + " total credits: " + withLab.totalCredits());
    }
}