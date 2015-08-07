package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Color;
import edu.neumont.pro180.chess.model.Move;
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

        board.placePiece(new Pawn(board, Color.DARK), 1, 0);
        board.placePiece(new Pawn(board, Color.LIGHT), 2, 0);
        board.print();

        board.makeMove(1, 0, 2, 0);
        board.print();
    }
}
