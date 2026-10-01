public class AlarmClock implements Ringable {

    private final String time;

    /**
     * @param time the alarm's set time
     */
    public AlarmClock(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}