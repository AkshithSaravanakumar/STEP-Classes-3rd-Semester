import java.util.*;

public class StopWordFilteredWordFrequencyReport {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "the", "was", "and", "a", "is", "of", "in"
    ));

    /**
     * Normalizes feedback, filters stop words, counts frequencies, and prints words
     * sorted by count in descending order.
     *
     * @param feedback paragraph of feedback text
     */
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        // Normalize: lowercase and strip punctuation using replace()
        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "")
                .replace("\"", "")
                .replace("'", "");

        String[] words = cleaned.split("\\s+");
        Map<String, Integer> frequencyMap = new LinkedHashMap<>();

        for (String word : words) {
            String trimmedWord = word.trim();
            if (trimmedWord.isEmpty() || STOP_WORDS.contains(trimmedWord)) {
                continue;
            }
            frequencyMap.put(trimmedWord, frequencyMap.getOrDefault(trimmedWord, 0) + 1);
        }

        // Sort entries by count in descending order
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Stop-Word-Filtered Word Frequency Report ===");
        System.out.print("Enter feedback paragraph: ");
        String feedback = scanner.nextLine();

        printFilteredWordFrequency(feedback);

        scanner.close();
    }
}
