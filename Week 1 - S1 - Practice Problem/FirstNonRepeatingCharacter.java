import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    /**
     * Finds the first non-repeating character in the given text.
     *
     * @param text input string to scan
     * @return the first unique character, or '\0' if none exists
     */
    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];

        // Step 1: Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 256) {
                frequency[ch]++;
            }
        }

        // Step 2: Scan left to right to find the first character with frequency 1
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch < 256 && frequency[ch] == 1) {
                return ch;
            }
        }

        // Return '\0' if no non-repeating character found
        return '\0';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Unique Letter Hunt Mini-Game ===");
        System.out.print("Enter a word or sentence: ");
        String input = scanner.nextLine();

        char result = findFirstNonRepeatingChar(input);

        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        scanner.close();
    }
}
