/**
 * Being chargeable is a separate concern from operating, so it is modelled as
 * an interface. The two charge() overloads are resolved at compile time purely
 * by how many arguments the caller passes.
 */
public interface Chargeable {

    /**
     * @return a message describing a general charge top-up
     */
    String charge();

    /**
     * @param minutes how many minutes to charge for
     * @return a message describing a timed charge
     */
    String charge(int minutes);
}