package edu.neumont.pro180.chess.parser;

import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.model.*;
import pieces.*;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public class MoveParser {
    private static Pattern piecePlacementPattern = Pattern.compile("([kqbnrp])([ld])([a-h])([1-8])");
    private static Pattern singlePieceMovementPattern = Pattern.compile("([a-h])([1-8])([a-h])([1-8])(\\*)?");
    private static Pattern twoPieceMovementPattern = Pattern.compile("([a-h])([1-8])([a-h])([1-8])([a-h])([1-8])([a-h])([1-8])");

    /**
     * Parses and passes directives onto the board.
     * @param command The directive to be parsed. Example: kld8
     * @return The resulting Move's toString()
     * @throws ParseException Thrown if the directive cannot be parsed.
     * @throws IllegalMoveException Thrown if the move is invalid.
     */
    public static String parseCommand(String command) throws ParseException, IllegalMoveException {
        Directive userDirective = parseDirective(command);

        // If the input is bad,
        if (userDirective == null) {
            throw new ParseException("Syntax error", 0);
        }

        Matcher matcher = null;
        switch (userDirective) {
            case PIECE_PLACEMENT:
                matcher = piecePlacementPattern.matcher(command);
                matcher.find();
                Board.getInstance().placePiece(
                        getPiece(matcher.group(1), getColor(matcher.group(2))),
                        getRow(matcher.group(4)), getColumn(matcher.group(3))
                );
                break;
            case SINGLE_PIECE_MOVEMENT:
                matcher = singlePieceMovementPattern.matcher(command);
                matcher.find();

                return Board.getInstance().tryMove(
                        getRow(matcher.group(2)), getColumn(matcher.group(1)),
                        getRow(matcher.group(4)), getColumn(matcher.group(3)),
                        getCapture(matcher.group(5))
                ).toString();
            case TWO_PIECE_MOVEMENT:
                matcher = twoPieceMovementPattern.matcher(command);
                matcher.find();

                String result = "";
                result += Board.getInstance().tryMove(
                        getRow(matcher.group(2)), getColumn(matcher.group(1)),
                        getRow(matcher.group(4)), getColumn(matcher.group(3)),
                        false
                ).toString();
                result += Board.getInstance().tryMove(
                        getRow(matcher.group(6)), getColumn(matcher.group(5)),
                        getRow(matcher.group(8)), getColumn(matcher.group(7)),
                        false
                ).toString();
                return result;
        }
        return null;
    }

    private static Directive parseDirective(String directive) {
        if (piecePlacementPattern.matcher(directive).matches()) {
            return Directive.PIECE_PLACEMENT;
        }
        if (singlePieceMovementPattern.matcher(directive).matches()) {
            return Directive.SINGLE_PIECE_MOVEMENT;
        }
        if (twoPieceMovementPattern.matcher(directive).matches()) {
            return Directive.TWO_PIECE_MOVEMENT;
        }
        return null;
    }

    private static Integer getRow(String row) {
        return (Board.WIDTH - Integer.valueOf(row));
    }

    private static Integer getColumn(String column) {
        char c = column.charAt(0);
        int temp = (int) c;
        if (temp >= 97 && temp <= 122) return (temp - 97); // 97 is the value of ascii 'a'
        else return null; // Shouldn't happen
    }

    private static boolean getCapture(String asterisk) {
        if (asterisk == null) return false;
        else return true;
    }

    private static Color getColor(String color) {
        switch (color) {
            case "l":
                return Color.LIGHT;
            case "d":
                return Color.DARK;
            default:
                return null;
        }
    }

    private static Piece getPiece(String piece, Color color) {
        switch (piece) {
            case "k":
                return new King(Board.getInstance(), color);
            case "q":
                return new Queen(Board.getInstance(), color);
            case "b":
                return new Bishop(Board.getInstance(), color);
            case "n":
                return new Knight(Board.getInstance(), color);
            case "r":
                return new Rook(Board.getInstance(), color);
            case "p":
                return new Pawn(Board.getInstance(), color);
            default:
                return null;
        }
    }

    private enum Directive {
        PIECE_PLACEMENT,
        SINGLE_PIECE_MOVEMENT,
        TWO_PIECE_MOVEMENT
    }
}
