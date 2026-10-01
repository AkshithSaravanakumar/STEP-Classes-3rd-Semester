package gym;

/**
 * Annual plan: twelve months with 25% off.
 */
public class AnnualPlan implements MembershipPlan {

    public static final double DISCOUNT = 0.25;

    @Override
    public String getPlanName() {
        return "Annual";
    }

    @Override
    public int getMonths() {
        return 12;
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT;
    }
}