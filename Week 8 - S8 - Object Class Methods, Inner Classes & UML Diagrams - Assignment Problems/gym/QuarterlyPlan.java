package gym;

/**
 * Quarterly plan: three months with 10% off, so ₹2,700 instead of ₹3,000.
 */
public class QuarterlyPlan implements MembershipPlan {

    public static final double DISCOUNT = 0.10;

    @Override
    public String getPlanName() {
        return "Quarterly";
    }

    @Override
    public int getMonths() {
        return 3;
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT;
    }
}