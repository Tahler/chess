package edu.neumont.pro180.chess.core.controller;

import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Color;
import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.io.MoveReader;

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
    private final Boolean isVerbose;

    public Engine(MoveReader moveReader, Main.Flag flag) {
        this.board = new Board();
        this.validator = new MoveValidator(board);
        this.moveReader = moveReader;
        this.isVerbose = flag.equals(Main.Flag.VERBOSE);

        board.makeMove(new Move(4, 6, 4, 4));
        board.makeMove(new Move(5, 1, 5, 3));
        board.makeMove(new Move(4, 4, 5, 3));
        board.makeMove(new Move(6, 1, 6, 3));
//        board.makeMove(new Move(3, 7, 7, 3));
    }

    public void play() {
        if (isVerbose) System.out.println(board);
        do {
            Move move;
            try {
                System.out.print(">");
                move = moveReader.readLine(); // parse exception throws to catch
                if (move == null) break;
                validator.validate(move);
                board.makeMove(move);
                if (isVerbose) { // print the move and board if verbose
                    System.out.println(move);
                    System.out.println(board);
                    if (validator.isInCheck() && !validator.isOver()) System.out.println("Check!");
                }
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
