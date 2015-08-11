package edu.neumont.pro180.chess.io;

import edu.neumont.pro180.chess.exception.IllegalMoveException;

import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class MoveReader {

    public String parseLine(String line) {
        String move = null;
        try {
            move = MoveParser.parseCommand(line);
        } catch (Exception e) {
            System.out.println("Bad input at: " + line + " (" + e.getMessage() + ")");
        }
        return move;
    }

}
