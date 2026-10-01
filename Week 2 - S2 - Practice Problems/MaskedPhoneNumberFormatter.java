import java.util.Scanner;

public class MaskedPhoneNumberFormatter {

    /**
     * Validates and masks a 10-digit phone number, showing XXXXXX followed by the last 4 digits.
     *
     * @param phone raw phone number string
     * @return masked phone number format "XXXXXX-XXXX" or "Invalid phone number"
     */
    public static String maskPhoneNumber(String phone) {
        if (phone == null) {
            return "Invalid phone number";
        }

        String trimmed = phone.trim();

        if (trimmed.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < trimmed.length(); i++) {
            if (!Character.isDigit(trimmed.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Build masked string using StringBuilder and insert "-" between mask and last 4 digits
        StringBuilder sb = new StringBuilder("XXXXXX");
        sb.append(trimmed.substring(6));
        sb.insert(6, "-");

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        String result = maskPhoneNumber(phone);
        System.out.println(result);

        scanner.close();
    }
}
