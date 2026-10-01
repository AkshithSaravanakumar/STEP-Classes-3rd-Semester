public class IdCard {

    String name;
    int booksIssued;

    /**
     * @param name        card holder name
     * @param booksIssued number of books currently issued
     */
    public IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }

    public String getName() {
        return name;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public static void main(String[] args) {
        IdCard ravi = new IdCard("Ravi", 0);

        // Assigning one variable to another copies the reference, not the object
        IdCard duplicate = ravi;
        duplicate.booksIssued = 3;

        // A genuinely separate object with identical field values
        IdCard separate = new IdCard("Ravi", 3);

        System.out.println(ravi.name + "'s booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));
        System.out.println("separate == ravi: " + (separate == ravi));
    }
}