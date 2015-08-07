package edu.neumont.pro180.chess.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class Piece {
    private final Color color;
    private Tile location;

    public Piece(Color color, Tile startingLocation) {
        this.color = color;
        this.location = startingLocation;
    }
}
