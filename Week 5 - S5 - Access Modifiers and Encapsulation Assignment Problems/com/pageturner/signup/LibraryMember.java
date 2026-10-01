package com.pageturner.signup;

import java.util.Objects;

/**
 * A fully compliant JavaBean for the self-service signup form: a public
 * no-argument constructor plus getX/setX pairs, and isX for the boolean.
 *
 * Two different protective patterns sit side by side here:
 *   membershipId   -> write-once behind a setter the framework must be able to scan
 *   securityAnswer -> write-only, stored one-way, with no getter anywhere
 */
public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    /**
     * Public no-argument constructor required by bean-scanning frameworks.
     */
    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    /**
     * Write-once: a setter genuinely exists so the framework's scan succeeds,
     * but only the very first call takes effect. Every later call is silently
     * ignored.
     *
     * @param id the membership id to fix for this member
     */
    public void setMembershipId(String id) {
        if (membershipId != null) {
            return;
        }

        membershipId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Boolean properties use isX, not getX.
     *
     * @return whether this member has premium status
     */
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    /**
     * Write-only property. Only a one-way transformation is stored, and no
     * method on this class can ever retrieve the original answer again.
     *
     * @param answer the security answer used for forgot-my-card verification
     */
    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            return;
        }

        securityAnswerHash = oneWayTransform(answer);
    }

    /**
     * A deterministic, one-way transform. Not real cryptography, but enough to
     * show that the plain answer is never kept around.
     *
     * @param answer the plain-text answer
     * @return the stored transformed form
     */
    private static String oneWayTransform(String answer) {
        return "h#" + Integer.toHexString(Objects.hash(answer)) + "#" + answer.length();
    }

    /**
     * Exists only so the demo can show that the stored value is not the plain
     * answer. There is deliberately no getSecurityAnswer().
     *
     * @return a redacted marker, never the answer
     */
    public String getSecurityAnswerStatus() {
        return securityAnswerHash == null ? "not set" : "stored (write-only)";
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());
        System.out.println(m.getName());
        System.out.println(m.isPremiumMember());

        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());

        m.setSecurityAnswer("BlueMountain");
        System.out.println(m.getSecurityAnswerStatus());
    }
}