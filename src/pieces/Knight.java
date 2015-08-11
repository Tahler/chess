package pieces;

import edu.neumont.pro180.chess.Main;
import edu.neumont.pro180.chess.model.Board;
import edu.neumont.pro180.chess.model.Tile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Knight extends Piece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public Collection<Tile> getValidMoves() {
        Collection<Tile> validMoves = new ArrayList<>();
        return validMoves;
    }

    @Override
    public boolean isLegalMove(Tile start, Tile end) {
        return (Math.abs(start.getX() - end.getX()) <= 2 && Math.abs(start.getX() - end.getX()) <= 2)
            && !((start.getX() == end.getX() || start.getY() == end.getY())
                || (Math.abs(end.getX() - start.getX()) == Math.abs(end.getY() - start.getY())));
    }

    @Override
    public String toString() {
        return "KNIGHT";
    }

    public Character toChar() {
        return 'N';
    }
}
