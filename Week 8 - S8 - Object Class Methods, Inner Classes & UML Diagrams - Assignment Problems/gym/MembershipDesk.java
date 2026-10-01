package gym;

import java.util.ArrayList;
import java.util.List;

/**
 * Membership desk. It depends on the {@link MembershipPlan} interface, so a new
 * plan plugs in without any change here.
 */
public class MembershipDesk {

    private final List<Membership> memberships = new ArrayList<>();

    /**
     * @param member who is buying
     * @param plan the plan being bought
     * @return the new membership, already active
     */
    public Membership buy(Member member, MembershipPlan plan) {
        Membership membership = new Membership(member, plan);
        memberships.add(membership);
        System.out.println(plan.getPlanName() + " membership created for " + member.getName() + ".");
        System.out.println("Fee: ₹" + String.format("%.2f", membership.getFee()) + ".");
        System.out.println("Status: " + membership.getStatus() + ".");
        return membership;
    }

    /**
     * @return every membership sold so far
     */
    public List<Membership> getMemberships() {
        return new ArrayList<>(memberships);
    }

    public static void main(String[] args) {
        MembershipDesk desk = new MembershipDesk();

        Member asha = new Member("M1", "Asha");
        Member ravi = new Member("M2", "Ravi");

        Membership ashaMembership = desk.buy(asha, new QuarterlyPlan());
        Membership raviMembership = desk.buy(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        raviMembership.expire();
        raviMembership.freeze();
    }
}