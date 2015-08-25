package edu.neumont.pro180.chess.core.controller;

import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Color;
import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.io.MoveReader;
import edu.neumont.pro180.chess.io.UserMoveReader;

import java.text.ParseException;

/**
 * The controller.
 * Instructs the interface to interact with the user and retrieve moves.
 * Validates moves before executing them on the board.
 */
public class Engine {
    private final Board board;
    private final MoveValidator validator;
    private final MoveReader moveReader;

    public Engine() {
        this.board = new Board();
        this.validator = new MoveValidator(board);
        this.moveReader = new UserMoveReader();
    }

    public void play() {
        System.out.println(board);
        do {
            Move move;
            try {
                System.out.print(">");
                move = moveReader.readLine(); // parse exception throws to catch
                if (move == null) break;
                validator.validate(move);
                board.makeMove(move);
                System.out.println(move);
                System.out.println(board);
                if (validator.isInCheck() && !validator.isOver()) System.out.println("Check!");
            } catch (IllegalMoveException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (!validator.isOver());

        Color result = validator.getResult();
        System.out.println((result == null) ? "Stalemate!" : "Checkmate! The winner is " + result + "!");
    }
}
