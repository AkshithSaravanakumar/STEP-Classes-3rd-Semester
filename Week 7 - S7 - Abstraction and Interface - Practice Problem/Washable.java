/**
 * Being washable is a separate concern from preparing food, so it is modelled
 * as an interface rather than folded into KitchenTool.
 */
public interface Washable {

    /**
     * @return a message describing how this tool was cleaned
     */
    String clean();
}