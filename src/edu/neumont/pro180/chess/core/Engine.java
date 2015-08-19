package edu.neumont.pro180.chess.core;

import edu.neumont.pro180.chess.core.algorithms.MoveValidator;

/**
 * Created by Tyler Berry on 8/18/2015.
 */
public class Engine {
    private Board board;
    private MoveValidator validator;
    private Move lastMove;

    public Engine() {
        board = new Board();
        validator = new MoveValidator(board);
        lastMove = null;
    }
}
