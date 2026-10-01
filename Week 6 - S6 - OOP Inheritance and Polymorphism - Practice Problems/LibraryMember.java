public class LibraryMember {

    private static final int MAX_FINES = 10;
    private static final int FIRST_MEMBER_NUMBER = 101;

    private static int membersEnrolled;

    private final String memberNumber;
    private final int borrowLimit;

    private String memberId;
    private int booksBorrowed;

    private final int[] fineHistory = new int[MAX_FINES];
    private int fineCount;

    private String lastBorrowedGenre;

    /**
     * Assigns the shared, never-reassignable member number from a static
     * counter that ticks once per object.
     *
     * @param borrowLimit how many books this member may borrow
     */
    public LibraryMember(int borrowLimit) {
        this("AUTO", borrowLimit);
    }

    /**
     * The one place memberId validation lives. A blank, whitespace-only, or
     * too-short id is refused outright rather than creating a half-valid
     * membership.
     *
     * @param memberId    the membership id
     * @param borrowLimit how many books this member may borrow
     */
    public LibraryMember(String memberId, int borrowLimit) {
        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }

        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        membersEnrolled++;
        this.memberNumber = "LIB-" + (FIRST_MEMBER_NUMBER + membersEnrolled - 1);
        this.memberId = memberId.trim();
        this.borrowLimit = borrowLimit;
    }

    public final String getMemberNumber() {
        return memberNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getLastBorrowedGenre() {
        return lastBorrowedGenre;
    }

    /**
     * The ceiling borrowBook() checks against. Subclasses widen it rather than
     * duplicating the borrow logic.
     *
     * @return the maximum books this member may hold
     */
    protected int getEffectiveBorrowLimit() {
        return borrowLimit;
    }

    /**
     * Borrows one book, up to the effective limit.
     */
    public void borrowBook() {
        if (booksBorrowed >= getEffectiveBorrowLimit()) {
            return;
        }

        booksBorrowed++;
    }

    /**
     * Records the genre and then delegates to the no-argument version, so the
     * limit logic is never duplicated.
     *
     * @param genre the genre of the book being borrowed
     */
    public void borrowBook(String genre) {
        this.lastBorrowedGenre = genre;
        borrowBook();
    }

    /**
     * Records a fine into the private history. Student members reuse this by
     * calling super after applying their own discount, which is why the
     * recording logic lives only here.
     *
     * @param amount the fine to apply
     */
    protected void chargeFine(int amount) {
        if (fineCount >= MAX_FINES) {
            return;
        }

        fineHistory[fineCount] = amount;
        fineCount++;
    }

    /**
     * A fresh copy every time, so outside code can never reach the real
     * internal array.
     *
     * @return the recorded fines
     */
    public int[] getFineHistory() {
        int[] copy = new int[fineCount];
        System.arraycopy(fineHistory, 0, copy, 0, fineCount);
        return copy;
    }

    /**
     * @return the sum of every fine ever charged to this membership
     */
    public int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }

    /**
     * Describes this membership. Every subclass overrides it, so a single call
     * through a LibraryMember reference produces the right line.
     *
     * @return this membership's own description
     */
    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    /**
     * Checks the exact format "R" + two digits + one uppercase letter, using
     * charAt() and the Character checks rather than a regular expression.
     *
     * @param code the renewal code entered by the member
     * @return whether the code is well formed
     */
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(3));
    }

    /**
     * @return how many memberships have been constructed so far
     */
    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    /**
     * Attempts to build one member per entry and counts the rejections
     * instead of stopping at the first bad id. No pre-validation happens here:
     * the constructor is the only place the rule lives.
     *
     * @param memberIds   ids to enroll
     * @param borrowLimit limit given to each new member
     * @return enrolment summary
     */
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {
            try {
                new LibraryMember(memberIds[i], borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException rejectedId) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    /**
     * Decides the generation purely through instanceof checks. No class carries
     * a manual "type" field.
     *
     * @param member the membership to classify
     * @return where it sits in the hierarchy
     */
    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMember) {
            return "Direct subclass (2 generations deep)";
        }

        return "Base class (1 generation)";
    }

    /**
     * Sums borrowed counts across a mixed list. No type check is needed: the
     * call on a LibraryMember reference already runs each object's own
     * getBooksBorrowed().
     *
     * @param members any mix of the four membership types
     * @return the total books borrowed
     */
    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (int i = 0; i < members.length; i++) {
            total += members[i].getBooksBorrowed();
        }

        return total;
    }

    /**
     * Builds the whole weekly report as one piece of text. displayInfo() is
     * called polymorphically, and the course is reached through an instanceof
     * guard placed immediately before the downcast, so the cast is never
     * attempted on a membership that isn't a student.
     *
     * @param members the memberships to report on
     * @return the assembled report
     */
    public static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < members.length; i++) {
            LibraryMember member = members[i];

            // Called polymorphically: no instanceof chain decides what to print
            report.append(member.displayInfo());

            if (member instanceof StudentMember) {
                // Check first, cast only inside the branch that already passed
                StudentMember student = (StudentMember) member;
                report.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    /**
     * Settles the night's memberships. Faculty members are separated from
     * regular ones via instanceof, and a null placeholder entry is counted and
     * skipped rather than crashing the run.
     *
     * @param members the batch to audit
     * @return audit summary
     */
    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (members[i] instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        // Problem 1
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException rejected) {
            System.out.println(rejected.getMessage());
        }

        System.out.println(enrollBatch(new String[]{"STU1", "LB1", "STU2", "   ", "STU3"}, 3));

        // Problem 2
        StudentMember studentMember = new StudentMember("STU10", 3, "CSE");
        HonorsStudentMember honorsMember =
                new HonorsStudentMember("STU11", 3, "ECE", 2);
        FacultyMember facultyMember = new FacultyMember("STU12", 5, "Physics");

        System.out.println(classifyGeneration(honorsMember));
        System.out.println(classifyGeneration(facultyMember));

        studentMember.borrowBook();
        studentMember.borrowBook();
        honorsMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();

        System.out.println(getTotalBooksBorrowed(
                new LibraryMember[]{studentMember, honorsMember, facultyMember}));

        // Problem 3
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        chargeFine(s, 100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(s.getFineHistory()));

        // Problem 4
        System.out.println(batchPrint(new LibraryMember[]{
                new LibraryMember("LB55", 3),
                new StudentMember("STU6", 3, "ECE")
        }));

        // Problem 5
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.getMemberNumber());
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(isValidRenewalCode("R12A"));
        System.out.println(isValidRenewalCode("R1A"));
        System.out.println(isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        System.out.println(processNightlyAudit(new LibraryMember[]{
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        }));
    }

    /**
     * chargeFine is protected, so this helper reaches it the same way the
     * library's own fine-processing code would, from inside the hierarchy.
     *
     * @param member the membership to fine
     * @param amount the undiscounted fine
     */
    static void chargeFine(LibraryMember member, int amount) {
        member.chargeFine(amount);
    }
}