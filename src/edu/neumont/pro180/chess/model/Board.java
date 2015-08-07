package edu.neumont.pro180.chess.model;

import edu.neumont.pro180.chess.exception.IllegalMoveException;

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

    /**
     * Places a piece at the specified location, so long as the location is currently empty
     * @param piece The piece to be placed
     * @param location The location the piece should be placed on
     * @throws IllegalMoveException Thrown if the location has a piece currently on it
     */
    public void placePiece(Piece piece, Tile location) throws IllegalMoveException {
        if (location.getPiece() == null) location.setPiece(piece);
        else throw new IllegalMoveException("Location is currently non-empty");
    }
    public void placePiece(Piece piece, int row, int col) throws IllegalMoveException {
        placePiece(piece, tiles[row][col]);
    }

    /**
     * Executes a move on the board if it is valid
     * @param move The move attempting to be made
     * @throws IllegalMoveException Thrown if the move is illegal
     */
    public void tryMove(Move move) throws IllegalMoveException {
        if (move.isValid()) {
            move.getStart().setPiece(null);
            move.getEnd().setPiece(move.getMover());
            moves.add(move);
        } else throw new IllegalMoveException("That move is currently invalid");
    }
    public void tryMove(int startRow, int startCol, int endRow, int endCol, boolean isCapture) throws IllegalMoveException {
        this.tryMove(new Move(tiles[startRow][startCol], tiles[endRow][endCol], isCapture));
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Prints the boards toString() to the console.
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Prints a tabular representation of the board, with the pieces on it.
     *
     * Example:
     *
     *     a   b   c   d   e   f   g   h
     *   ---------------------------------
     * 8 | - | k | r | - | - | - | - | - |
     *   ---------------------------------
     * 7 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     * 6 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     * 5 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     * 4 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     * 3 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     * 2 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     * 1 | - | - | - | - | - | - | - | - |
     *   ---------------------------------
     *
     * @return The string representation of the board (includes new lines)
     */
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
