package edu.neumont.pro180.chess.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class Piece {
    private final Board board;
    private final Color color;
    private Tile location;

    public Piece(Board board, Color color, Tile startingLocation) {
        this.board = board;
        this.color = color;
        this.location = startingLocation;
        startingLocation.setPiece(this);
    }

    public void setLocation(Tile location) {
        this.location = location;
        location.setPiece(this); // be sure to change the tile's piece too!
    }

    // TODO: make it return a collection of tiles that are unoccupied
//    public abstract Tile[] validMoves {}

    /**
     * Shadows the Object.toString(), but serves the same purpose.
     * @return A string representation of this piece, not acknowledging color.
     */
    public abstract String toString();

    /**
     * A light piece will be lowercase.
     * A dark piece will be uppercase.
     * @return The string representation of this piece, acknowledging color.
     */
    public String toStringTeam() {
        switch (color) {
            case LIGHT:
                return toString().toLowerCase();
            case DARK:
                return toString().toUpperCase();
            default:
                return "This piece has no color?";
        }
    }
}
