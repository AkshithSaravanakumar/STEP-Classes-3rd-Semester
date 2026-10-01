public class Sculpture extends ArtPiece {

    /**
     * @param title the sculpture's title
     */
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + getTitle() + ", carved from stone";
    }
}