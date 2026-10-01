/**
 * Drives every ringing device through one shared system. The loop needs to
 * know nothing about the devices beyond Ringable.
 */
public class WakeUpCircuit {

    /**
     * @param devices a mixed array of ringing devices
     */
    public static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring());

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring());

        ringAll(new Ringable[]{ a, d });
    }
}