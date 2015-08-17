package edu.neumont.pro180.chess.model.pieces;

import edu.neumont.pro180.chess.Main;
import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(Board board, Color color) {
        super(board, color);
        hasMoved = false;
    }

    @Override
    public Collection<Tile> getValidMoves() {
        Collection<Tile> validMoves = new ArrayList<>();
        return validMoves;
    }

    @Override
    public boolean isLegalMove(Tile start, Tile end) {
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
    }

    @Override
    public String toString() {
        return "PAWN";
    }

    public Character toChar() {
        return 'P';
    }
}
