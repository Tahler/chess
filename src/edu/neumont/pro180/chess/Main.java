package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.core.*;
import edu.neumont.pro180.chess.io.FileMoveReader;
import edu.neumont.pro180.chess.io.UserMoveReader;

import java.io.FileNotFoundException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) new UserMoveReader().start();
        else try {
            if (args[0].trim().toLowerCase().equals("v")) {
                new FileMoveReader().readFile(args[1], FileMoveReader.Flag.VERBOSE);
            } else {
                new FileMoveReader().readFile(args[0], FileMoveReader.Flag.NONE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + args[0] + "\" does not exist.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("CLI syntax incorrect.");
        }

        Board.getInstance().print();
    }
}
