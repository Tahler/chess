package edu.neumont.pro180.chess.core.model;

import edu.neumont.pro180.chess.exception.IllegalMoveException;

import java.util.ArrayList;
import java.util.List;

import edu.neumont.pro180.chess.core.model.Piece.Type;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Board {
    // Private Member Variables
    private final Piece[][] board;

    // Track the location of kings for 'check' validation
    public Tile lightKingLocation;
    public Tile darkKingLocation;

    // The color of the player whose turn it is
    private Color currentTurn;

    private List<Move> moveHistory = new ArrayList<>();

    public Board() {
        // Initialize tiles
        board = new Piece[8][8];

        // Initialize pieces
        for (int i = 0; i < board.length; i++) {
            board[1][i] = new Piece(Type.PAWN, Color.DARK);
            board[6][i] = new Piece(Type.PAWN, Color.LIGHT);
        }

        board[0][0] = board[0][7] = new Piece(Type.ROOK, Color.DARK);
        board[0][1] = board[0][6] = new Piece(Type.KNIGHT, Color.DARK);
        board[0][2] = board[0][5] = new Piece(Type.BISHOP, Color.DARK);
        board[0][3] = new Piece(Type.QUEEN, Color.DARK);
        board[0][4] = new Piece(Type.KING, Color.DARK);
        darkKingLocation = new Tile(0, 4);

        board[7][0] = board[7][7] = new Piece(Type.ROOK, Color.LIGHT);
        board[7][1] = board[7][6] = new Piece(Type.KNIGHT, Color.LIGHT);
        board[7][2] = board[7][5] = new Piece(Type.BISHOP, Color.LIGHT);
        board[7][3] = new Piece(Type.QUEEN, Color.LIGHT);
        board[7][4] = new Piece(Type.KING, Color.LIGHT);
        lightKingLocation = new Tile(7, 4);

        currentTurn = Color.LIGHT;
    }

    /**
     * Executes a move on the board and adds it to the history of moves.
     * It does not validate the move passed in.
     * @param move A pre-validated move
     */
    public void makeMove(Move move) {
        executeMove(move);
        moveHistory.add(move);
    }

    /**
     * Undoes the last move on the board.
     */
    public void undoMove() {
        Move lastMove = moveHistory.remove(moveHistory.size() - 1);   // remove from the history
        Move move = new Move(lastMove.getEnd(), lastMove.getStart()); // the opposite of the undone move

        Piece mover = move.getMover();
        if (mover.getType().equals(Type.KING)) {
            if (mover.getColor().equals(Color.LIGHT)) {
                lightKingLocation = move.getEnd();
            } else {
                darkKingLocation = move.getEnd();
            }
        }

        move.execute(board);
    }

    /**
     * Used by addMove and undoMove to switch piece positions on the board.
     * @param move The pre-validated move
     */
    private void executeMove(Move move) {
        // If king, move tracked king location
        Piece mover = move.getMover();
        if (mover.getType().equals(Type.KING)) {
            if (mover.getColor().equals(Color.LIGHT)) {
                lightKingLocation = move.getEnd();
            } else {
                darkKingLocation = move.getEnd();
            }
        }

        move.execute(board);
    }

    /**
     * Executes a move on the board if it is valid
     * @throws IllegalMoveException Thrown if the move is illegal
     */
//    public void tryMove(Move move) throws IllegalMoveException {
//        if (move.isValid()) {
//            move.getStart().setPiece(null);
//            move.getEnd().setPiece(move.getMover());
//            moveHistory.add(move);
//        } else throw new IllegalMoveException("That move is invalid");
//    }
//    public Move tryMove(int startRow, int startCol, int endRow, int endCol) throws IllegalMoveException {
//        Move result = new Move(pieces[startRow][startCol], pieces[endRow][endCol]);
//        this.tryMove(result);
//        return result;
//    }

    public Piece getPieceAt(Tile location) {
        return getPieceAt(location.x, location.y);
    }

    public Piece getPieceAt(Integer x, Integer y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) return board[x][y];
        else throw new IllegalArgumentException("The tile (" + x + ", " + y + ") is not on the board!");
    }

    /**
     * Prints the boards toString() to the console.
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Returns a string representation of the board, complete with piece representations as well on it.
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
     * @return The string representation of the board, including new lines
     */
    public String toString() {
        String board = "" +
                "    a   b   c   d   e   f   g   h  " + "\n" +
                "  ---------------------------------" + "\n";

        for (int i = 0; i < this.board.length; i++) {
            board += (8 - i) + " "; // "8 "
            for (int j = 0; j < this.board[0].length; j++) {
                Piece p = this.board[i][j];
                board += "| " + ((p == null) ? " - " : p.toChar()) + " ";
            }
            board += "|" + "\n";
            board += "  ---------------------------------" + "\n";
        }

        return board;
    }

    public Boolean isOver() {
        return false;
    }

    public Color getResult() {
        return Color.LIGHT;
    }
}
