package com.pageturner.membership;

import com.pageturner.core.LibraryMember;

/**
 * The premium-membership module lives in a different package from
 * LibraryMember, so the only way it can read the protected finesOwed is by
 * inheriting from it.
 */
public class PremiumMember extends LibraryMember {

    private int loyaltyPoints;

    public PremiumMember(String membershipPin, String branchCode, double finesOwed,
                         String displayName, int loyaltyPoints) {
        super(membershipPin, branchCode, finesOwed, displayName);
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * OWN_TYPE case: the reference is a PremiumMember, so reading the inherited
     * protected field compiles.
     *
     * @return fines owed, read through this class's own type
     */
    public double readFinesThroughOwnType() {
        return finesOwed;
    }

    /**
     * PARENT_TYPE case: the same field reached through a LibraryMember-typed
     * reference is denied, because Java goes by the reference's declared type,
     * not the object's real type. This is why the line below does not compile:
     *
     * double fines = parentTypedRef.finesOwed;
     *
     * @param parentTypedRef a reference declared as LibraryMember
     * @return why that access would not compile
     */
    public String explainParentTypeAccess(LibraryMember parentTypedRef) {
        return "LibraryMember-typed reference to a "
                + parentTypedRef.getClass().getSimpleName()
                + ": DENIED (compile-time typing decides, not runtime typing)";
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public static void main(String[] args) {
        PremiumMember premium =
                new PremiumMember("9002", "CHN-04", 120.0, "Rahul Dev", 640);

        System.out.println("Fines via own type: Rs " + premium.readFinesThroughOwnType());

        LibraryMember parentTypedRef = premium;
        System.out.println(premium.explainParentTypeAccess(parentTypedRef));
    }
}