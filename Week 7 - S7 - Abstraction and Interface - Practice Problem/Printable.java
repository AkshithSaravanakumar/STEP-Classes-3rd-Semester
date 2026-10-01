/**
 * The one thing a package and an invoice have in common is that both need a
 * printed label. Printable is the only thing that lets one method work on both,
 * even though they share no other parent.
 */
public interface Printable {

    /**
     * @return the label text for this item
     */
    String printLabel();
}