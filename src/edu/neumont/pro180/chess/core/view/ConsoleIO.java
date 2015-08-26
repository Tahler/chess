package edu.neumont.pro180.chess.core.view;

import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.core.view.io.MoveReader;
import edu.neumont.pro180.chess.core.view.io.UserMoveReader;

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
}
