package edu.neumont.pro180.chess.io;

import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Move;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import static edu.neumont.pro180.chess.parser.MoveParser.parseCommand;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class FileMoveReader extends MoveReader {

    public enum Flag {
        VERBOSE,
        NONE
    }

    /**
     * Reads all commands in a file and prints out the end result
     * @param fileName The text file containing the commands
     */
    public void readFile(String fileName, Flag flag) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // If the "v" flag was made, need to print the board before moves
        if (flag.equals(Flag.VERBOSE)) {
            Board.getInstance().print();
        }

        // TODO: abstract this somewhere else; UserMoveReader runs VERY similar code
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().trim();
                if (!line.isEmpty() && !line.startsWith("//")) { // skip this line if it is empty or a comment
                    // TODO: parses and interprets twice
                    String move = null;
                    try {
                        move = parseCommand(line);
                    } catch (ParseException e) {
                        System.out.println("Bad input at: " + line);
                    } catch (IllegalMoveException e) {
                        System.out.println("Bad input at: " + line + " (" + e.getMessage() + ")");
                    }

                    // If "v" was flagged, print every move to the console.
                    if (flag.equals(Flag.VERBOSE)) {
                        System.out.println(move);
                        Board.getInstance().print();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
