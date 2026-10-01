import java.util.Scanner;

public class BookInventory {

    private String title;
    private String author;
    private int copiesAvailable;

    /**
     * Constructor that sets all three fields, replacing the parallel arrays
     * for titles, authors and copy counts.
     *
     * @param title           book title
     * @param author          book author
     * @param copiesAvailable number of copies available
     */
    public BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    /**
     * Instance method that prints one formatted inventory line.
     */
    public void printEntry() {
        System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of book entries: ");
        int count = Integer.parseInt(scanner.nextLine().trim());

        BookInventory[] inventory = new BookInventory[count];

        for (int i = 0; i < count; i++) {
            System.out.print("Enter book entry (Title, Author, Copies): ");
            String[] fields = scanner.nextLine().trim().split(",");

            String title = fields[0].trim();
            String author = fields[1].trim();
            int copiesAvailable = Integer.parseInt(fields[2].trim());

            inventory[i] = new BookInventory(title, author, copiesAvailable);
        }

        for (BookInventory entry : inventory) {
            entry.printEntry();
        }

        scanner.close();
    }
}