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
    private Color lastMove;

    public MoveValidator(Board board) {
        this.board = board;
        this.lastMove = null;
    }

    public Boolean isValid(Move move) {
        return getValidMoves(move.getStart()).contains(move);
    }

    private Boolean isAttacked(Tile location, Color color) {
        Piece attackedPiece = board.getPieceAt(location);
        Piece attacker;
        // If any possible next move on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                attacker = board.getPieceAt(i, j);
                if (attacker != null && !attacker.getColor().equals(color)) {
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
                board.makeMove(move); // TODO: use a transactional board buffer to check
                if (isAttacked(
                       (mover.getColor().equals(Color.LIGHT) ? board.lightKingLocation : board.darkKingLocation),
                        mover.getColor())) {
                    moves.remove(move);
                }
                board.undoMove();
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
        if(board.getPieceAt(p.x, p.y).getColor().equals(Color.LIGHT)) {
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
        while(p.x - i >= 0 && p.y + i <= 7) {
            moves.add(new Move(p.x, p.y, p.x - i, p.y + i));
            if (board.getPieceAt(p.x - i, p.y + i) != null) break;
            i++;
        }

        // Northeast
        i = 1;
        while(p.x + i <= 7 && p.y + i <= 7) {
            moves.add(new Move(p.x, p.y, p.x + i, p.y + i));
            if(board.getPieceAt(p.x + i, p.y + i) != null) break;
            i++;
        }

        // Southwest
        i = 1;
        while(p.x - i >= 0 && p.y - i >= 0) {
            moves.add(new Move(p.x, p.y, p.x - i, p.y - i));
            if(board.getPieceAt(p.x - i, p.y - i) != null) break;
            i++;
        }

        // Southeast
        i = 1;
        while(p.x + i <= 7 && p.y - i >= 0) {
            moves.add(new Move(p.x, p.y, p.x + i, p.y - i));
            if(board.getPieceAt(p.x + i, p.y - i) != null) break;
            i++;
        }

        return (moves.size() == 0) ? null : moves;
    }

    private List<Move> getQueenMoves(Tile p) {
        return merge(getRookMoves(p), getBishopMoves(p));
    }

    private List<Move> getKingMoves(Tile p) {
        List<Move> moves = new ArrayList<>();



        return (moves.size() == 0) ? null : moves;
    }

    /**
     * Returns true if move is valid.
     * Will return false if:
     * 1. Moving to the same tile
     * 2. Move from a location where there is no piece
     * 3. Move to a location where there is already an occupying piece (unless it is a capture)
     * 4. Move to capture a location where there is no occupying piece
     * 5. The path is blocked by another piece (unless a capture is intended)
     * @return True if the move is valid, false if invalid
     */
//    public boolean isValid() throws IllegalMoveException {
//        // Move to the same location
//        if (start == end) throw new IllegalMoveException("Cannot move to the same tile");
//        // Move from a location where there is no piece
//        if (mover == null) throw new IllegalMoveException("There is no piece at that location.");
//        // If it is not that piece's team's turn
//        if (mover.getColor() == lastMoveColor) throw new IllegalMoveException("It is not currently " + mover.getColor() + "'s turn.");
//        // Move to a location where there is already an occupying piece (unless it is a capture)
//        if (captured != null && !isCapture) throw new IllegalMoveException("There is already a piece at that location.");
//        // Move to capture a location where there is no occupying piece
//        if (captured == null &&  isCapture) throw new IllegalMoveException("There is no piece to capture at that location.");
//        // Move from or to a location that doesn't exist: COVERED IN TILE CONSTRUCTOR
//
//        if (!mover.isLegalMove(start, end)) throw new IllegalMoveException("The " + mover.toStringTeam() + " cannot move to that location.");
//
//        // Iterate through the path, figure out if this path is obstructed (knights excluded from path obstruction)
//        if (!(mover instanceof Knight)) {
//            for (Tile tile : this) {
//                Piece pathBlocker = tile.getPiece();
//                if (pathBlocker != null && tile != end) throw new IllegalMoveException("The " + pathBlocker.toStringTeam() + " is in the way of the " + mover.toStringTeam());
//            }
//        }
//
//        Move.lastMoveColor = mover.getColor();
//        return true;
//    }
}
