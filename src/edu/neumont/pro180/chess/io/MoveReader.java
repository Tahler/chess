package edu.neumont.pro180.chess.io;

import edu.neumont.pro180.chess.exception.IllegalMoveException;

import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class MoveReader {

    public void parseLine(String line) {
        try {
            MoveParser.parseCommand(line);
        } catch (ParseException e) {
            System.out.println("Bad input at: " + line + " (" + e.getMessage() + ")");
        } catch (IllegalMoveException e) {
            System.out.println("Bad input at: " + line + " (" + e.getMessage() + ")");
        }
    }

}
