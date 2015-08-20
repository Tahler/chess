package edu.neumont.pro180.chess.core.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class Move {
    private final Tile start;
    private final Tile end;
    private Piece mover;

    public Move(Integer x1, Integer y1, Integer x2, Integer y2) {
        this(new Tile(x1, y1), new Tile(x2, y2));
    }

    public Move(Tile start, Tile end) {
        this.start = start;
        this.end = end;
    }

    public Tile getStart() {
        return start;
    }
    public Tile getEnd() {
        return end;
    }

//    public Piece getMover() {
//        return mover;
//    }

    public void execute(Piece[][] board) {
        int x1 = getStart().x;
        int y1 = getStart().y;
        int x2 = getEnd().x;
        int y2 = getEnd().y;

        Piece p = board[y1][x1];

        if(p != null) {
            mover = p;
            board[y2][x2] = board[y1][x1];
            board[y1][x1] = null;
        }

        // Castling
        Move castle = null;
        if (y1 == 0 && x1 == 4 && x2 == 6) { // white short castle
            castle = new Move(7, 0, 5, 0);
        } else if (y1 == 0 && x1 == 4 && x2 == 2) { //white long castle
            castle = new Move(0, 0, 3, 0);
        } else if (y1 == 7 && x1 == 4 && x2 == 6) { //black short castle
            castle = new Move(7, 7, 5, 7);
        } else if (y1 == 7 && x1 == 4 && x2 == 2) { //black long castle
            castle = new Move(0, 7, 3, 7);
        }
        if (castle != null) {
            castle.execute(board); // recurse, moving the rook now
        }
    }

    @Override
    public String toString() {
        if (mover != null) return "Moving the " + mover.toStringTeam() + " from " + start.toString() + " to " + end.toString();
        else return "Moving from " + start.toString() + " to " + end.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (start != null ? !start.equals(move.start) : move.start != null) return false;
        return !(end != null ? !end.equals(move.end) : move.end != null);
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    //    @Override
//    public Iterator<Tile> iterator() {
//        Board board = Board.getInstance();
//        Collection<Tile> path = new ArrayList<>();
//
//        int x = start.getY();
//        int y = start.getX();
//
//        int endX = end.getY();
//        int endY = end.getX();
//
//        // 1 for further down, -1 for further back, 0 for static
//        int dirX = ((endX > x) ? 1 : (x == endX) ? 0 : -1);
//        int dirY = ((endY > y) ? 1 : (y == endY) ? 0 : -1);
//
//        Tile cursor = start;
//        while (cursor != end) {
//            // Add before: the path should not include the starting tile.
//            x += dirX;
//            y += dirY;
//
//            // Reassign the cursor tile
//            cursor = board.getTile(x, y);
//
//            // Finally, add the cursor tile
//            path.add(cursor);
//        }
//
//        return path.iterator();
//    }
}
