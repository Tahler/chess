package edu.neumont.pro180.chess.model.pieces;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.Collection;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class Piece {
    protected final Board board;
    protected final Color color;

    public Piece(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    // TODO: will be useful to implement this for a GUI that highlights tiles
    public abstract Collection<Tile> getValidMoves();

    /**
     * @param start The piece's current position
     * @param end The piece's ending position
     * @return True if the move is legal according to the Piece's rules for moving,
     *         false otherwise
     */
    public abstract boolean isLegalMove(Tile start, Tile end);

    /**
     * Shadows the Object.toString(), but serves the same purpose.
     * This forces the pieces to override toString();
     * @return A string representation of this piece, not acknowledging color.
     */
    public abstract String toString();

    /**
     * Similar to toString(), but a single character
     * @return The character representation of this piece, not acknowledging color.
     */
    public abstract Character toChar();

    public String toStringTeam() {
        switch (color) {
            case LIGHT:
                return "LIGHT " + toString();
            case DARK:
                return "DARK " + toString();
            default:
                return "COLORLESS"; // Should never be returned, piece's color cannot be null
        }
    }

    /**
     * A light piece will be lowercase.
     * A dark piece will be uppercase.
     * @return The string representation of this piece, acknowledging color.
     */
    public Character toCharTeam() {
        switch (color) {
            case LIGHT:
                return Character.toLowerCase(toChar());
            case DARK:
                return Character.toUpperCase(toChar());
            default:
                return '-'; // Should never be returned, piece's color cannot be null
        }
    }
}
