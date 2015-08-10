package edu.neumont.pro180.chess.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class King extends Piece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "KING";
    }

    public Character toChar() {
        return 'K';
    }
}
