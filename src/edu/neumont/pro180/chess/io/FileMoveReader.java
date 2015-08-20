package edu.neumont.pro180.chess.io;

import edu.neumont.pro180.chess.core.model.Move;

import java.io.*;
import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class FileMoveReader extends MoveReader {
    public FileMoveReader(String fileName) throws FileNotFoundException {
        this(new File(fileName));
    }

    public FileMoveReader(File file) throws FileNotFoundException {
        super(new BufferedReader(new FileReader(file)));
    }

    /**
     public void readFile(String fileName, Flag flag) throws FileNotFoundException {
     BufferedReader reader = new BufferedReader(new FileReader(fileName));

     // If the "v" flag was made, need to print the board before moves
     if (flag.equals(Flag.VERBOSE)) {
     Board.getInstance().print();
     }

     String line;
     try {
     while ((line = reader.readLine()) != null) {
     line = line.toLowerCase().trim();
     if (!line.isEmpty() && !line.startsWith("//")) { // skip this line if it is empty or a comment
     String move = super.parseLine(line);

     // If "v" was flagged, print every move to the console.
     if (flag.equals(Flag.VERBOSE) && move != null) {
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
     */
}
