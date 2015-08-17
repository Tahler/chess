package edu.neumont.pro180.chess.model.pieces;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Bishop extends Piece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public Collection<Tile> getValidMoves() {
        Collection<Tile> validMoves = new ArrayList<>();
        return validMoves;
    }

    @Override
    public boolean isLegalMove(Tile start, Tile end) {
        return Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY());
    }

    @Override
    public String toString() {
        return "BISHOP";
    }

    public Character toChar() {
        return 'B';
    }
}
