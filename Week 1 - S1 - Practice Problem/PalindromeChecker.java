import java.util.Scanner;

public class PalindromeChecker {

    /**
     * Iterative approach: Compares characters from both ends moving towards the center.
     */
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Recursive approach: Recursively compares the first and last characters,
     * shrinking the substring in each recursive call.
     */
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    /**
     * Array reversal approach: Converts string to a char array, reverses it,
     * and compares with original characters.
     */
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }

    private static String formatResult(boolean isPal) {
        return isPal ? "Palindrome" : "Not Palindrome";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== QA Text Verification Toolkit: Palindrome Checker ===");
        System.out.print("Enter text to check: ");
        String input = scanner.nextLine();

        boolean iterative = isPalindromeIterative(input);
        boolean recursive = isPalindromeRecursive(input);
        boolean arrayReversal = isPalindromeArrayReversal(input);

        System.out.println("Iterative: " + formatResult(iterative) +
                " | Recursive: " + formatResult(recursive) +
                " | Array Reversal: " + formatResult(arrayReversal));

        scanner.close();
    }
}
