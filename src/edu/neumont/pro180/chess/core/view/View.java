package edu.neumont.pro180.chess.core.view;

import edu.neumont.pro180.chess.core.model.Move;

import java.text.ParseException;

public interface View {
    void print(Object message);
    Move readMove() throws ParseException;
}
