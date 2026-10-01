/**
 * A pruner is a cutting tool, so it reuses CuttingTool's own message via
 * super.use() and appends only the trimming detail this level introduces.
 */
public class Pruner extends CuttingTool {

    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}