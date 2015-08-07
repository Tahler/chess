package edu.neumont.pro180.chess.model;

import com.sun.istack.internal.Nullable;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Tile {
    private Piece piece;
    private final Integer row;
    private final Integer col;

    public Tile(Integer row, Integer column) throws Exception {
        if (row >= 0 && row < Board.BOARD_WIDTH) this.row = row;
        else throw new IndexOutOfBoundsException("Cannot create a tile in that row!");

        if (column >= 0 && column < Board.BOARD_HEIGHT) this.col = column;
        else throw new IndexOutOfBoundsException("Cannot create a tile in that column!");
    }

    public Piece getPiece() {
        return piece;
    }
    public void setPiece(@Nullable Piece piece) {
        this.piece = piece;
    }

    public String toString() {
        if (piece == null) return "-";
        else return piece.toStringTeam();
    }
}
