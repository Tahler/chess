package edu.neumont.pro180.chess.core.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Piece {

    /*
    :::movement:::

    // bishop
     return Math.abs(end.getY() - start.getY()) == Math.abs(end.getX() - start.getX());

    // rook
     return start.getY() == end.getY() || start.getX() == end.getX();

    // queen: Rook or Bishop move
     return (start.getY() == end.getY() || start.getX() == end.getX())
     || (Math.abs(end.getY() - start.getY()) == Math.abs(end.getX() - start.getX()));

     // pawn
         Integer deltaY = end.getY() - start.getY();
         Integer deltaYLimit = 1;

         // Move direction is based on color
         if (getColor().equals(Color.LIGHT)) {
         if (deltaY >= 0) return false;
         if (start.getY() == Board.LIGHT_PAWN_ROW) deltaYLimit = 2; // Pawn has not moved yet, can move two spaces
         } else if (getColor().equals(Color.DARK)) {
         if (deltaY <= 0) return false;
         if (start.getY() == Board.DARK_PAWN_ROW) deltaYLimit = 2;
         }
         // Now that direction is valid, verify distance.
         deltaY = Math.abs(deltaY);
         if (deltaY > deltaYLimit) return false;

         Integer deltaX = Math.abs(start.getX() - end.getX()); // 0 if moving, 1 if capturing
         if (deltaX == 0) { // Moving forward
         if (end.getPiece() != null) return false; // Pawn cannot capture pieces in front of itself
         } else if (deltaX == 1) { // Capturing
         if (end.getPiece() == null) return false; // Pawn cannot move diagonally if not capturing
         if (deltaY != 1) return false; // Pawn cannot move two spaces and capture like a knight
         } else return false; // Pawns can never move to the side

         return true;

        // king
         return Math.abs(start.getY() - end.getY()) <= 1
         && Math.abs(start.getX() - end.getX()) <= 1;

        // knight
        return (Math.abs(start.getY() - end.getY()) <= 2 && Math.abs(start.getY() - end.getY()) <= 2)
         && !((start.getY() == end.getY() || start.getX() == end.getX())
         || (Math.abs(end.getY() - start.getY()) == Math.abs(end.getX() - start.getX())));
     */

    public enum Type {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    private final Color color;
    private Type type;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return A string representation of this piece, not acknowledging color.
     */
    public String toString() {
        switch (type) {
            case PAWN:
                return "PAWN";
            case ROOK:
                return "ROOK";
            case KNIGHT:
                return "KNIGHT";
            case BISHOP:
                return "BISHOP";
            case QUEEN:
                return "QUEEN";
            case KING:
                return "KING";
            default:
                return "NONE";
        }
    }

    /**
     * Similar to toString(), but a single character
     * @return The character representation of this piece, not acknowledging color.
     */
    public Character toChar() {
        switch (type) {
            case PAWN:
                return 'P';
            case ROOK:
                return 'R';
            case KNIGHT:
                return 'N';
            case BISHOP:
                return 'B';
            case QUEEN:
                return 'Q';
            case KING:
                return 'K';
            default:
                return '-';
        }
    }

    /**
     * Precedes the toString() with the respective "LIGHT" or "DARK"
     * @return The string representation of this piece, acknowledging color.
     */
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
     * @return The character representation of this piece, acknowledging color.
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
