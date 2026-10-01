/**
 * Simple single-inheritance subclass. memberId and borrowLimit are forwarded
 * to the parent via super(...) rather than being redeclared here.
 */
public class StudentMember extends LibraryMember {

    private String course;

    /**
     * @param memberId    the membership id
     * @param borrowLimit how many books this member may borrow
     * @param course      the course the student is enrolled in
     */
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    /**
     * Every fine is halved before it reaches the parent, so the discount
     * applies to the recorded amount too. The parent's own logic does the
     * recording, which is why there is no second recording step here.
     *
     * @param amount the undiscounted fine
     */
    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course
                + " | Books Borrowed: " + getBooksBorrowed();
    }
}