package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.model.*;
import edu.neumont.pro180.chess.parser.FileMoveReader;
import edu.neumont.pro180.chess.parser.UserMoveReader;

import java.io.FileNotFoundException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) UserMoveReader.start();
        else try {
            FileMoveReader.readFile(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + args[0] + "\" does not exist.");
        }

        Board.getInstance().print();
    }
}
