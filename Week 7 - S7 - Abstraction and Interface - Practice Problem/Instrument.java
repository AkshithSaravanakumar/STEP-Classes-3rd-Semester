/**
 * Root of the instrument family. play() is abstract because playing is
 * something every instrument does, but no one generic description fits all.
 */
public abstract class Instrument {

    /**
     * @return the base playing message; subclasses layer their own detail on top
     */
    public abstract String play();
}