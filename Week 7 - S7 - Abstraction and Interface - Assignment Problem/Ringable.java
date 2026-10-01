/**
 * The only thing an alarm clock and a doorbell have in common is that both
 * ring, so that is the entire contract. They deliberately share no parent
 * class, because they need no other shared behaviour.
 */
public interface Ringable {

    /**
     * @return the message describing this device ringing
     */
    String ring();
}