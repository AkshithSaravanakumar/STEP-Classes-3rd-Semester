package laundry;

/**
 * A washing machine runs at most one wash at a time. The busy flag and the
 * current cycle are private and reachable only through {@link #startWash} and
 * {@link #completeCycle}, so no outside code can flip a machine to free while a
 * wash is still running.
 *
 * UML:
 * <pre>
 * WashingMachine
 *  - machineId : String
 *  - currentCycle : WashCycle
 *  + getMachineId() : String
 *  + isBusy() : boolean
 *  + getCurrentCycle() : WashCycle
 *  + startWash(student, washType) : WashCycle
 *  + completeCycle() : WashCycle
 * </pre>
 *
 * WashingMachine "1" -- "0..1" WashCycle
 */
public class WashingMachine {

    private final String machineId;

    private WashCycle currentCycle;

    /**
     * @param machineId the machine's id, e.g. M1
     */
    public WashingMachine(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineId() {
        return machineId;
    }

    /**
     * @return true while a wash is running
     */
    public boolean isBusy() {
        return currentCycle != null;
    }

    /**
     * @return the running cycle, or null when the machine is free
     */
    public WashCycle getCurrentCycle() {
        return currentCycle;
    }

    /**
     * @param student who wants the wash
     * @param washType the type of wash
     * @return the started cycle, or null when the machine is busy
     */
    public WashCycle startWash(Student student, WashType washType) {
        if (isBusy()) {
            System.out.println("Machine " + machineId + " is currently busy.");
            return null;
        }
        currentCycle = new WashCycle(student, this, washType);
        System.out.println(washType.getTypeName() + " wash started on " + machineId
                + " for " + student.getName() + " (" + washType.getDurationMinutes() + " min).");
        System.out.println("Charge: ₹" + String.format("%.2f", washType.getCharge()) + ".");
        return currentCycle;
    }

    /**
     * Frees the machine and returns the cycle that finished.
     *
     * @return the completed cycle, or null when the machine was already free
     */
    public WashCycle completeCycle() {
        if (!isBusy()) {
            System.out.println("Machine " + machineId + " has no running cycle.");
            return null;
        }
        WashCycle finished = currentCycle;
        finished.markCompleted();
        currentCycle = null;
        System.out.println(machineId + " cycle completed.");
        System.out.println(machineId + " is now free.");
        return finished;
    }

    @Override
    public String toString() {
        return machineId;
    }
}