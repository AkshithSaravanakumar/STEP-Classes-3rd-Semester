/**
 * Prints labels for a mixed array of items. The only thing the loop needs to
 * know about each element is that it is Printable.
 */
public class LabelPrinter {

    /**
     * @param items a mixed array of printable items
     */
    public static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println(p.printLabel());

        Invoice i = new Invoice("INV-42");
        System.out.println(i.printLabel());

        printAll(new Printable[]{ p, i });
    }
}