package notice;

/**
 * SMS delivery, formatted for a short message.
 */
public class SmsChannel implements NotificationChannel {

    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[SMS -> " + student.getName() + " " + student.getPhone() + "] "
                + notice.getTitle() + ".");
    }
}