package edu.neumont.pro180.chess.model;

import edu.neumont.pro180.chess.exception.IllegalMoveException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Move {
    private final Tile start;
    private final Tile end;
    private final Piece mover;
    private final boolean isCapture;
    private final Piece captured;

    public Move(Tile start, Tile end, boolean isCapture) {
        this.start = start;
        this.end = end;
        this.mover = start.getPiece();
        this.isCapture = isCapture;
        this.captured = end.getPiece();
    }
    public Move(Tile start, Tile end, Piece mover, Piece captured, boolean isCapture) {
        this.start = start;
        this.end = end;
        this.mover = mover;
        this.captured = captured;
        this.isCapture = isCapture;
    }

    /**
     * Returns true if move is valid.
     * Will return false if:
     * 1. Move from a location where there is no piece
     * 2. Move to a location where there is already an occupying piece (unless it is a capture)
     * 3. Move to capture a location where there is no occupying piece
     * @return True if the move is valid, false if invalid
     */
    public boolean isValid() throws IllegalMoveException {
        // Move from a location where there is no piece
        if (getStart().getPiece() == null) throw new IllegalMoveException("There is no piece at that location.");
        // Move to a location where there is already an occupying piece (unless it is a capture)
        if (getEnd().getPiece() != null && !isCapture()) throw new IllegalMoveException("There is already a piece at that location.");
        // Move to capture a location where there is no occupying piece
        if (isCapture() && getEnd().getPiece() == null) throw new IllegalMoveException("There is no piece to capture at that location.");
        // Move from or to a location that doesn't exist: COVERED IN TILE CONSTRUCTOR

        return true;
    }

    public Tile getStart() {
        return start;
    }
    public Tile getEnd() {
        return end;
    }
    public Piece getMover() {
        return mover;
    }
    public Piece getCaptured() {
        return captured;
    }

    public boolean isCapture() {
        return isCapture;
    }

    @Override
    public String toString() {
        try {
            isValid();
            return "Moving " + mover.toString() + " from " + start.toString() + " to " + end.toString();
        } catch (IllegalMoveException e) {
            return "The " + mover.toString() + " cannot move in that direction.";
        }
    }
}
