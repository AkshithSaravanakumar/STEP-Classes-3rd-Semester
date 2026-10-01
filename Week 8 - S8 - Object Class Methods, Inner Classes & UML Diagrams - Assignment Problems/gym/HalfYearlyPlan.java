package gym;

/**
 * Half-yearly plan, added to show the point of the exercise: the desk and
 * membership are written against {@link MembershipPlan}, so this class
 * appearing later required no change to either.
 */
public class HalfYearlyPlan implements MembershipPlan {

    public static final double DISCOUNT = 0.20;

    @Override
    public String getPlanName() {
        return "Half-Yearly";
    }

    @Override
    public int getMonths() {
        return 6;
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT;
    }
}