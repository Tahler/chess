package edu.neumont.pro180.chess.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Queen extends Piece {
    public Queen(Board board, Color color, Tile startingLocation) {
        super(board, color, startingLocation);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
