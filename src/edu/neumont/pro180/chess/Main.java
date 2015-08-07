package edu.neumont.pro180.chess;

import edu.neumont.pro180.chess.model.*;

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
        Board board = Board.getInstance();
        board.print();

        // Parse input to receive piece and location info

        board.placePiece(new Pawn(board, Color.DARK), 1, 0);
        board.placePiece(new Queen(board, Color.LIGHT), 2, 0);
        board.print();

        // Parse input to receive row and column info

        board.tryMove(4, 0, 4, 0, false);
        board.print();
    }
}
