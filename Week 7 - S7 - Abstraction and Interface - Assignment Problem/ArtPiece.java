/**
 * There is no such thing as a generic art piece with no actual form, which is
 * why this class is abstract and describe() is abstract with it.
 *
 * The shared counter lives here and is incremented once inside this
 * constructor, so every subclass reaches it automatically through super().
 */
public abstract class ArtPiece {

    private static final int FIRST_PIECE_ID = 2001;

    private static int piecesCreated;

    private final String pieceId;
    private final String title;

    /**
     * @param title the piece's title
     */
    public ArtPiece(String title) {
        piecesCreated++;
        this.pieceId = "PIECE-" + (FIRST_PIECE_ID + piecesCreated - 1);
        this.title = title;
    }

    /**
     * @return this piece's unique id
     */
    public String getPieceId() {
        return pieceId;
    }

    public String getTitle() {
        return title;
    }

    /**
     * @return the description of this specific kind of art piece
     */
    public abstract String describe();

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());
        System.out.println(p.getPieceId());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
        System.out.println(s.getPieceId());
    }
}