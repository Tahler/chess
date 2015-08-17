package edu.neumont.pro180.chess.model.pieces;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class King extends Piece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public Collection<Tile> getValidMoves() {
        Collection<Tile> validMoves = new ArrayList<>();
        return validMoves;
    }

    @Override
    public boolean isLegalMove(Tile start, Tile end) {
        // Only one space in any direction
        return Math.abs(start.getY() - end.getY()) <= 1
            && Math.abs(start.getX() - end.getX()) <= 1;
    }

    @Override
    public String toString() {
        return "KING";
    }

    public Character toChar() {
        return 'K';
    }
}
