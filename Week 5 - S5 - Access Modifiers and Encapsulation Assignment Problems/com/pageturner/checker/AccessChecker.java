package com.pageturner.checker;

/**
 * Decides an access attempt purely from the real Java visibility rules,
 * never from hardcoded field names.
 */
public class AccessChecker {

    private static final String ALLOWED = "ALLOWED";
    private static final String DENIED = "DENIED";

    private static final String[] MODIFIERS = {"private", "default", "protected", "public"};

    /**
     * private   -> its own class only
     * default   -> its own package
     * protected -> its own package, plus a subclass in another package when the
     *             reference's declared type is that subclass (OWN_TYPE)
     * public    -> everywhere
     *
     * @param fieldModifier  one of private / default / protected / public
     * @param accessorContext where the access is attempted from
     * @return ALLOWED or DENIED
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("private".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) ? ALLOWED : DENIED;
        }

        if ("default".equals(fieldModifier)) {
            if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                return ALLOWED;
            }
            return DENIED;
        }

        if ("protected".equals(fieldModifier)) {
            if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                return ALLOWED;
            }
            if ("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                return ALLOWED;
            }
            return DENIED;
        }

        if ("public".equals(fieldModifier)) {
            return ALLOWED;
        }

        return DENIED;
    }

    /**
     * Groups the batch by modifier instead of returning one flat total. The
     * grouping is built around all four modifiers, so a modifier with zero
     * attempts still appears with a 0 / 0 line instead of breaking the report.
     *
     * @param attempts pairs of {fieldModifier, accessorContext}
     * @return one summary segment per modifier, joined by " | "
     */
    public static String summarizeByModifier(String[][] attempts) {
        int[] allowed = new int[MODIFIERS.length];
        int[] denied = new int[MODIFIERS.length];

        for (int i = 0; i < attempts.length; i++) {
            for (int m = 0; m < MODIFIERS.length; m++) {
                if (!MODIFIERS[m].equals(attempts[i][0])) {
                    continue;
                }

                if (ALLOWED.equals(classifyAccess(attempts[i][0], attempts[i][1]))) {
                    allowed[m]++;
                } else {
                    denied[m]++;
                }
                break;
            }
        }

        StringBuilder summary = new StringBuilder();

        for (int m = 0; m < MODIFIERS.length; m++) {
            if (m > 0) {
                summary.append(" | ");
            }

            summary.append(MODIFIERS[m]).append(": ")
                    .append(allowed[m]).append(" allowed")
                    .append(" / ")
                    .append(denied[m]).append(" denied");
        }

        return summary.toString();
    }

    /**
     * Scans strictly in order and stops the moment the first denial is found,
     * without processing anything after it.
     *
     * @param attempts pairs of {fieldModifier, accessorContext}
     * @return a description of the first denied attempt, or "None Denied"
     */
    public static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            if (DENIED.equals(classifyAccess(attempts[i][0], attempts[i][1]))) {
                // Attempt numbers are reported 1-based
                return attempts[i][0] + " via " + attempts[i][1]
                        + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] batch = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(batch));

        String[][] subclassBatch = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(firstDeniedAttempt(subclassBatch));

        String[][] cleanBatch = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(firstDeniedAttempt(cleanBatch));
    }
}