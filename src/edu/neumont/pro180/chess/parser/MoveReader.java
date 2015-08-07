package edu.neumont.pro180.chess.parser;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public abstract class MoveReader {
    private static Queue<String> script = new LinkedList<>();

    protected static Queue<String> getScript() {
        return script;
    }

    // TODO: can i change to return a string and have main print?
    protected static void printDirectives() {
        String directive = null;
        while ((directive = script.poll()) != null) {
            System.out.println(directive);
        }
    }
}
