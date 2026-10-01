public class ToyRobot extends Toy {

    /**
     * @param name the toy's name
     */
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Beep boop!";
    }
}