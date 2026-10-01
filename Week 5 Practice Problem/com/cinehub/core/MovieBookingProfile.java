package com.cinehub.core;

/**
 * A fully compliant JavaBean: public no-argument constructor plus getX/setX
 * pairs, and isX for booleans. The otp property is write-only, so no getter
 * for it exists anywhere on this class.
 */
public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    /**
     * Public no-argument constructor required by bean frameworks.
     */
    public MovieBookingProfile() {
    }

    /**
     * Convenience constructor for the common case. Chains to the no-argument
     * constructor via this() so the default setup is not repeated.
     *
     * @param name customer name
     */
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
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
     * @return whether the booking is confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Write-only property. The OTP can be set but never read back, in any
     * form.
     *
     * @param otp 4-6 digit numeric one-time password
     */
    public void setOtp(String otp) {
        if (otp == null || !otp.matches("\\d{4,6}")) {
            System.out.println("OTP rejected: expected 4 to 6 digits");
            return;
        }

        this.otp = otp;
        System.out.println("OTP accepted");
    }

    public static void main(String[] args) {
        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");

        System.out.println(p.getName());

        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471");
        p.setOtp("12");
    }
}