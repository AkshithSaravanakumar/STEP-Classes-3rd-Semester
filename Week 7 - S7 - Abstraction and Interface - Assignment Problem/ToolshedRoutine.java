/**
 * Runs the toolshed routine for any GardenTool, relying only on the base type.
 */
public class ToolshedRoutine {

    /**
     * @param tools the tools to run the routine on
     */
    public static void useAll(GardenTool[] tools) {
        for (GardenTool tool : tools) {
            System.out.println(tool.use());
        }
    }

    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());

        useAll(new GardenTool[]{ c, p });
    }
}