import java.util.Scanner;

public class BankTransactionReferenceValidator {

    /**
     * Normalizes the reference by trimming spaces and converting only the first 3 characters to uppercase.
     *
     * @param raw the raw input reference
     * @return normalized reference string
     */
    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() <= 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /**
     * Validates and formats the 14-character transaction reference code without using regex.
     *
     * @param reference normalized reference code
     * @return formatted line or specific error message
     */
    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: reference code must be exactly 14 characters";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Validate remaining 11 characters are digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: date and sequence number must be 11 digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String dd = reference.substring(3, 5);
        String mm = reference.substring(5, 7);
        String yy = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] ");
        sb.append("DATE: ").append(dd).append("/").append(mm).append("/").append(yy);
        sb.append(" | SEQ: ").append(seq);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");
        String rawInput = scanner.nextLine();

        String normalized = normalizeReference(rawInput);
        String result = validateAndFormat(normalized);

        System.out.println(result);

        scanner.close();
    }
}
