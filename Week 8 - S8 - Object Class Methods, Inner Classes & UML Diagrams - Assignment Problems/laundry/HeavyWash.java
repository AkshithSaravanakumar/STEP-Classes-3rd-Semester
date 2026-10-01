package laundry;

/**
 * Heavy wash: 60 minutes, ₹45.
 */
public class HeavyWash extends WashType {

    @Override
    public String getTypeName() {
        return "Heavy";
    }

    @Override
    public int getDurationMinutes() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45.0;
    }
}