package model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Board {
    private final Tile[][] tiles;

    public Board() {
        // Initialize tiles
        tiles = new Tile[8][8];
        for (int i = 0; i < tiles.length; i++) { // going down the rows
            for (int j = 0; j < tiles[0].length; j++) { // going down the cols of each row
                tiles[i][j] = new Tile(i, j);
            }
        }

        // Initialize pieces

    }
}
