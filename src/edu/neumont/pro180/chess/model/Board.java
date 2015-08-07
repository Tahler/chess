package edu.neumont.pro180.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Board {
    // Public Static Final
    public static final Integer BOARD_WIDTH = 8;
    public static final Integer BOARD_HEIGHT = 8;

    // Private Member Variables
    private final Tile[][] tiles;
    private List<Move> moves = new ArrayList<>();

    // Singleton setup
    private static Board ourInstance = new Board();
    public static Board getInstance() {
        return ourInstance;
    }
    private Board() {
        // Initialize tiles
        tiles = new Tile[8][8];
        for (int i = 0; i < tiles.length; i++) { // going down the rows
            for (int j = 0; j < tiles[0].length; j++) { // going down the cols of each row
                try {
                    tiles[i][j] = new Tile(i, j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // TODO: setup board
    }


    public void placePiece(Piece piece, Tile location) {
        location.setPiece(piece);
    }
    public void placePiece(Piece piece, int row, int col) {
        placePiece(piece, tiles[row][col]);
    }

    /**
     * Executes a move on the board if it is valid
     * @param move The move attempting to be made
     */
    public void tryMove(Move move) {
        if (move.isValid()) {
            move.getStart().setPiece(null);
            move.getEnd().setPiece(move.getMover());
            moves.add(move);
        }
    }

    public void tryMove(int startRow, int startCol, int endRow, int endCol, boolean isCapture) {
        this.tryMove(new Move(tiles[startRow][startCol], tiles[endRow][endCol], isCapture));
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void print() {
        System.out.println(toString());
    }

    // TODO: return a board, calling each tiles toString
    public String toString() {
        String board = "" +
                "    a   b   c   d   e   f   g   h  " + "\n" +
                "  ---------------------------------" + "\n";

        for (int i = 0; i < tiles.length; i++) {
            board += (8 - i) + " "; // "8 "
            for (int j = 0; j < tiles[0].length; j++) {
                board += "| " + tiles[i][j].toString() + " ";
            }
            board += "|" + "\n";
            board += "  ---------------------------------" + "\n";
        }

        return board;
    }
}
