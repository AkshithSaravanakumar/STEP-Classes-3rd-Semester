/**
 * A violin is a string instrument, so it reuses StringInstrument's own message
 * via super.play() and appends only the bow detail this level introduces.
 */
public class Violin extends StringInstrument {

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}