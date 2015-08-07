package edu.neumont.pro180.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Board {
    private final Tile[][] tiles;
    private List<Move> moves = new ArrayList<>();

    public Board() {
        // Initialize tiles
        tiles = new Tile[8][8];
        for (int i = 0; i < tiles.length; i++) { // going down the rows
            for (int j = 0; j < tiles[0].length; j++) { // going down the cols of each row
                tiles[i][j] = new Tile(i, j);
//                tiles[i][j].setPiece(new Pawn(this, Color.DARK, tiles[i][j]));
            }
        }
    }


    public void placePiece(Piece piece, Tile location) {
        location.setPiece(piece);
    }
    public void placePiece(Piece piece, int row, int col) {
        placePiece(piece, tiles[row][col]);
    }

    public void makeMove(Move move) {
        // move piece from start to end
        move.getStart().setPiece(null);
        move.getEnd().setPiece(move.getMover());
        moves.add(move);
    }
    public void makeMove(int startRow, int startCol, int endRow, int endCol) {
        this.makeMove(new Move(tiles[startRow][startCol], tiles[endRow][endCol]));
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
        // start from "8 | - | ..."
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
