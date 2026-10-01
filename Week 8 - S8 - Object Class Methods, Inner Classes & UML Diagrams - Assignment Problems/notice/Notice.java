package notice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A notice has a title and one or more target departments. Validation lives
 * here rather than in the board, because a notice that has no target department
 * is not a notice at all.
 *
 * UML:
 * <pre>
 * Notice
 *  - title : String
 *  - targetDepartments : Set&lt;String&gt;
 *  + getTitle() : String
 *  + getTargetDepartments() : Set&lt;String&gt;
 *  + getDepartmentsLabel() : String
 * </pre>
 *
 * Notice "1" -- "1..*" Department (by name)
 */
public class Notice {

    private final String title;
    private final Set<String> targetDepartments = new LinkedHashSet<>();

    /**
     * @param title the notice title, must not be blank
     * @param targetDepartments the departments it targets, at least one
     * @throws IllegalArgumentException when the title is blank or no department
     *         is targeted
     */
    public Notice(String title, String... targetDepartments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("A notice title is required.");
        }
        for (String department : targetDepartments) {
            if (department != null && !department.trim().isEmpty()) {
                this.targetDepartments.add(department.trim().toUpperCase());
            }
        }
        if (this.targetDepartments.isEmpty()) {
            throw new IllegalArgumentException("At least one target department is required.");
        }
        this.title = title.trim();
    }

    public String getTitle() {
        return title;
    }

    /**
     * @return the departments this notice targets
     */
    public Set<String> getTargetDepartments() {
        return Collections.unmodifiableSet(targetDepartments);
    }

    /**
     * @return the targets joined with ", " for printed messages
     */
    public String getDepartmentsLabel() {
        return String.join(", ", targetDepartments);
    }

    /**
     * @return the targets as a list, for convenient iteration
     */
    public List<String> getTargetDepartmentList() {
        return new ArrayList<>(targetDepartments);
    }

    @Override
    public String toString() {
        return title;
    }
}