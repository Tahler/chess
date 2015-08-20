package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.core.Engine;
import edu.neumont.pro180.chess.core.algorithms.MoveValidator;
import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.core.model.Tile;
import edu.neumont.pro180.chess.io.FileMoveReader;
import edu.neumont.pro180.chess.io.MoveReader;
import edu.neumont.pro180.chess.io.UserMoveReader;

import java.io.FileNotFoundException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Main {
//    public static void main(String[] args) {
//        MoveReader mr = null;
//        Flag flag = null;
//
//        try {
//            if (args.length == 0) {
////                flag = Flag.NONE;
//                flag = Flag.VERBOSE;
//                mr = new UserMoveReader();
//            }
//            else if (args.length == 1) {
//                // Verbose user input
//                if (args[0].toLowerCase().equals("v")) {
//                    flag = Flag.VERBOSE;
//                    mr = new UserMoveReader();
//                // Non-verbose file input
//                } else {
//                    flag = Flag.NONE;
//                    mr = new FileMoveReader(args[0].trim());
//
//                }
//            } else { // args.length >= 2
//                // Verbose file input, v first
//                if (args[0].toLowerCase().equals("v")) {
//                    flag = Flag.VERBOSE;
//                    mr = new FileMoveReader(args[0].trim());
//                // Verbose file input, file first
//                } else if (args[1].toLowerCase().equals("v")) {
//                    flag = Flag.VERBOSE;
//                    mr = new FileMoveReader(args[0].trim());
//                }
//            }
//
//            new Engine(mr, flag).play();
//        } catch (FileNotFoundException e) {
//            System.out.println("File \"" + args[0] + "\" does not exist.");
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("CLI syntax incorrect");
//        }
//    }

    public static void main(String[] args) {
        Board b = new Board();
        MoveValidator mv = new MoveValidator(b);
        System.out.println(b);

        b.makeMove(new Move(0, 0, 0, 1));
        System.out.println(b.getLastMove());
        System.out.println(b);
        System.out.println(mv.isAttacked(new Tile(0, 6)));

        b.makeMove(new Move(0, 1, 1, 5));
        System.out.println(b.getLastMove());
        System.out.println(b);
        System.out.println(mv.isAttacked(new Tile(1, 5)));
    }

    public enum Flag {
        VERBOSE,
        NONE
    }
}
