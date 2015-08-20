package edu.neumont.pro180.chess.core.model;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (x != null ? !x.equals(tile.x) : tile.x != null) return false;
        return !(y != null ? !y.equals(tile.y) : tile.y != null);

    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf((char) (x + 97)).toUpperCase() + (8 - y);
    }
}
