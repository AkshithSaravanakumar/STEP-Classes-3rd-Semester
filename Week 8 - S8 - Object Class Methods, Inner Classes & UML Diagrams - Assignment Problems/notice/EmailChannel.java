package notice;

/**
 * Email delivery. Formats the message with the student's email address.
 */
public class EmailChannel implements NotificationChannel {

    @Override
    public String getChannelName() {
        return "Email";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[Email -> " + student.getName() + " <" + student.getEmail() + ">] "
                + notice.getTitle() + ".");
    }
}