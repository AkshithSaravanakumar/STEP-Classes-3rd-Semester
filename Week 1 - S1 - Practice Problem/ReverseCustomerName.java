import java.util.Scanner;

public class ReverseCustomerName {

    /**
     * Reverses the given customer name without modifying the original data.
     *
     * @param customerName original customer name
     * @return reversed customer name
     */
    public static String reverseCustomerName(String customerName) {
        if (customerName == null) {
            return null;
        }

        char[] chars = customerName.toCharArray();
        char[] reversed = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }

        return new String(reversed);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Customer Identity Verification System ===");
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        String reversed = reverseCustomerName(name);

        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reversed);

        scanner.close();
    }
}
