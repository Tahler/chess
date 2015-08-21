package edu.neumont.pro180.chess.core.model;

/**
 * Created by Tyler Berry on 8/21/2015.
 * Used only by the MoveValidator to test if a move would put the king in check
 */
public class BoardBuffer extends AbstractBoard {
    public BoardBuffer(AbstractBoard board) {
        super(board);
    }
}
