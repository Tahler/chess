package edu.neumont.pro180.chess.model;

import edu.neumont.pro180.chess.model.pieces.Piece;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Tile {
    private Piece piece;
    private final Integer row;
    private final Integer col;

    public Tile(Integer row, Integer column) throws Exception {
        if (row >= 0 && row < Board.WIDTH) this.row = row;
        else throw new IndexOutOfBoundsException("Cannot create a tile in that row!");

        if (column >= 0 && column < Board.HEIGHT) this.col = column;
        else throw new IndexOutOfBoundsException("Cannot create a tile in that column!");
    }

    // Might seem backwards, but it makes the board more like a window coordinate system
    public int getY() {
        return row;
    }
    public int getX() {
        return col;
    }

    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Character toChar() {
        if (piece == null) return '-';
        else return (piece.toCharTeam());
    }

    public String toString() {
        return String.valueOf((char) (col + 97)).toUpperCase() + (Board.WIDTH - row);
    }
}
