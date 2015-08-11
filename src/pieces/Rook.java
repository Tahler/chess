package pieces;

import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Rook extends Piece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public Collection<Tile> getValidMoves() {
        Collection<Tile> validMoves = new ArrayList<>();
        return validMoves;
    }

    @Override
    public String toString() {
        return "ROOK";
    }

    public Character toChar() {
        return 'R';
    }
}
