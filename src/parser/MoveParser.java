package parser;

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
     public static String parseCommand(String command) {
        Directives userDirective = parseDirective(command);

        // If the input is bad,
        if (userDirective == null) {
            return "Bad input at: " + command;
        }

        switch (userDirective) {
            case PIECE_PLACEMENT:
                Matcher matcher1 = piecePlacementPattern.matcher(command);
                matcher1.find();
                String readable1 = "Place " +
                        getColor(matcher1.group(2)) + " " +
                        getPiece(matcher1.group(1)) +
                        " on " + matcher1.group(3).toUpperCase() + matcher1.group(4);
                return readable1;
            case SINGLE_PIECE_MOVEMENT:
                Matcher matcher2 = singlePieceMovementPattern.matcher(command);
                matcher2.find();
                String readable2 = "Move the piece on " +
                        matcher2.group(1).toUpperCase() + matcher2.group(2) +
                        " to " +
                        matcher2.group(3).toUpperCase() + matcher2.group(4);
                return readable2;
            case TWO_PIECE_MOVEMENT:
                Matcher matcher3 = twoPieceMovementPattern.matcher(command);
                matcher3.find();
                String readable3 = "Move the piece on " +
                        matcher3.group(1).toUpperCase() + matcher3.group(2) +
                        " to " +
                        matcher3.group(3).toUpperCase() + matcher3.group(4) +
                        " and the piece on " +
                        matcher3.group(5).toUpperCase() + matcher3.group(6) +
                        " to " +
                        matcher3.group(7).toUpperCase() + matcher3.group(8);
                return readable3;
            default:
                return "Something went wrong in MoveParser.parseCommand()";
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

    private static String getColor(String color) {
        switch (color) {
            case "l":
                return "LIGHT";
            case "d":
                return "DARK";
            default:
                return null;
        }
    }

    private static String getPiece(String piece) {
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

    private enum Directives {
        PIECE_PLACEMENT,
        SINGLE_PIECE_MOVEMENT,
        TWO_PIECE_MOVEMENT
    }
}
