package com.cinehub.checker;

public class AccessChecker {

    private static final String ALLOWED = "ALLOWED";
    private static final String DENIED = "DENIED";

    /**
     * Four modifiers against the access contexts, decided purely from the real
     * Java visibility rules.
     *
     * private  -> same class only
     * default  -> same package
     * protected-> same package, plus a subclass in another package accessing
     *             the field through its OWN type
     * public   -> everywhere
     */
    static String classifyAccess(String fieldModifier, String accessorContext) {
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
            // The subclass case: legal only through the subclass's own type
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
     * Runs a batch of attempts and counts how many were allowed or denied.
     *
     * @param attempts pairs of {fieldModifier, accessorContext}
     * @return summary line
     */
    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {
            if (ALLOWED.equals(classifyAccess(attempts[i][0], attempts[i][1]))) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));

        // The five contexts, now that the subclass cases are handled
        String[] modifiers = {"private", "default", "protected", "public"};
        String[] contexts = {
                "SAME_CLASS",
                "SAME_PACKAGE",
                "DIFFERENT_PACKAGE",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
        };

        System.out.println();
        System.out.printf("%-10s", "modifier");
        for (int c = 0; c < contexts.length; c++) {
            System.out.printf("%-40s", contexts[c]);
        }
        System.out.println();

        for (int m = 0; m < modifiers.length; m++) {
            System.out.printf("%-10s", modifiers[m]);
            for (int c = 0; c < contexts.length; c++) {
                System.out.printf("%-40s", classifyAccess(modifiers[m], contexts[c]));
            }
            System.out.println();
        }
    }
}