package gym;

/**
 * Monthly plan: one month at full price, no discount.
 */
public class MonthlyPlan implements MembershipPlan {

    @Override
    public String getPlanName() {
        return "Monthly";
    }

    @Override
    public int getMonths() {
        return 1;
    }

    @Override
    public double getDiscountRate() {
        return 0.0;
    }
}