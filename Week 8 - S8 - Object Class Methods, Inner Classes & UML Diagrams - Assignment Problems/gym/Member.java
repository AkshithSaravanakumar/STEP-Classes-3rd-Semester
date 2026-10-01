package gym;

/**
 * A gym member.
 *
 * UML: Member "1" -- "1..*" Membership
 */
public class Member {

    private final String memberId;
    private final String name;

    /**
     * @param memberId the member's unique id
     * @param name the member's display name
     */
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}