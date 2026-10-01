public class Participant {

    private static final String UNASSIGNED_TEAM = "Unassigned";

    private String name;
    private String teamName;
    private boolean registered;

    /**
     * Constructor for a participant who arrives already part of a team. Sets
     * all three fields directly.
     *
     * @param name     participant name
     * @param teamName team the participant belongs to
     */
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    /**
     * Constructor for a solo participant who has no team yet. Chains to the
     * two-argument constructor via this(...) so the field-setting logic exists
     * only once, giving them a placeholder team name until they are matched.
     *
     * @param name participant name
     */
    public Participant(String name) {
        this(name, UNASSIGNED_TEAM);
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean isRegistered() {
        return registered;
    }

    /**
     * Prints name, teamName and registered on a single line.
     */
    public void printStatus() {
        System.out.println(name + " | " + teamName + " | Registered: " + registered);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teamNames = {"ByteBusters", "", "CodeCrafters", ""};

        Participant[] participants = new Participant[names.length];

        for (int i = 0; i < participants.length; i++) {
            // if-else picks the constructor that fits this participant
            if (teamNames[i] == null || teamNames[i].trim().isEmpty()) {
                participants[i] = new Participant(names[i]);
            } else {
                participants[i] = new Participant(names[i], teamNames[i]);
            }

            participants[i].printStatus();
        }
    }
}