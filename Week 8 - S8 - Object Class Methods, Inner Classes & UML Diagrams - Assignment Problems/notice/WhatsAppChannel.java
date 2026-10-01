package notice;

/**
 * WhatsApp delivery, added to prove the broadcaster does not change.
 */
public class WhatsAppChannel implements NotificationChannel {

    @Override
    public String getChannelName() {
        return "WhatsApp";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[WhatsApp -> " + student.getName() + "] " + notice.getTitle() + ".");
    }
}