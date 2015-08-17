package edu.neumont.pro180.chess.model.pieces;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Knight extends Piece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public Collection<Tile> getValidMoves() {
        Collection<Tile> validMoves = new ArrayList<>();
        return validMoves;
    }

    @Override
    public boolean isLegalMove(Tile start, Tile end) {
        return (Math.abs(start.getY() - end.getY()) <= 2 && Math.abs(start.getY() - end.getY()) <= 2)
            && !((start.getY() == end.getY() || start.getX() == end.getX())
                || (Math.abs(end.getY() - start.getY()) == Math.abs(end.getX() - start.getX())));
    }

    @Override
    public String toString() {
        return "KNIGHT";
    }

    public Character toChar() {
        return 'N';
    }
}
