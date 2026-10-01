package com.pageturner.core;

/**
 * Each field carries the access level that matches who genuinely needs it:
 *
 * membershipPin -> private   : only LibraryMember itself
 * branchCode    -> default   : only classes in this package
 * finesOwed     -> protected : same package, plus a subclass elsewhere
 * displayName   -> public    : reachable from anywhere
 */
public class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode, double finesOwed,
                         String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    /**
     * private field, read only from inside this class.
     *
     * @return the membership PIN
     */
    public String readPinInternally() {
        return membershipPin;
    }

    /**
     * protected field, read from inside this class. The cross-package and
     * subclass behaviour is exercised in Problem 2.
     *
     * @return the fines owed
     */
    public double getFinesOwed() {
        return finesOwed;
    }

    /**
     * default field, read from inside this package.
     *
     * @return the branch code
     */
    public String getBranchCode() {
        return branchCode;
    }

    public static void main(String[] args) {
        LibraryMember member =
                new LibraryMember("4417", "CHN-01", 250.0, "Priya Nair");

        System.out.println("Display Name: " + member.displayName);
        System.out.println("Branch Code: " + member.getBranchCode());
        System.out.println("Fines Owed: Rs " + member.getFinesOwed());
        System.out.println("Pin readable internally: " + member.readPinInternally());
    }
}