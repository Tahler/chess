package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Color;
import edu.neumont.pro180.chess.model.Pawn;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Main {
    public static void main(String[] args) {
    // Module0
//        if (args.length == 0) UserMoveReader.start();
//        else try {
//            FileMoveReader.readFile(args[0]);
//        } catch (FileNotFoundException e) {
//            System.out.println("File \"" + args[0] + "\" does not exist.");
//        }

    // Module1
        Board board = new Board();
        board.print();

//        board.getTiles()[0][0].setPiece(new Pawn());
        new Pawn(board, Color.DARK, board.getTiles()[0][0]); // places a piece

//        board.makeMove(0, 0, 0, 0, new Pawn(board, Color.DARK, board.getTiles()[0][0]), null);
        board.print();
    }
}
