package gym;

/**
 * A membership owns two independent concerns and keeps them apart: the fee is
 * delegated to the plan, and the status is managed entirely here with no public
 * setter, so every status change is validated.
 *
 * UML:
 * <pre>
 * Membership
 *  - member : Member
 *  - plan : MembershipPlan
 *  - fee : double
 *  - status : MembershipStatus
 *  + getStatus() : MembershipStatus
 *  + checkIn() : boolean
 *  + freeze() : boolean
 *  + unfreeze() : boolean
 *  + expire() : boolean
 * </pre>
 *
 * Membership "1" *-- "1" MembershipPlan (composition: a membership has exactly
 * one plan for its whole life)
 * Membership "*" -- "1" Member
 */
public class Membership {

    private final Member member;
    private final MembershipPlan plan;
    private final double fee;

    private MembershipStatus status = MembershipStatus.ACTIVE;

    /**
     * @param member who owns this membership
     * @param plan the plan bought
     */
    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
    }

    public Member getMember() {
        return member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public double getFee() {
        return fee;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    /**
     * @return true when the membership permits check-in
     */
    public boolean isActive() {
        return status.allowsCheckIn();
    }

    /**
     * @return true when this member may check in now
     */
    public boolean checkIn() {
        if (!status.allowsCheckIn()) {
            System.out.println("Check-in denied: " + member.getName()
                    + "'s membership is " + status + ".");
            return false;
        }
        System.out.println(member.getName() + " checked in successfully.");
        return true;
    }

    /**
     * @return true when the freeze was allowed
     */
    public boolean freeze() {
        if (!status.canFreeze()) {
            System.out.println("Cannot freeze an " + status + " membership.");
            return false;
        }
        status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen.");
        System.out.println("Status: " + status + ".");
        return true;
    }

    /**
     * @return true when the unfreeze was allowed
     */
    public boolean unfreeze() {
        if (!status.canUnfreeze()) {
            System.out.println("Cannot unfreeze an " + status + " membership.");
            return false;
        }
        status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen.");
        System.out.println("Status: " + status + ".");
        return true;
    }

    /**
     * @return true when the expiry was allowed
     */
    public boolean expire() {
        if (!status.canExpire()) {
            System.out.println("Cannot expire an " + status + " membership.");
            return false;
        }
        status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired.");
        System.out.println("Status: " + status + ".");
        return true;
    }
}