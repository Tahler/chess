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

    public enum Flag {
        VERBOSE,
        NONE
    }
}
