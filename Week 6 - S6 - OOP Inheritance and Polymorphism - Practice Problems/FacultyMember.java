/**
 * Hierarchical inheritance: extends LibraryMember directly, entirely
 * independent of StudentMember.
 */
public class FacultyMember extends LibraryMember {

    private String department;

    /**
     * @param memberId    the membership id
     * @param borrowLimit how many books this member may borrow
     * @param department  the faculty department
     */
    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    /**
     * Faculty memberships get their number from the same shared counter.
     *
     * @param borrowLimit how many books this member may borrow
     * @param department  the faculty department
     */
    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}