public class MembershipCard {

    private static String libraryName;
    private static String validUntil;

    /**
     * Static initialisation block: runs exactly once when the class is first
     * loaded, no matter how many cards are issued afterwards.
     */
    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    private String studentName;

    /**
     * @param studentName name of the student the card is issued to
     */
    public MembershipCard(String studentName) {
        this.studentName = studentName;
        System.out.println("Membership card issued: " + studentName);
    }

    public String getStudentName() {
        return studentName;
    }

    public static String getLibraryName() {
        return libraryName;
    }

    public static String getValidUntil() {
        return validUntil;
    }

    public static void printCardInfo() {
        System.out.println("Library: " + libraryName);
        System.out.println("Valid until: " + validUntil);
    }

    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        MembershipCard[] cards = new MembershipCard[names.length];

        // "Library info loaded" still prints only once, no matter the loop count
        for (int i = 0; i < names.length; i++) {
            cards[i] = new MembershipCard(names[i]);
        }
    }
}