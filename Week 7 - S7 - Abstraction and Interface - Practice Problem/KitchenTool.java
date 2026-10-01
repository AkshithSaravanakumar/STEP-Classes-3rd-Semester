/**
 * How a tool prepares food is the abstract concern. speedLevel is private and
 * only reachable through JavaBean accessors that reject values outside 1-5.
 */
public abstract class KitchenTool {

    public static final int MIN_SPEED_LEVEL = 1;
    public static final int MAX_SPEED_LEVEL = 5;

    private static final int DEFAULT_SPEED_LEVEL = 1;

    private int speedLevel = DEFAULT_SPEED_LEVEL;

    /**
     * @return the name of this tool's food preparation step
     */
    public abstract String prepare();

    /**
     * @return the current speed level, always between 1 and 5
     */
    public int getSpeedLevel() {
        return speedLevel;
    }

    /**
     * Rejects first, assigns second, so an out-of-range value leaves the
     * previous level untouched.
     *
     * @param speedLevel the requested level
     */
    public void setSpeedLevel(int speedLevel) {
        if (speedLevel < MIN_SPEED_LEVEL || speedLevel > MAX_SPEED_LEVEL) {
            System.out.println("Rejected speed level " + speedLevel
                    + ": must be between " + MIN_SPEED_LEVEL + " and " + MAX_SPEED_LEVEL);
            return;
        }
        this.speedLevel = speedLevel;
    }
}