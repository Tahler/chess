package edu.neumont.pro180.chess.core;

import edu.neumont.pro180.chess.Main;
import edu.neumont.pro180.chess.core.algorithms.MoveValidator;
import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Move;
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
    }

    public void play() {
        do {
            Move move;
            try {
                move = moveReader.readLine(); // parse exception throws to catch
                if (validator.isValid(move)) {
                    board.makeMove(move);
                    if (isVerbose) {
                        System.out.println(move); // print the move and board if verbose
                        System.out.println(board);
                    }
                } else {
                    System.out.println("That move is invalid.");
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (!board.isOver());

        System.out.println("The winner is " + board.getResult());
        System.out.println(board);
    }
}
