/**
 * Simple single-inheritance subclass. memberId and monthlyFee are forwarded to
 * the parent via super(...) rather than being redeclared here.
 */
public class PremiumMember extends GymMember {

    private String trainerName;

    /**
     * @param memberId    the membership id
     * @param monthlyFee  the membership's monthly fee
     * @param trainerName the member's assigned trainer
     */
    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    /**
     * @param monthlyFee  the membership's monthly fee
     * @param trainerName the member's assigned trainer
     */
    public PremiumMember(int monthlyFee, String trainerName) {
        super(monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    /**
     * Every late fee is halved before it reaches the parent, so the discount
     * applies to the recorded amount too. The parent's own logic does the
     * recording, which is why there is no second recording step here.
     *
     * @param amount the undiscounted fee
     */
    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName
                + " | Sessions: " + getSessionsAttended();
    }
}