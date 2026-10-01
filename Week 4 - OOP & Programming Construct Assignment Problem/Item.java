public class Item {

    private String itemName;
    private int stock;

    /**
     * The parameter names match the field names exactly, so both clashes are
     * resolved with the this keyword.
     *
     * @param itemName name of the item
     * @param stock    starting stock level
     */
    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    public String getItemName() {
        return itemName;
    }

    public int getStock() {
        return stock;
    }

    /**
     * Adds the restock quantity to the current stock. The parameter is named
     * stock exactly like the field, so this.stock is the field and stock is the
     * argument.
     *
     * @param stock quantity to add
     */
    public void restock(int stock) {
        this.stock = this.stock + stock;
    }

    /**
     * Prints the item's name and final stock.
     */
    public void printFinalStock() {
        System.out.println(itemName + " | Final Stock: " + stock);
    }

    public static void main(String[] args) {
        String[] itemNames = {"Samosa", "Tea Powder", "Bread", "Biscuit Packs"};
        int[] startingStock = {15, 40, 8, 25};

        Item[] shelf = new Item[itemNames.length];

        for (int i = 0; i < shelf.length; i++) {
            shelf[i] = new Item(itemNames[i], startingStock[i]);
        }

        // The same restock quantity is applied to every item
        for (int i = 0; i < shelf.length; i++) {
            shelf[i].restock(20);
        }

        for (int i = 0; i < shelf.length; i++) {
            shelf[i].printFinalStock();
        }
    }
}