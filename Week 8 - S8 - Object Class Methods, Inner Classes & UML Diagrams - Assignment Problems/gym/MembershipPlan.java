package gym;

/**
 * A plan owns its own fee rule. The desk never branches on plan type, so a new
 * plan such as Half-Yearly is one new class.
 *
 * UML:
 * <pre>
 * MembershipPlan (interface)
 *  + getPlanName() : String
 *  + getMonths() : int
 *  + getDiscountRate() : double
 *  + calculateFee() : double
 *
 * MembershipPlan &lt;|.. MonthlyPlan
 * MembershipPlan &lt;|.. QuarterlyPlan
 * MembershipPlan &lt;|.. AnnualPlan
 * </pre>
 */
public interface MembershipPlan {

    /**
     * Base monthly rate every plan is priced from.
     */
    double BASE_MONTHLY_RATE = 1000.0;

    /**
     * @return the plan's display name
     */
    String getPlanName();

    /**
     * @return how many months the plan covers
     */
    int getMonths();

    /**
     * @return the discount applied to the undiscounted total, e.g. 0.10 for 10%
     */
    double getDiscountRate();

    /**
     * @return the fee for this plan, in rupees
     */
    default double calculateFee() {
        double gross = BASE_MONTHLY_RATE * getMonths();
        double fee = gross * (1 - getDiscountRate());
        return Math.round(fee * 100) / 100.0;
    }
}