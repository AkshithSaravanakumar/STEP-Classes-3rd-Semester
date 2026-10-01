package laundry;

/**
 * Normal wash: 45 minutes, ₹30.
 */
public class NormalWash extends WashType {

    @Override
    public String getTypeName() {
        return "Normal";
    }

    @Override
    public int getDurationMinutes() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30.0;
    }
}