package com.pageturner.core;

/**
 * Genuinely immutable: every field is final, and the bookIds array is
 * defensively copied on the way in and again on the way out, so neither the
 * caller's array nor a mutated return value can ever reach the real data.
 *
 * Note: the class itself cannot be declared final, because
 * ReferenceOnlyLoanReceipt is required to extend it. Immutability is instead
 * locked down with final fields plus final methods, which a subclass can
 * neither reassign nor override.
 */
public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    /**
     * Defensive copy on the way in: later changes to the caller's array cannot
     * affect this receipt.
     *
     * @param memberId the borrowing member
     * @param bookIds  books on this receipt
     */
    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public final String getMemberId() {
        return memberId;
    }

    /**
     * Defensive copy on the way out: mutating the returned array leaves the
     * receipt untouched.
     *
     * @return a fresh copy of the book ids
     */
    public final String[] getBookIds() {
        return bookIds.clone();
    }

    /**
     * Correcting a receipt returns a brand-new object, because the issued one
     * can never change.
     *
     * @param index position of the mis-scanned book id
     * @param newId the corrected id
     * @return a new receipt with the correction applied
     */
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            return this;
        }

        String[] corrected = bookIds.clone();
        corrected[index] = newId;

        return new LoanReceipt(memberId, corrected);
    }

    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});

        // Proving the outbound copy independently of the inbound copy
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        // Proving the inbound copy: the original array is untouched too
        String[] original = new String[]{"BK-100", "BK-101"};
        LoanReceipt fromOriginal = new LoanReceipt("LIB-8842", original);
        original[0] = "MUTATED";
        System.out.println(fromOriginal.getBookIds()[0]);

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(java.util.Arrays.toString(r.getBookIds()));
        System.out.println(java.util.Arrays.toString(corrected.getBookIds()));
    }
}