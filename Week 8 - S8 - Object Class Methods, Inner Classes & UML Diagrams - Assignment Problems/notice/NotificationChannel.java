package notice;

/**
 * A delivery channel. Each implementation delivers in its own way, and the
 * notice board depends only on this interface, so a new channel such as WhatsApp
 * is one new class.
 *
 * UML:
 * <pre>
 * NotificationChannel (interface)
 *  + getChannelName() : String
 *  + send(student, notice) : void
 *
 * NotificationChannel &lt;|.. EmailChannel
 * NotificationChannel &lt;|.. SmsChannel
 * NotificationChannel &lt;|.. AppChannel
 * NotificationChannel &lt;|.. WhatsAppChannel
 * </pre>
 */
public interface NotificationChannel {

    /**
     * @return the channel's display name
     */
    String getChannelName();

    /**
     * Delivers one notice to one student over this channel.
     *
     * @param student the recipient
     * @param notice the notice to deliver
     */
    void send(Student student, Notice notice);
}
