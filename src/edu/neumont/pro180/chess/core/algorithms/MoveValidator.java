package edu.neumont.pro180.chess.core.algorithms;

import edu.neumont.pro180.chess.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Tyler Berry on 8/18/2015.
 */
public class MoveValidator {
    private final Board board;

    public MoveValidator(Board board) {
        this.board = board;
    }

    public List<Move> getPawnMoves(Tile p) {
        List<Move> moves = new ArrayList<>();

        if (board.getPieceAt(p.x, p.y).getColor().equals(Color.LIGHT)) {
            if (board.getPieceAt(p.x, p.y + 1) == null) { // Pawn can move forward one space as long as no piece is blocking
                moves.add(new Move(p.x, p.y, p.x, p.y + 1));
                if (p.y == 1) moves.add(new Move(p.x, p.y, p.x, p.y + 2)); // Pawn can move forward two spots
            }
        } else {
            if (board.getPieceAt(p.x, p.y - 1) == null) {
                moves.add(new Move(p.x, p.y, p.x, p.y - 1));
                if (p.y == 6) moves.add(new Move(p.x, p.y, p.x, p.y - 2)); // TODO: will this allow a pawn to jump over a piece in front of it on the first move??
            }
        }

        return (moves.size() == 0) ? null : moves; // TODO: blatant
    }







    /**
     * Returns true if move is valid.
     * Will return false if:
     * 1. Moving to the same tile
     * 2. Move from a location where there is no piece
     * 3. Move to a location where there is already an occupying piece (unless it is a capture)
     * 4. Move to capture a location where there is no occupying piece
     * 5. The path is blocked by another piece (unless a capture is intended)
     * @return True if the move is valid, false if invalid
     */
//    public boolean isValid() throws IllegalMoveException {
//        // Move to the same location
//        if (start == end) throw new IllegalMoveException("Cannot move to the same tile");
//        // Move from a location where there is no piece
//        if (mover == null) throw new IllegalMoveException("There is no piece at that location.");
//        // If it is not that piece's team's turn
//        if (mover.getColor() == lastMoveColor) throw new IllegalMoveException("It is not currently " + mover.getColor() + "'s turn.");
//        // Move to a location where there is already an occupying piece (unless it is a capture)
//        if (captured != null && !isCapture) throw new IllegalMoveException("There is already a piece at that location.");
//        // Move to capture a location where there is no occupying piece
//        if (captured == null &&  isCapture) throw new IllegalMoveException("There is no piece to capture at that location.");
//        // Move from or to a location that doesn't exist: COVERED IN TILE CONSTRUCTOR
//
//        if (!mover.isLegalMove(start, end)) throw new IllegalMoveException("The " + mover.toStringTeam() + " cannot move to that location.");
//
//        // Iterate through the path, figure out if this path is obstructed (knights excluded from path obstruction)
//        if (!(mover instanceof Knight)) {
//            for (Tile tile : this) {
//                Piece pathBlocker = tile.getPiece();
//                if (pathBlocker != null && tile != end) throw new IllegalMoveException("The " + pathBlocker.toStringTeam() + " is in the way of the " + mover.toStringTeam());
//            }
//        }
//
//        Move.lastMoveColor = mover.getColor();
//        return true;
//    }
}
