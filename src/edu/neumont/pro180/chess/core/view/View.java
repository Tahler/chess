package edu.neumont.pro180.chess.core.view;

import edu.neumont.pro180.chess.core.model.Move;
import edu.neumont.pro180.chess.core.model.Piece;

import java.text.ParseException;

public interface View {
    void print(Object message);
    Move readMove() throws ParseException;
    Piece.Type getPawnPromotion();
}
