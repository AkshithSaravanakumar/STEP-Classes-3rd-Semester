import java.util.Scanner;

public class AtmPinLengthValidator {

    /**
     * Checks if the entered PIN has exactly 4 digits using length() and if/else.
     *
     * @param pin the PIN string to validate
     */
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ATM PIN: ");
        String pin = scanner.nextLine().trim();

        checkPinLength(pin);

        scanner.close();
    }
}
