public class Painting extends ArtPiece {

    /**
     * @param title the painting's title
     */
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + getTitle() + ", framed on canvas";
    }
}