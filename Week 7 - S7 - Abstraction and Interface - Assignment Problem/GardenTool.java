/**
 * Root of the garden tool family. use() is abstract because every tool can be
 * used, but no single description fits them all.
 */
public abstract class GardenTool {

    /**
     * @return the base usage message; subclasses layer their own detail on top
     */
    public abstract String use();
}