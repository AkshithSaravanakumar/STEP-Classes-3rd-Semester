package laundry;

/**
 * Delicate wash, added to show the point of the exercise: booking logic is
 * written against {@link WashType}, so this class appearing later required no
 * change to {@link LaundryService} or {@link WashingMachine}.
 */
public class DelicateWash extends WashType {

    @Override
    public String getTypeName() {
        return "Delicate";
    }

    @Override
    public int getDurationMinutes() {
        return 20;
    }

    @Override
    public double getCharge() {
        return 60.0;
    }
}