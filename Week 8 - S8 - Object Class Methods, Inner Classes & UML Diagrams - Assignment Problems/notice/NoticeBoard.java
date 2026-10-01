package notice;

import java.util.ArrayList;
import java.util.List;

/**
 * The notice board. It validates by asking the {@link Notice} to exist at all,
 * finds the students in the target departments, and delivers through whatever
 * channels each student prefers. It never names a concrete channel class, so a
 * new channel needs no change here.
 *
 * UML: NoticeBoard "1" -- "0..*" Student
 *        NoticeBoard ..> NotificationChannel (uses, does not own)
 */
public class NoticeBoard {

    private final List<Student> students = new ArrayList<>();

    /**
     * @param student a student to notify
     */
    public void register(Student student) {
        students.add(student);
    }

    /**
     * @return every registered student
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * @param notice the notice to look for students for
     * @return the students in any of the notice's target departments
     */
    public List<Student> findRecipients(Notice notice) {
        List<Student> recipients = new ArrayList<>();
        for (Student student : students) {
            if (student.isInAnyDepartment(notice.getTargetDepartments())) {
                recipients.add(student);
            }
        }
        return recipients;
    }

    /**
     * Posts a notice to every matching student on each of their channels.
     *
     * @param notice the notice to post
     * @return the number of deliveries made
     */
    public int post(Notice notice) {
        List<Student> recipients = findRecipients(notice);
        System.out.println("Notice '" + notice.getTitle() + "' posted to "
                + notice.getDepartmentsLabel() + ".");

        int deliveries = 0;
        for (Student student : recipients) {
            for (NotificationChannel channel : student.getPreferredChannels()) {
                channel.send(student, notice);
                deliveries++;
            }
        }
        return deliveries;
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        EmailChannel email = new EmailChannel();
        SmsChannel sms = new SmsChannel();
        AppChannel app = new AppChannel();

        board.register(new Student("S1", "Asha", "CSE", "asha@cse.edu", "9000000001", email, app));
        board.register(new Student("S2", "Ravi", "ECE", "ravi@ece.edu", "9000000002", sms));

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));

        try {
            new Notice("Sports Day");
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
        try {
            new Notice("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
    }
}