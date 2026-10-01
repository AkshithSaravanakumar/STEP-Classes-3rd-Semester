public class Doorbell implements Ringable {

    private final String location;

    /**
     * @param location where this doorbell is installed
     */
    public Doorbell(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}