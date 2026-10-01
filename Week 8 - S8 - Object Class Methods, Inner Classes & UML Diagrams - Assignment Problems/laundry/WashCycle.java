package laundry;

/**
 * One wash run, tracking the student, the machine and the wash type. The charge
 * comes from the wash type, so this class never hard-codes a price.
 *
 * UML: WashCycle "1" -- "1" Student
 *         WashCycle "1" -- "1" WashingMachine
 *         WashCycle "1" -- "1" WashType
 */
public class WashCycle {

    private final Student student;
    private final WashingMachine machine;
    private final WashType washType;

    private boolean completed;

    /**
     * @param student who started the wash
     * @param machine the machine running it
     * @param washType the type of wash
     */
    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getDurationMinutes() {
        return washType.getDurationMinutes();
    }

    public double getCharge() {
        return washType.getCharge();
    }

    /**
     * Called by the machine once the cycle finishes.
     */
    void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return washType.getTypeName() + " wash on " + machine.getMachineId() + " for " + student;
    }
}