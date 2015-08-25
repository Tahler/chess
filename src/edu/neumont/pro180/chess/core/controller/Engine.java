package edu.neumont.pro180.chess.core.controller;

import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Color;
import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.core.view.ConsoleIO;
import edu.neumont.pro180.chess.core.view.View;
import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.io.MoveReader;
import edu.neumont.pro180.chess.io.UserMoveReader;

import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/18/2015.
 * The controller.
 * Instructs the interface to interact with the user and retrieve moves.
 * Validates moves before executing them on the board.
 */
public class Engine {
    private final Board board;
    private final MoveValidator validator;
    private final MoveReader moveReader; // TODO: consider wrapping this in some view class, take souts and put them in there
    private final View view;

    public Engine() {
        this.board = new Board();
        this.validator = new MoveValidator(board);
        this.moveReader = new UserMoveReader();
        this.view = new ConsoleIO();

        board.makeMove(new Move(4, 6, 4, 4));
        board.makeMove(new Move(5, 1, 5, 3));
        board.makeMove(new Move(4, 4, 5, 3));
        board.makeMove(new Move(6, 1, 6, 3));
//        board.makeMove(new Move(3, 7, 7, 3));
    }

    public void play() {
        view.print(board);
        do {
            Move move;
            try {
                view.print(">");
                move = moveReader.readLine(); // parse exception throws to catch
                if (move == null) break;
                validator.validate(move);
                board.makeMove(move);
                    view.print(move.toString());
                    if (validator.isInCheck() && !validator.isOver()) view.print("Check!");
                    view.print(board);
            } catch (IllegalMoveException e) {
                view.print(e.getMessage());
            } catch (ParseException e) {
                view.print(e.getMessage());
            }
        } while (!validator.isOver());

        Color result = validator.getResult();
        view.print((result == null) ? "Stalemate!" : "Checkmate! The winner is " + result + "!");
    }
}
