package edu.neumont.pro180.chess.core.view;

import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.core.model.Piece;
import edu.neumont.pro180.chess.core.view.io.MoveReader;
import edu.neumont.pro180.chess.core.view.io.UserMoveReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class ConsoleIO implements View {

    private final MoveReader moveReader;

    public ConsoleIO() {
        moveReader = new UserMoveReader();
    }

    @Override
    public void print(Object message) {
        System.out.println(message);
    }

    @Override
    public Move readMove() throws ParseException {
        System.out.print(">");
        Move move = moveReader.readLine();
        return move;
    }

    @Override
    public Piece.Type getPawnPromotion() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean validInput = false;
        do {
            System.out.println("What would you like to promote your pawn to? ");
            System.out.print(">");
            try {
                String response = in.readLine().toUpperCase();
                switch (response) {
                    case "QUEEN":
                    case "Q":
                        return Piece.Type.QUEEN;
                    case "KNIGHT":
                    case "N":
                    case "K":
                        return Piece.Type.KNIGHT;
                    case "BISHOP":
                    case "B":
                        return Piece.Type.BISHOP;
                    case "ROOK":
                    case "R":
                        return Piece.Type.ROOK;
                    default:
                        System.out.println("Please specifiy (Q)ueen, (K)night, (B)ishop, or (R)ook");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while(!validInput);
        return null;
    }
}
