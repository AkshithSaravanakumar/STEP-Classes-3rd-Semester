/**
 * Runs the warm-up routine for any Instrument, relying only on the base type.
 */
public class OrchestraWarmUp {

    /**
     * @param instruments the instruments to warm up
     */
    public static void warmUpAll(Instrument[] instruments) {
        for (Instrument instrument : instruments) {
            System.out.println(instrument.play());
        }
    }

    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        System.out.println(s.play());

        Violin v = new Violin();
        System.out.println(v.play());

        warmUpAll(new Instrument[]{ s, v });
    }
}