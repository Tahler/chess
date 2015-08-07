package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().trim();
                if (!line.isEmpty()) getScript().add(MoveParser.parseCommand(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printDirectives();
    }
}
