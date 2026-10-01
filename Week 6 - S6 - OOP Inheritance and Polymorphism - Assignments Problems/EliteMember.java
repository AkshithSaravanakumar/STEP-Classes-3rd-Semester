/**
 * Multilevel inheritance: three generations deep, GymMember -> PremiumMember ->
 * EliteMember. The extra privilege is a reserved locker.
 */
public class EliteMember extends PremiumMember {

    private String lockerNumber;

    /**
     * @param memberId     the membership id
     * @param monthlyFee   the membership's monthly fee
     * @param trainerName  the member's assigned trainer
     * @param lockerNumber the reserved locker
     */
    public EliteMember(String memberId, int monthlyFee, String trainerName,
                       String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName()
                + " | Locker: " + lockerNumber
                + " | Sessions: " + getSessionsAttended();
    }
}