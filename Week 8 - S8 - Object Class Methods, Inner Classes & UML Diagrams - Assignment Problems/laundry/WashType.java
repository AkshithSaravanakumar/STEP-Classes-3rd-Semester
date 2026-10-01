package laundry;

/**
 * A wash type owns its own duration and charge. The booking logic never sees a
 * switch over wash types, it only asks the type, so a new type such as Delicate
 * is one new subclass.
 *
 * UML:
 * <pre>
 * WashType (abstract)
 *  + getTypeName() : String {abstract}
 *  + getDurationMinutes() : int {abstract}
 *  + getCharge() : double {abstract}
 *
 * WashType &lt;|-- QuickWash
 * WashType &lt;|-- NormalWash
 * WashType &lt;|-- HeavyWash
 * WashType &lt;|-- DelicateWash
 * </pre>
 */
public abstract class WashType {

    /**
     * @return the display name of this wash type
     */
    public abstract String getTypeName();

    /**
     * @return how long this wash runs, in minutes
     */
    public abstract int getDurationMinutes();

    /**
     * @return the charge for this wash, in rupees
     */
    public abstract double getCharge();
}