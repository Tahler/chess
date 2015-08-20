package edu.neumont.pro180.chess.io;

import edu.neumont.pro180.chess.Main;
import edu.neumont.pro180.chess.core.model.Board;
import edu.neumont.pro180.chess.core.model.Move;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class UserMoveReader extends MoveReader {

    public UserMoveReader() {
        super(new BufferedReader(new InputStreamReader(System.in)));
    }

//    public void start() {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        // Show user the board before asking for first move
//        Board.getInstance().print();
//
//        try {
//            String line;
//            while (true) {
//                // Get user input
//                System.out.print('>');
//                line = reader.readLine().toLowerCase().trim();
//
//                // Quit on blank enter
//                if (!line.isEmpty()) {
//                    String move = super.parseLine(line);
//                    if (move != null) {
//                        System.out.println(move);
//                        Board.getInstance().print();
//                    }
//                }
//                else break;
//            }
//        } catch (IOException e) {
//            // Error with readLine(), never seen it thrown
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                // Error with closing, never seen it thrown
//                e.printStackTrace();
//            }
//        }
//    }
}
