package laundry;

/**
 * Quick wash: 30 minutes, ₹20.
 */
public class QuickWash extends WashType {

    @Override
    public String getTypeName() {
        return "Quick";
    }

    @Override
    public int getDurationMinutes() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20.0;
    }
}