/**
 * A string instrument shares the general instrument playing routine, then adds
 * the strumming detail specific to strings.
 */
public class StringInstrument extends Instrument {

    @Override
    public String play() {
        // Instrument.play() is abstract, and Java forbids calling an abstract
        // method through super, so this first level supplies the whole message.
        // Deeper levels such as Violin can and do call super.play().
        return "Strumming the strings";
    }
}