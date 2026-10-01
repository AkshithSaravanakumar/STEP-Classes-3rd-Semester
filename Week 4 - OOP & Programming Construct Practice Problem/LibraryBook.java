public class LibraryBook {

    private static final String PENDING_ISBN = "PENDING";

    private String title;
    private String isbn;

    /**
     * Constructor for a book with a confirmed ISBN.
     *
     * @param title book title
     * @param isbn  confirmed ISBN
     */
    public LibraryBook(String title, String isbn) {
        this.title = title;
        // A blank or missing ISBN must never be left as "" or null
        if (isbn == null || isbn.trim().isEmpty()) {
            this.isbn = PENDING_ISBN;
        } else {
            this.isbn = isbn.trim();
        }
    }

    /**
     * Constructor for a book with no confirmed ISBN. Chains to the two-argument
     * constructor via this(...) so the field-setting logic exists only once.
     *
     * @param title book title
     */
    public LibraryBook(String title) {
        this(title, PENDING_ISBN);
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    /**
     * Prints the entry's cataloguing status.
     */
    public void printStatus() {
        System.out.println(title + " | " + isbn + " | Catalogued: true");
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        LibraryBook[] batch = new LibraryBook[titles.length];

        // Every entry in the batch is processed and printed in a single pass
        for (int i = 0; i < titles.length; i++) {
            if (isbns[i] == null || isbns[i].trim().isEmpty()) {
                batch[i] = new LibraryBook(titles[i]);
            } else {
                batch[i] = new LibraryBook(titles[i], isbns[i]);
            }
        }

        for (int i = 0; i < batch.length; i++) {
            batch[i].printStatus();
        }
    }
}