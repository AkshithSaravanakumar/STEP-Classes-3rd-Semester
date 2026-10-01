package com.pageturner.core;

/**
 * Reconciles the day's receipts. Reference-only loans settle differently from
 * regular loans via an instanceof check, and a null placeholder from the
 * scanner feed is counted and skipped rather than crashing the whole run.
 *
 * Runs in a single pass with O(1) extra space: only a handful of counters.
 */
public class CirculationLedger {

    private static String branchCode;

    /**
     * One-time, class-level setup: runs exactly once when the class is loaded.
     */
    static {
        branchCode = "CHN-01";
        System.out.println("Ledger ready for branch " + branchCode);
    }

    public static String getBranchCode() {
        return branchCode;
    }

    /**
     * Settles a batch of receipts for the night.
     *
     * @param receipts the day's receipts, possibly containing nulls
     * @return settlement summary
     */
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {
            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
                ReferenceOnlyLoanReceipt ref = (ReferenceOnlyLoanReceipt) receipts[i];
                System.out.println("Reference-only | " + ref.getMemberId()
                        + " | Room " + ref.getRoomNumber()
                        + " | Books " + ref.getBookIds().length);
            } else {
                regular++;
                System.out.println("Regular loan | " + receipts[i].getMemberId()
                        + " | Books " + receipts[i].getBookIds().length);
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}