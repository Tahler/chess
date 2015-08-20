package edu.neumont.pro180.chess.io;

import java.io.*;

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
}
