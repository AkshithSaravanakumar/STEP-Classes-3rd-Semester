/**
 * Hierarchical inheritance: extends GymMember directly, entirely independent of
 * PremiumMember.
 */
public class GroupClassMember extends GymMember {

    private String className;

    /**
     * @param memberId   the membership id
     * @param monthlyFee the membership's monthly fee
     * @param className  the group class the member attends
     */
    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    /**
     * @param monthlyFee the membership's monthly fee
     * @param className  the group class the member attends
     */
    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className
                + " | Sessions: " + getSessionsAttended();
    }
}