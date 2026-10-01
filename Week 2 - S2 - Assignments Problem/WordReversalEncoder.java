import java.util.Scanner;

public class WordReversalEncoder {

    /**
     * Reverses each word individually in a sentence while preserving word order.
     *
     * @param sentence input sentence with space-separated words
     * @return sentence with each word reversed
     */
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder reversedWord = new StringBuilder();

            // Reverse using a loop as specified in the problem requirements
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            result.append(reversedWord);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sentence to encode: ");
        String sentence = scanner.nextLine();

        String reversed = reverseEachWord(sentence);
        System.out.println(reversed);

        scanner.close();
    }
}
