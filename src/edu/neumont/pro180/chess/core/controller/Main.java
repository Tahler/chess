package edu.neumont.pro180.chess.core.controller;

import edu.neumont.pro180.chess.core.controller.Engine;
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
        Flag flag = Flag.NONE;

        if (args.length == 0) {
            flag = Flag.VERBOSE;
            mr = new UserMoveReader();
        } else {
            for (String s : args) {
                if (s.toLowerCase().equals("v")) flag = Flag.VERBOSE;
                else try {
                    mr = new FileMoveReader(s.trim());
                } catch (FileNotFoundException e) {
                    System.out.println("File \"" + args[0] + "\" does not exist.");
                }
            }
        }

        new Engine(mr, flag).play();
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
