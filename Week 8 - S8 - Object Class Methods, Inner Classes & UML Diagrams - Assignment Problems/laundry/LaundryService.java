package laundry;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Laundry booking. It talks only to {@link WashingMachine} and {@link WashType},
 * so a new wash type needs no change here.
 */
public class LaundryService {

    /**
     * Inner class: the machines this service knows about. Keeping the registry
     * nested means the service and the set of machines it hands out are declared
     * together rather than in two files.
     *
     * UML: LaundryService o-- MachineRegistry (inner class)
     */
    public static class MachineRegistry {

        private final Map<String, WashingMachine> machines = new LinkedHashMap<>();

        /**
         * @param machine the machine to add
         */
        public void add(WashingMachine machine) {
            machines.put(machine.getMachineId(), machine);
        }

        /**
         * @param machineId the machine's id
         * @return the machine, or null when it is not registered
         */
        public WashingMachine get(String machineId) {
            return machines.get(machineId);
        }
    }

    private final MachineRegistry registry = new MachineRegistry();

    /**
     * @param machine the machine to make available for booking
     */
    public void register(WashingMachine machine) {
        registry.add(machine);
    }

    /**
     * @param machineId the machine to use
     * @param student who wants the wash
     * @param washType the type of wash
     * @return the started cycle, or null when the machine is unknown or busy
     */
    public WashCycle startWash(String machineId, Student student, WashType washType) {
        WashingMachine machine = registry.get(machineId);
        if (machine == null) {
            System.out.println("Unknown machine: " + machineId + ".");
            return null;
        }
        return machine.startWash(student, washType);
    }

    /**
     * @param machineId the machine whose cycle should finish
     * @return the completed cycle, or null when nothing was running
     */
    public WashCycle completeCycle(String machineId) {
        WashingMachine machine = registry.get(machineId);
        if (machine == null) {
            System.out.println("Unknown machine: " + machineId + ".");
            return null;
        }
        return machine.completeCycle();
    }

    /**
     * @param machineId the machine to inspect
     * @return true when the machine is free
     */
    public boolean isFree(String machineId) {
        WashingMachine machine = registry.get(machineId);
        return machine != null && !machine.isBusy();
    }

    public static void main(String[] args) {
        LaundryService laundry = new LaundryService();
        laundry.register(new WashingMachine("M1"));
        laundry.register(new WashingMachine("M2"));

        Student asha = new Student("S1", "Asha");
        Student ravi = new Student("S2", "Ravi");
        Student neha = new Student("S3", "Neha");

        laundry.startWash("M1", asha, new QuickWash());
        laundry.startWash("M1", ravi, new HeavyWash());
        laundry.startWash("M2", ravi, new HeavyWash());
        laundry.completeCycle("M1");
        laundry.startWash("M1", neha, new NormalWash());

        System.out.println("M1 busy at end: " + !laundry.isFree("M1"));
        System.out.println("M2 busy at end: " + !laundry.isFree("M2"));
    }
}