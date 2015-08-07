package edu.neumont.pro180.chess.parser;

import edu.neumont.pro180.chess.exception.IllegalMoveException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class FileMoveReader extends MoveReader {
    /**
     * Reads all commands in a file and prints out the end result
     * @param fileName The text file containing the commands
     */
    public static void readFile(String fileName) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // TODO: abstract this somewhere else; UserMoveReader runs VERY similar code
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().trim();
                if (!line.isEmpty()) {
                    // TODO: parses and interprets twice
                    try {
                        MoveParser.parseCommand(line);
                    } catch (ParseException e) {
                        System.out.println("Bad input at: " + line);
                    } catch (IllegalMoveException e) {
                        System.out.println("Bad input at: " + line + " (" + e.getMessage() + ")");
                    }

                    // TODO: parses twice
                    // TODO: create toString in Move that is called after each line?
                    getScript().add(MoveParser.parseCommandForString(line));
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

        printDirectives();
    }
}
