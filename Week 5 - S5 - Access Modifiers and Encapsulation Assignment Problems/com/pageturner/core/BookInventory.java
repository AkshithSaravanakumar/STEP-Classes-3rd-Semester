package com.pageturner.core;

/**
 * copiesAvailable can only ever move through validated methods, so the count
 * can never reach -1 or exceed what the branch actually owns. The invariant
 * that always holds is
 *
 * 0 <= copiesAvailable <= copiesTotal
 *
 * and it is checked before every single transition, not just at the edges.
 */
public class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    /**
     * Rejects a nonsensical total instead of letting the object exist in an
     * invalid state.
     *
     * @param copiesTotal number of copies the branch owns
     */
    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            System.out.println("construction rejected");
            this.copiesTotal = 0;
            this.copiesAvailable = 0;
            return;
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    /**
     * Checks a copy out. Silently rejected when nothing is available, so the
     * count never goes negative.
     */
    public void checkOut() {
        if (copiesAvailable - 1 < 0) {
            return;
        }

        copiesAvailable--;
    }

    /**
     * Checks a copy back in. Silently rejected once the inventory is already at
     * full capacity, because there is nothing genuine to return.
     */
    public void checkIn() {
        if (copiesAvailable + 1 > copiesTotal) {
            return;
        }

        copiesAvailable++;
    }

    /**
     * Read-only access to the available copy count. There is no setter, so it
     * cannot be set to an arbitrary value from outside.
     *
     * @return copies currently available
     */
    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public int getCopiesTotal() {
        return copiesTotal;
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();
        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();
        System.out.println(b.getCopiesAvailable());
    }
}