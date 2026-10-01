/**
 * Multilevel inheritance: three generations deep, LibraryMember ->
 * StudentMember -> HonorsStudentMember. The extra borrowing privilege is a
 * bonus allowance on top of the ordinary limit.
 */
public class HonorsStudentMember extends StudentMember {

    private int bonusLimit;

    /**
     * @param memberId    the membership id
     * @param borrowLimit the ordinary borrowing limit
     * @param course      the course the student is enrolled in
     * @param bonusLimit  extra books allowed for honors privileges
     */
    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    public int getBonusLimit() {
        return bonusLimit;
    }

    /**
     * Honors members may borrow past the ordinary limit, up to the bonus
     * allowance on top of it.
     *
     * @return the ordinary limit plus the honors bonus
     */
    @Override
    protected int getEffectiveBorrowLimit() {
        return getBorrowLimit() + bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + getCourse()
                + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + getBooksBorrowed();
    }

    public static void main(String[] args) {
        System.out.println(new LibraryMember("STU1", 3).displayInfo());
        System.out.println(new StudentMember("STU2", 3, "CSE").displayInfo());
        System.out.println(new HonorsStudentMember("STU3", 3, "ECE", 2).displayInfo());
        System.out.println(new FacultyMember("STU4", 5, "Physics").displayInfo());
    }
}