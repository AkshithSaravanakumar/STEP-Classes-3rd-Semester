import java.util.Scanner;

public class LibraryIsbnValidator {

    /**
     * Normalizes the raw input: trims spaces and converts only the first 3 characters to uppercase.
     *
     * @param raw the raw input string
     * @return normalized string
     */
    public static String normalizeCode(String raw) {
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
     * Validates the 13-character code (3 letters + 4 digits + 6 digits) and formats it.
     * Uses Character.isLetter() and Character.isDigit() in a loop without regex.
     *
     * @param code the normalized code
     * @return formatted display string or error message
     */
    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: year and catalog must be 10 digits";
            }
        }

        // Valid code: build formatted line using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(code.substring(0, 3)).append("] ");
        sb.append("YEAR: ").append(code.substring(3, 7)).append(" | ");
        sb.append("CATALOG: ").append(code.substring(7));

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ISBN-style code: ");
        String rawInput = scanner.nextLine();

        String normalized = normalizeCode(rawInput);
        String result = validateAndFormat(normalized);

        System.out.println(result);

        scanner.close();
    }
}
