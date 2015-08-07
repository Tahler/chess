package edu.neumont.pro180.chess.parser;

import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.model.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class UserMoveReader extends MoveReader {

    public static void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Show user the board before asking for first move
        Board.getInstance().print();

        try {
            String line = null;
            while (true) {
                // Get user input
                System.out.print('>');
                line = reader.readLine().toLowerCase().trim();

                // Quit on blank enter
                if (!line.isEmpty()) {
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

                    Board.getInstance().print();
                }
                else break;
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
