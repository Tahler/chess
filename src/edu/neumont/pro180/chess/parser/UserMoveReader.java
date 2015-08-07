package edu.neumont.pro180.chess.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class UserMoveReader extends MoveReader {

    public static void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = null;
            while (true) {
                // Get user input
                System.out.print('>');
                line = reader.readLine().toLowerCase().trim();

                // Quit on blank enter
                if (!line.isEmpty()) getScript().add(MoveParser.parseCommand(line));
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
