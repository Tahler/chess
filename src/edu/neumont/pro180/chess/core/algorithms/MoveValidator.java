package edu.neumont.pro180.chess.core.algorithms;

import edu.neumont.pro180.chess.core.model.*;

import java.util.*;

/*
// Move to the same location
        if (start == end) throw new IllegalMoveException("Cannot move to the same tile");
        // Move from a location where there is no piece
        if (mover == null) throw new IllegalMoveException("There is no piece at that location.");
        // If it is not that piece's team's turn
        if (mover.getColor() == lastMoveColor) throw new IllegalMoveException("It is not currently " + mover.getColor() + "'s turn.");
        // Move to a location where there is already an occupying piece (unless it is a capture)
        if (captured != null && !isCapture) throw new IllegalMoveException("There is already a piece at that location.");
        // Move to capture a location where there is no occupying piece
        if (captured == null &&  isCapture) throw new IllegalMoveException("There is no piece to capture at that location.");
        // Move from or to a location that doesn't exist: COVERED IN TILE CONSTRUCTOR

        if (!mover.isLegalMove(start, end)) throw new IllegalMoveException("The " + mover.toStringTeam() + " cannot move to that location.");

        // Iterate through the path, figure out if this path is obstructed (knights excluded from path obstruction)
        if (!(mover instanceof Knight)) {
            for (Tile tile : this) {
                Piece pathBlocker = tile.getPiece();
                if (pathBlocker != null && tile != end) throw new IllegalMoveException("The " + pathBlocker.toStringTeam() + " is in the way of the " + mover.toStringTeam());
            }
        }
 */

/**
 * Created by Tyler Berry on 8/18/2015.
 */
public class MoveValidator {
    private final Board board;

    public MoveValidator(Board board) {
        this.board = board;
    }

    public Boolean isValid(Move move) {
        return getValidMoves(move.getStart()).contains(move);
    }

    /**
     * First finds legal shapes of moves,
     * then filters by moves that would take your own piece,
     * or moves that would put the king in check
     * @param p The Tile location of the potential mover
     * @return A list of legal moves for the piece in that tile
     */
    public List<Move> getValidMoves(Tile p) {
        List<Move> moves;
        Piece mover = board.getPieceAt(p.x, p.y);

        // Retrieve the possible moves based on the piece's type
        switch (board.getPieceAt(p.x, p.y).getType()) {
            case PAWN:
                moves = getPawnMoves(p);
                break;
            case ROOK:
                moves = getRookMoves(p);
                break;
            case KNIGHT:
                moves = getKnightMoves(p);
                break;
            case BISHOP:
                moves = getBishopMoves(p);
                break;
            case QUEEN:
                moves = getQueenMoves(p);
                break;
            case KING:
                moves = getKingMoves(p);
                break;
            default:
                return null; // No piece on this tile: no valid moves
        }

        // Filter: don't allow pieces to move on top of their own pieces
        for(Move move : moves) {
            // Remove from the possible moves if attempting to capture its own color
            Piece captured = board.getPieceAt(move.getEnd().x, move.getEnd().y);
            if (captured != null && captured.getColor().equals(mover.getColor())) moves.remove(move);
            // Remove from the possible moves if the move would put the king in check
            else {
                board.makeMove(move); // Make a move
                if (isAttacked( // Pass the current player's King location to check if it's in check
                       mover.getColor().equals(Color.LIGHT) ? board.lightKingLocation : board.darkKingLocation)) {
                    moves.remove(move);
                }
                board.undoMove(); // Undo the move
            }
        }

        return (moves.size() == 0) ? null : moves;
    }

    /**
     * @param location The tile location of the potentially attacked piece
     * @return True if the piece at the tile location could be attacked in the current board state, false otherwise
     */
    private Boolean isAttacked(Tile location) {
        Color color = board.getPieceAt(location).getColor(); // The color of the attacked
        Piece potentialAttacker;
        // If any possible next move on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                potentialAttacker = board.getPieceAt(i, j);
                if (potentialAttacker != null && !potentialAttacker.getColor().equals(color)) {
                    List<Move> attacks = getValidMoves(new Tile(i, j));
                    if (attacks != null) {
                        for (Move move : attacks) {
                            if (move.getEnd().equals(location)) return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    private Boolean isAttacked(Integer x, Integer y) {
        return isAttacked(new Tile(x, y));
    }

    /**
     * The following methods check legal shapes of moves
     */
    private List<Move> getPawnMoves(Tile p) {
        List<Move> moves = new ArrayList<>();

        // Moves
        if (board.getPieceAt(p.x, p.y).getColor().equals(Color.LIGHT)) {
            if (board.getPieceAt(p.x, p.y + 1) == null) { // Pawn can move forward one space as long as no piece is blocking
                moves.add(new Move(p.x, p.y, p.x, p.y + 1));
                if (p.y == 1) moves.add(new Move(p.x, p.y, p.x, p.y + 2)); // Pawn can move forward two spots
            }
        } else {
            if (board.getPieceAt(p.x, p.y - 1) == null) {
                moves.add(new Move(p.x, p.y, p.x, p.y - 1));
                if (p.y == 6) moves.add(new Move(p.x, p.y, p.x, p.y - 2)); // TODO: will this allow a pawn to jump over a piece in front of it on the first move??
            }
        }

        // Attacks looks invalid
        if (board.getPieceAt(p.x, p.y).getColor().equals(Color.LIGHT)) {
            if (p.x + 1 <= 7) moves.add(new Move(p.x, p.y, p.x + 1, p.y + 1));
            if (p.x - 1 >= 0) moves.add(new Move(p.x, p.y, p.x - 1, p.y + 1));
        } else {
            if (p.x + 1 <= 7) moves.add(new Move(p.x, p.y, p.x + 1, p.y - 1));
            if (p.x - 1 >= 0) moves.add(new Move(p.x, p.y, p.x - 1, p.y - 1));
        }

        return (moves.size() == 0) ? null : moves; // TODO: blatant
    }
    private List<Move> getRookMoves(Tile p) {
        List<Move> moves = new ArrayList<>();

        int i;

        // North
        i = 1;
        while (p.y + i <= 7) {
            moves.add(new Move(p.x, p.y, p.x, p.y + i));
            if (board.getPieceAt(p.x, p.y + i) !=null) break;
            i++;
        }

        // East
        i = 1;
        while (p.x + i <= 7) {
            moves.add(new Move(p.x, p.y, p.x + i, p.y));
            if (board.getPieceAt(p.x + i, p.y) != null) break;
            i++;
        }

        // South
        i = 1;
        while (p.y - i >= 0) {
            moves.add(new Move(p.x, p.y, p.x, p.y - i));
            if (board.getPieceAt(p.x, p.y - i) != null) break;
            i++;
        }

        // West
        i = 1;
        while (p.x - i >= 0) {
            moves.add(new Move(p.x, p.y, p.x - i, p.y));
            if (board.getPieceAt(p.x - i, p.y) != null) break;
            i++;
        }

        return (moves.size() == 0) ? null : moves;
    }
    private List<Move> getKnightMoves(Tile p) {
        List<Move> moves = new ArrayList<>();

        if (p.x + 1 <= 7) {
            if (p.y + 2 <= 7) moves.add(new Move(p.x, p.y, p.x+1, p.y+2));
            if (p.y - 2 >= 0) moves.add(new Move(p.x, p.y, p.x+1, p.y-2));
            if (p.x + 2 <= 7) {
                if (p.y + 1 <= 7) moves.add(new Move(p.x, p.y, p.x + 2, p.y + 1));
                if (p.y - 1 >= 0) moves.add(new Move(p.x, p.y, p.x + 2, p.y - 1));
            }
        }
        if (p.x - 1 >= 0) {
            if (p.y + 2 <= 7) moves.add(new Move(p.x, p.y, p.x - 1, p.y + 2));
            if (p.y - 2 >= 0) moves.add(new Move(p.x, p.y, p.x - 1, p.y - 2));
            if (p.x - 2 >= 0) {
                if (p.y + 1 <= 7) moves.add(new Move(p.x, p.y, p.x - 2, p.y + 1));
                if (p.y - 1 >= 0) moves.add(new Move(p.x, p.y, p.x - 2, p.y - 1));
            }
        }

        return (moves.size() == 0) ? null : moves;
    }
    private List<Move> getBishopMoves(Tile p) {
        List<Move> moves = new ArrayList<>();

        int i;

        // Northwest
        i = 1;
        while (p.x - i >= 0 && p.y + i <= 7) {
            moves.add(new Move(p.x, p.y, p.x - i, p.y + i));
            if (board.getPieceAt(p.x - i, p.y + i) != null) break;
            i++;
        }

        // Northeast
        i = 1;
        while (p.x + i <= 7 && p.y + i <= 7) {
            moves.add(new Move(p.x, p.y, p.x + i, p.y + i));
            if (board.getPieceAt(p.x + i, p.y + i) != null) break;
            i++;
        }

        // Southwest
        i = 1;
        while (p.x - i >= 0 && p.y - i >= 0) {
            moves.add(new Move(p.x, p.y, p.x - i, p.y - i));
            if (board.getPieceAt(p.x - i, p.y - i) != null) break;
            i++;
        }

        // Southeast
        i = 1;
        while (p.x + i <= 7 && p.y - i >= 0) {
            moves.add(new Move(p.x, p.y, p.x + i, p.y - i));
            if (board.getPieceAt(p.x + i, p.y - i) != null) break;
            i++;
        }

        return (moves.size() == 0) ? null : moves;
    }
    private List<Move> getQueenMoves(Tile p) {
        return merge(getRookMoves(p), getBishopMoves(p));
    }
    private List<Move> getKingMoves(Tile p) {
        List<Move> moves = new ArrayList<>();
        Piece king = board.getPieceAt(p.x, p.y);
        Color kingColor = king.getColor();

        // Typical moves
        if (p.x + 1 <= 7) {
            moves.add(new Move(p.x, p.y, p.x + 1, p.y));
            if (p.y + 1 <= 7) moves.add(new Move(p.x, p.y, p.x + 1, p.y + 1));
            if (p.y - 1 >= 0) moves.add(new Move(p.x, p.y, p.x + 1, p.y - 1));
        }
        if (p.x - 1 >= 0) {
            moves.add(new Move(p.x, p.y, p.x - 1, p.y));
            if (p.y + 1 <= 7) moves.add(new Move(p.x, p.y, p.x - 1, p.y + 1));
            if (p.y - 1 >= 0) moves.add(new Move(p.x, p.y, p.x - 1, p.y - 1));
        }
        if (p.y + 1 <= 7) moves.add(new Move(p.x, p.y, p.x, p.y + 1));
        if (p.y - 1 >= 0) moves.add(new Move(p.x, p.y, p.x, p.y - 1));

        // Castling moves
        if (kingColor.equals(Color.LIGHT)) {
            if (p.x == 4 && p.y == 0 && !isAttacked(4, 0)) { // Can't castle out of check
                // Short
                if (board.getPieceAt(5, 0) == null && !isAttacked(5, 0) && // Can't castle through check
                        board.getPieceAt(6, 0) == null && !isAttacked(6, 0) &&
                        board.getPieceAt(7, 0).getType().equals(Piece.Type.ROOK) && !isAttacked(7, 0) && board.getPieceAt(7, 0).getColor().equals(Color.LIGHT)) {
                    moves.add(new Move(4, 0, 6, 0));
                }
                // Long
                if (board.getPieceAt(3, 0) == null && !isAttacked(3, 0) && // Can't castle through check
                        board.getPieceAt(2, 0) == null && !isAttacked(2, 0) &&
                        board.getPieceAt(1, 0) == null && !isAttacked(1, 0) &&
                        board.getPieceAt(0, 0).getType().equals(Piece.Type.ROOK) && !isAttacked(0, 0) && board.getPieceAt(0, 0).getColor().equals(Color.LIGHT)) {
                    moves.add(new Move(4, 0, 2, 0));
                }
            }
        } else {
            if (p.x == 4 && p.y == 7 && !isAttacked(4, 7)) {
                // Short
                if (board.getPieceAt(5, 7) == null && !isAttacked(5, 7) &&
                        board.getPieceAt(6, 7) == null && !isAttacked(6, 7) &&
                        board.getPieceAt(7, 7).getType().equals(Piece.Type.ROOK) && !isAttacked(7, 7) && board.getPieceAt(7, 7).getColor().equals(Color.DARK)) {
                    moves.add(new Move(4, 7, 6, 7));
                }
                // Long
                if (board.getPieceAt(3, 7) == null && !isAttacked(3, 7) &&
                        board.getPieceAt(2, 7) == null && !isAttacked(2, 7) &&
                        board.getPieceAt(1, 7) == null && !isAttacked(1, 7) &&
                        board.getPieceAt(0, 7).getType().equals(Piece.Type.ROOK) && !isAttacked(0, 7) && board.getPieceAt(0, 7).getColor().equals(Color.DARK)) {
                    moves.add(new Move(4, 7, 2, 7));
                }
            }
        }

        return (moves.size() == 0) ? null : moves;
    }

    /**
     * A useful tool for merging two lists
     * @param a First list
     * @param b Second list
     * @return The merged list
     */
    private List<Move> merge(List<Move> a, List<Move> b) {
        List<Move> moves = new ArrayList<>();
        if (a != null) moves.addAll(a);
        if (b != null) moves.addAll(b);
        return (moves.size() == 0) ? null : moves;
    }
}
