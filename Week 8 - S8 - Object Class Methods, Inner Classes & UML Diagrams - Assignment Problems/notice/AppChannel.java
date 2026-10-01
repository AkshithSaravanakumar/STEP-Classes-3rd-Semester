package notice;

/**
 * In-app delivery, formatted as a push-style line.
 */
public class AppChannel implements NotificationChannel {

    @Override
    public String getChannelName() {
        return "App";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[App -> " + student.getName() + "] " + notice.getTitle() + ".");
    }
}