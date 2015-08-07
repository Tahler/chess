package edu.neumont.pro180.chess.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Move {
    private final Tile start;
    private final Tile end;
    private final Piece mover;
    private final Piece captured;

    public Move(Tile start, Tile end) {
        this.start = start;
        this.end = end;
        this.mover = start.getPiece();
        this.captured = end.getPiece();
    }
    public Move(Tile start, Tile end, Piece mover, Piece captured) {
        this.start = start;
        this.end = end;
        this.mover = mover;
        this.captured = captured;
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
}
