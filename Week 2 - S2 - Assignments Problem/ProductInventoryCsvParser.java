import java.util.Scanner;

public class ProductInventoryCsvParser {

    /**
     * Parses a CSV inventory record line and displays formatted product details.
     *
     * @param csvLine comma-separated record line
     */
    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String quantity = fields[2].trim();

        System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter CSV line: ");
        String line = scanner.nextLine();

        parseInventoryRecord(line);

        scanner.close();
    }
}
