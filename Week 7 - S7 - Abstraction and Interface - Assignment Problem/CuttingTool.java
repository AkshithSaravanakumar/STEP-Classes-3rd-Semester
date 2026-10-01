/**
 * A cutting tool shares the general garden usage routine, then adds the
 * sharpening step specific to cutting tools.
 */
public class CuttingTool extends GardenTool {

    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        // GardenTool.use() is abstract, and Java forbids calling an abstract
        // method through super, so this first level supplies the whole message.
        // Deeper levels such as Pruner can and do call super.use().
        return "Using the tool in the garden, blade sharpened first";
    }
}