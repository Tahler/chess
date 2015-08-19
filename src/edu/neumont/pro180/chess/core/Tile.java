package edu.neumont.pro180.chess.core;

/**
 * Simply a wrapper for an x and y value on the chess board.
 */
public class Tile {
    public final Integer x;
    public final Integer y;

    public Tile(Integer row, Integer column) throws IndexOutOfBoundsException {
        if (row >= 0 && row < 8) this.y = row;
        else throw new IndexOutOfBoundsException("Cannot create a tile in that row!");

        if (column >= 0 && column < 8) this.x = column;
        else throw new IndexOutOfBoundsException("Cannot create a tile in that column!");
    }

    @Override
    public String toString() {
        return String.valueOf((char) (x + 97)).toUpperCase() + (8 - y);
    }
}
