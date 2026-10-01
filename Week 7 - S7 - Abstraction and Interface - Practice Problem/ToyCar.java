public class ToyCar extends Toy {

    /**
     * @param name the toy's name
     */
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Vroom vroom!";
    }
}