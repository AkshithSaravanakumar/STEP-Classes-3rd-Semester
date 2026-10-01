package com.pageturner.core;

/**
 * For books that never leave the building. Settles differently at night from a
 * regular loan, and is just as immutable as its parent.
 */
public class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    /**
     * @param memberId   the borrowing member
     * @param bookIds    books consulted in the building
     * @param roomNumber the reading room they were used in
     */
    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public final String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= getBookIds().length) {
            return this;
        }

        String[] corrected = getBookIds();
        corrected[index] = newId;

        return new ReferenceOnlyLoanReceipt(getMemberId(), corrected, roomNumber);
    }

    public static void main(String[] args) {
        ReferenceOnlyLoanReceipt r =
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3");

        System.out.println(r.getMemberId() + " | Room: " + r.getRoomNumber()
                + " | Books: " + java.util.Arrays.toString(r.getBookIds()));
    }
}