/**
 * There is no such thing as a generic toy with no specific sound of its own,
 * which is exactly why this class is abstract: new Toy() cannot compile.
 *
 * Every subclass reaches the shared counter through super(), so it only needs
 * to exist in one place.
 */
public abstract class Toy {

    private static final int FIRST_TOY_ID = 1001;

    private static int toysCreated;

    private final String toyId;
    private final String name;

    /**
     * @param name the toy's name
     */
    public Toy(String name) {
        toysCreated++;
        this.toyId = "TOY-" + (FIRST_TOY_ID + toysCreated - 1);
        this.name = name;
    }

    /**
     * @return this toy's unique id
     */
    public String getToyId() {
        return toyId;
    }

    public String getName() {
        return name;
    }

    /**
     * @return the sound this specific toy makes
     */
    public abstract String makeSound();

    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound());

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound());

        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}