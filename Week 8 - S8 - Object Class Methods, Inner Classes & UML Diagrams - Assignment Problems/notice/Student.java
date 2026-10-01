package notice;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A student belongs to one department and chooses one or more preferred
 * channels. The channel set is held as a collection rather than a list of
 * booleans, so the delivery loop needs no per-channel conditionals.
 *
 * UML:
 * <pre>
 * Student
 *  - studentId : String
 *  - name : String
 *  - department : String
 *  - email : String
 *  - phone : String
 *  - preferredChannels : Set&lt;NotificationChannel&gt;
 *  + getDepartment() : String
 *  + getPreferredChannels() : Set&lt;NotificationChannel&gt;
 * </pre>
 *
 * Student "1" *-- "1..*" NotificationChannel (composition: the channels belong
 * to this student's preference set)
 * Student "*" -- "1" Department (by name)
 */
public class Student {

    private final String studentId;
    private final String name;
    private final String department;
    private final String email;
    private final String phone;

    private final Set<NotificationChannel> preferredChannels = new LinkedHashSet<>();

    /**
     * @param studentId the student's unique id
     * @param name the student's display name
     * @param department the student's department, e.g. CSE
     * @param email the student's email address
     * @param phone the student's phone number
     * @param channels the channels this student prefers, at least one
     */
    public Student(String studentId, String name, String department, String email, String phone,
                   NotificationChannel... channels) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        for (NotificationChannel channel : channels) {
            this.preferredChannels.add(channel);
        }
        if (this.preferredChannels.isEmpty()) {
            throw new IllegalArgumentException("A student must prefer at least one channel.");
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * @return the channels this student wants notices on
     */
    public Set<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }

    /**
     * @param department the department to match
     * @return true when this student belongs to that department
     */
    public boolean isInDepartment(String department) {
        return this.department.equalsIgnoreCase(department);
    }

    /**
     * @param targets the target departments
     * @return true when this student is in any of them
     */
    public boolean isInAnyDepartment(Collection<String> targets) {
        for (String target : targets) {
            if (isInDepartment(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}