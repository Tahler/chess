package edu.neumont.pro180.chess.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class UserMoveReader extends MoveReader {
    public UserMoveReader() {
        super(new BufferedReader(new InputStreamReader(System.in)));
    }
}
