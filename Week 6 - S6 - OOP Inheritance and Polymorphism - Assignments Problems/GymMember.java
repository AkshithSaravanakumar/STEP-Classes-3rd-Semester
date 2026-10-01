public class GymMember {

    private static final int MAX_LATE_FEES = 10;
    private static final int FIRST_MEMBERSHIP_NUMBER = 2001;

    private static int membersEnrolled;

    private final String membershipNumber;
    private final int monthlyFee;

    private String memberId;
    private int sessionsAttended;
    private int feesPaid;
    private String lastPaymentMode;

    private final int[] lateFeeHistory = new int[MAX_LATE_FEES];
    private int lateFeeCount;

    /**
     * Assigns the shared, never-reassignable membership number from a static
     * counter that ticks once per object.
     *
     * @param monthlyFee the membership's monthly fee
     */
    public GymMember(int monthlyFee) {
        this("AUTO", monthlyFee);
    }

    /**
     * The one place memberId validation lives. A blank, whitespace-only, or
     * too-short id is refused outright rather than creating a half-valid
     * membership.
     *
     * @param memberId   the membership id
     * @param monthlyFee the membership's monthly fee
     */
    public GymMember(String memberId, int monthlyFee) {
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }

        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        membersEnrolled++;
        this.membershipNumber = "GYM-"
                + (FIRST_MEMBERSHIP_NUMBER + membersEnrolled - 1);
        this.memberId = memberId.trim();
        this.monthlyFee = monthlyFee;
    }

    public final String getMembershipNumber() {
        return membershipNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    /**
     * Records one attended session.
     */
    public void attendSession() {
        sessionsAttended++;
    }

    /**
     * Pays a flat amount toward the fee.
     *
     * @param amount the amount paid
     */
    public void payFee(int amount) {
        feesPaid += amount;
    }

    /**
     * Records the payment mode and then delegates to the flat-amount version,
     * so the running total is never duplicated.
     *
     * @param amount the amount paid
     * @param mode   how the payment was made
     */
    public void payFee(int amount, String mode) {
        this.lastPaymentMode = mode;
        payFee(amount);
    }

    /**
     * Records a late fee into the private history. Premium members reuse this
     * by calling super after halving the amount, which is why the recording
     * logic lives only here.
     *
     * @param amount the fee to charge
     */
    protected void chargeLateFee(int amount) {
        if (lateFeeCount >= MAX_LATE_FEES) {
            return;
        }

        lateFeeHistory[lateFeeCount] = amount;
        lateFeeCount++;
    }

    /**
     * A fresh copy every time, so outside code can never reach the real
     * internal array.
     *
     * @return the recorded late fees
     */
    public int[] getLateFeeHistory() {
        int[] copy = new int[lateFeeCount];
        System.arraycopy(lateFeeHistory, 0, copy, 0, lateFeeCount);
        return copy;
    }

    /**
     * @return the sum of every late fee ever charged to this membership
     */
    public int getTotalLateFees() {
        int total = 0;

        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }

    /**
     * Describes this membership. Every subclass overrides it, so a single call
     * through a GymMember reference produces the right line.
     *
     * @return this membership's own description
     */
    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    /**
     * Checks the exact format "G" + two digits + one uppercase letter, using
     * charAt() and the Character checks rather than a regular expression.
     *
     * @param code the referral code entered by the member
     * @return whether the code is well formed
     */
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'G') {
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
     * Attempts to build one member per entry and counts the rejections instead
     * of stopping at the first bad id. No pre-validation happens here: the
     * constructor is the only place the rule lives.
     *
     * @param memberIds  ids to sign up
     * @param monthlyFee fee given to each new member
     * @return sign-up summary
     */
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {
            try {
                new GymMember(memberIds[i], monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException rejectedId) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    /**
     * Decides the generation purely through instanceof checks. No class carries
     * a manual "type" field.
     *
     * @param member the membership to classify
     * @return where it sits in the hierarchy
     */
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof PremiumMember) {
            return "Direct subclass (2 generations deep)";
        }

        return "Base class (1 generation)";
    }

    /**
     * Sums attended sessions across a mixed list. No type check is needed: the
     * call on a GymMember reference already runs each object's own
     * getSessionsAttended().
     *
     * @param members any mix of the four membership types
     * @return the total sessions attended
     */
    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;

        for (int i = 0; i < members.length; i++) {
            total += members[i].getSessionsAttended();
        }

        return total;
    }

    /**
     * Builds the whole announcement as one piece of text. displayInfo() is
     * called polymorphically, and the trainer is reached through an instanceof
     * guard placed immediately before the downcast, so the cast is never
     * attempted on a membership that isn't premium.
     *
     * @param members the memberships to announce
     * @return the assembled announcement
     */
    public static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();

        for (int i = 0; i < members.length; i++) {
            GymMember member = members[i];

            // Called polymorphically: no instanceof chain decides what to print
            announcement.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                // Check first, cast only inside the branch that already passed
                PremiumMember premium = (PremiumMember) member;
                announcement.append(" [Trainer via downcast: ")
                        .append(premium.getTrainerName()).append("]");
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }

    /**
     * Settles the week's check-ins. Group class members are separated from
     * regular ones via instanceof, and a null placeholder entry is counted and
     * skipped rather than crashing the run.
     *
     * @param members the batch to settle
     * @return settlement summary
     */
    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (members[i] instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    /**
     * chargeLateFee is protected, so this helper reaches it the way the gym's
     * own fee-processing code would, from inside the hierarchy.
     *
     * @param member the membership to charge
     * @param amount the fee amount
     */
    static void chargeLateFee(GymMember member, int amount) {
        member.chargeLateFee(amount);
    }

    public static void main(String[] args) {
        // Problem 1
        try {
            new GymMember("GM1", 1000);
        } catch (IllegalArgumentException rejected) {
            System.out.println(rejected.getMessage());
        }

        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended());

        System.out.println(signUpBatch(
                new String[]{"MEM1", "GM1", "MEM2", "  ", "MEM3"}, 1000));

        // Problem 2
        PremiumMember premiumMember = new PremiumMember("MEM02", 2000, "Coach Riya");
        EliteMember eliteMember =
                new EliteMember("MEM03", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClassMember =
                new GroupClassMember("MEM04", 1500, "Zumba");

        System.out.println(new GymMember("MEM05", 1000).displayInfo());
        System.out.println(premiumMember.displayInfo());
        System.out.println(eliteMember.displayInfo());
        System.out.println(groupClassMember.displayInfo());

        System.out.println(classifyGeneration(eliteMember));
        System.out.println(classifyGeneration(groupClassMember));

        for (int i = 0; i < 3; i++) {
            premiumMember.attendSession();
        }
        for (int i = 0; i < 2; i++) {
            eliteMember.attendSession();
        }
        for (int i = 0; i < 4; i++) {
            groupClassMember.attendSession();
        }

        System.out.println(getTotalSessionsAttended(
                new GymMember[]{premiumMember, eliteMember, groupClassMember}));

        // Problem 3
        PremiumMember feeMember = new PremiumMember("MEM07", 2000, "Coach Riya");
        chargeLateFee(feeMember, 200);
        System.out.println(feeMember.getTotalLateFees());

        int[] history = feeMember.getLateFeeHistory();
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(feeMember.getLateFeeHistory()));

        // Problem 4
        System.out.println(batchPrint(new GymMember[]{
                new GymMember("MEM08", 1000),
                new PremiumMember("MEM09", 2000, "Coach Riya")
        }));

        // Problem 5
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.getMembershipNumber());
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(isValidReferralCode("G45B"));
        System.out.println(isValidReferralCode("G4B"));
        System.out.println(isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        System.out.println(processWeeklyCheckIn(new GymMember[]{
                new GroupClassMember(1500, "Zumba"),
                null,
                new GymMember(1000)
        }));
    }
}