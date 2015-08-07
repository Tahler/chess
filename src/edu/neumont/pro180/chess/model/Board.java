package edu.neumont.pro180.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Board {
    public static final Integer BOARD_WIDTH = 8;
    public static final Integer BOARD_HEIGHT = 8;

    private final Tile[][] tiles;
    private List<Move> moves = new ArrayList<>();

    public Board() {
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

    public void makeMove(Move move) {
        if (move.isValid()) {
            move.getStart().setPiece(null);
            move.getEnd().setPiece(move.getMover());
            moves.add(move);
        }
    }

    public void makeMove(int startRow, int startCol, int endRow, int endCol, boolean isCapture) {
        this.makeMove(new Move(tiles[startRow][startCol], tiles[endRow][endCol], isCapture));
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
