package edu.neumont.pro180.chess.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class Piece {
    private final Board board;
    private final Color color;

    public Piece(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    // TODO: make it return a collection of tiles that are unoccupied
//    public abstract Tile[] validMoves {}

    /**
     * Shadows the Object.toString(), but serves the same purpose.
     * This forces the pieces to override toString();
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
