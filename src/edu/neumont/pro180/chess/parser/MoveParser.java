package edu.neumont.pro180.chess.parser;

import edu.neumont.pro180.chess.exception.IllegalMoveException;
import edu.neumont.pro180.chess.model.*;

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
     * Takes in a command like 'kld8' and returns a readable String like 'Place LIGHT KING on D8'
     * @param command The command directive
     * @return The human readable directive
     */
     public static String parseCommandForString(String command) {
        Directives userDirective = parseDirective(command);

        // If the input is bad,
        if (userDirective == null) {
            return "Bad input at: " + command;
        }

        switch (userDirective) {
            case PIECE_PLACEMENT:
                Matcher matcher1 = piecePlacementPattern.matcher(command);
                matcher1.find();
                return "Place " +
                        getColorString(matcher1.group(2)) + " " +
                        getPieceString(matcher1.group(1)) +
                        " on " + matcher1.group(3).toUpperCase() + matcher1.group(4);
            case SINGLE_PIECE_MOVEMENT:
                Matcher matcher2 = singlePieceMovementPattern.matcher(command);
                matcher2.find();
                return "Move the piece on " +
                        matcher2.group(1).toUpperCase() + matcher2.group(2) +
                        " to " +
                        matcher2.group(3).toUpperCase() + matcher2.group(4);
            case TWO_PIECE_MOVEMENT:
                Matcher matcher3 = twoPieceMovementPattern.matcher(command);
                matcher3.find();
                return "Move the piece on " +
                        matcher3.group(1).toUpperCase() + matcher3.group(2) +
                        " to " +
                        matcher3.group(3).toUpperCase() + matcher3.group(4) +
                        " and the piece on " +
                        matcher3.group(5).toUpperCase() + matcher3.group(6) +
                        " to " +
                        matcher3.group(7).toUpperCase() + matcher3.group(8);
            default:
                return "Something went wrong in MoveParser.parseCommandForString()";
        }
    }

    public static void parseCommand(String command) throws ParseException, IllegalMoveException {
        Directives userDirective = parseDirective(command);

        // If the input is bad,
        if (userDirective == null) {
            throw new ParseException("Bad input at: " + command, 0);
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

                Board.getInstance().tryMove(
                        getRow(matcher.group(2)), getColumn(matcher.group(1)),
                        getRow(matcher.group(4)), getColumn(matcher.group(3)),
                        getCapture(matcher.group(5))
                );
                break;
            case TWO_PIECE_MOVEMENT:
                matcher = twoPieceMovementPattern.matcher(command);
                matcher.find();

                Board.getInstance().tryMove(
                        getRow(matcher.group(2)), getColumn(matcher.group(1)),
                        getRow(matcher.group(4)), getColumn(matcher.group(3)),
                        false
                );
                Board.getInstance().tryMove(
                        getRow(matcher.group(6)), getColumn(matcher.group(5)),
                        getRow(matcher.group(8)), getColumn(matcher.group(7)),
                        false
                );
                break;
        }
    }

    private static Directives parseDirective(String directive) {
        if (piecePlacementPattern.matcher(directive).matches()) {
            return Directives.PIECE_PLACEMENT;
        }
        if (singlePieceMovementPattern.matcher(directive).matches()) {
            return Directives.SINGLE_PIECE_MOVEMENT;
        }
        if (twoPieceMovementPattern.matcher(directive).matches()) {
            return Directives.TWO_PIECE_MOVEMENT;
        }
        return null;
    }

    private static Integer getRow(String row) {
        return (Board.BOARD_WIDTH - Integer.valueOf(row));
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

    private static String getColorString(String color) {
        switch (color) {
            case "l":
                return "LIGHT";
            case "d":
                return "DARK";
            default:
                return null;
        }
    }

    private static String getPieceString(String piece) {
        switch (piece) {
            case "k":
                return "KING";
            case "q":
                return "QUEEN";
            case "b":
                return "BISHOP";
            case "n":
                return "KNIGHT";
            case "r":
                return "ROOK";
            case "p":
                return "PAWN";
            default:
                return null;
        }
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

    private enum Directives {
        PIECE_PLACEMENT,
        SINGLE_PIECE_MOVEMENT,
        TWO_PIECE_MOVEMENT
    }
}
