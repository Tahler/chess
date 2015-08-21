package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.core.Engine;
import edu.neumont.pro180.chess.core.algorithms.MoveValidator;
import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Color;
import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.core.model.Tile;
import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.io.FileMoveReader;
import edu.neumont.pro180.chess.io.MoveReader;
import edu.neumont.pro180.chess.io.UserMoveReader;

import java.io.FileNotFoundException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Main {
    public static void main(String[] args) {
        MoveReader mr = null;
        Flag flag = null;

        try {
            if (args.length == 0) {
                flag = Flag.VERBOSE;
                mr = new UserMoveReader();
            }
            else if (args.length == 1) {
                // Verbose user input
                if (args[0].toLowerCase().equals("v")) {
                    flag = Flag.VERBOSE;
                    mr = new UserMoveReader();
                // Non-verbose file input
                } else {
                    flag = Flag.NONE;
                    mr = new FileMoveReader(args[0].trim());

                }
            } else { // args.length >= 2
                // Verbose file input, v first
                if (args[0].toLowerCase().equals("v")) {
                    flag = Flag.VERBOSE;
                    mr = new FileMoveReader(args[0].trim());
                // Verbose file input, file first
                } else if (args[1].toLowerCase().equals("v")) {
                    flag = Flag.VERBOSE;
                    mr = new FileMoveReader(args[0].trim());
                }
            }

            new Engine(mr, flag).play();
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + args[0] + "\" does not exist.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("CLI syntax incorrect");
        }
    }

//    public static void main(String[] args) {
//        Board b = new Board();
//        MoveValidator mv = new MoveValidator(b);
//        System.out.println(b);
//
//        Move myMove = new Move(4, 6, 3, 6);
//
//        System.out.println(myMove + " would " + (!mv.wouldPlaceKingInCheck(myMove) ? "not " : "") + "put the king in check");
//
////        try {
////            mv.validate(myMove);
//            b.makeMove(myMove);
////        } catch (IllegalMoveException e) {
////            e.printStackTrace();
////        }
//
//        System.out.println(b);
//        System.out.println(mv.isAttacked(b.lightKingLocation, Color.LIGHT) ? "is in check" : "is not in check");
//    }

    public enum Flag {
        VERBOSE,
        NONE
    }
}
