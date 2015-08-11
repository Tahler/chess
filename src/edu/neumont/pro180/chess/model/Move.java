package edu.neumont.pro180.chess.model;

import edu.neumont.pro180.chess.exception.IllegalMoveException;
import pieces.Knight;
import pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Move implements Iterable<Tile> {
    private final Tile start;
    private final Tile end;
    private final Piece mover;
    private final boolean isCapture;
    private final Piece captured;

    public Move(Tile start, Tile end, boolean isCapture) {
        this(start, end, start.getPiece(), end.getPiece(), isCapture);
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
     * 1. Moving to the same tile
     * 2. Move from a location where there is no piece
     * 3. Move to a location where there is already an occupying piece (unless it is a capture)
     * 4. Move to capture a location where there is no occupying piece
     * 5. The path is blocked by another piece (unless a capture is intended)
     * @return True if the move is valid, false if invalid
     */
    public boolean isValid() throws IllegalMoveException {
        // Move to and from the same location
        if (getStart() == getEnd()) throw new IllegalMoveException("Cannot move to the same tile");
        // Move from a location where there is no piece
        if (getStart().getPiece() == null) throw new IllegalMoveException("There is no piece at that location.");
        // Move to a location where there is already an occupying piece (unless it is a capture)
        if (getEnd().getPiece() != null && !isCapture()) throw new IllegalMoveException("There is already a piece at that location.");
        // Move to capture a location where there is no occupying piece
        if (isCapture() && getEnd().getPiece() == null) throw new IllegalMoveException("There is no piece to capture at that location.");
        // Move from or to a location that doesn't exist: COVERED IN TILE CONSTRUCTOR

        // Iterate through the path, figure out if this path is obstructed (knights excluded from path obstruction)
        if (!(mover instanceof Knight)) {
            for (Tile tile : this) {
                Piece pathBlocker = tile.getPiece();
                if (pathBlocker != null && tile != end) throw new IllegalMoveException("The " + pathBlocker.toStringTeam() + " is in the way of the " + mover.toStringTeam());
            }
        }

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
        return "Moving the " + mover.toStringTeam() + " from " + start.toString() + " to " + end.toString();
    }

    @Override
    public Iterator<Tile> iterator() {
        Board board = Board.getInstance();
        Collection<Tile> path = new ArrayList<>();

        int x = start.getX();
        int y = start.getY();

        int endX = end.getX();
        int endY = end.getY();

        // 1 for further down, -1 for further back, 0 for static
        int dirX = ((endX > x) ? 1 : (x == endX) ? 0 : -1);
        int dirY = ((endY > y) ? 1 : (y == endY) ? 0 : -1);

        Tile cursor = start;
        while (cursor != end) { // TODO: ensure same reference
            System.out.println(x + ", " + y);

            // Add before: the path should not include the starting tile.
            x += dirX;
            y += dirY;

            // Reassign the cursor tile
            cursor = board.getTile(x, y);

            // Finally, add the cursor tile
            path.add(cursor);
        }

        return path.iterator();
    }
}
