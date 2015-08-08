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
    private static Pattern singlePieceMovementPattern = Pattern.compile("([a-h])([1-8])([a-h])([1-8])(\\*)?");
    private static Pattern twoPieceMovementPattern = Pattern.compile("([a-h])([1-8])([a-h])([1-8])([a-h])([1-8])([a-h])([1-8])");

    /**
     * Parses a command like 'e4d4' and sends a new Move to the Board, if successfully parsed
     * @param command The short command, either a single piece movement or a two piece movement.
     *                Single piece:
     *                              This case-insensitive directive is in the format [column][row][column][row][is-capture]:
     *                              column = [a-h]
     *                              row = [1-8]
     *                              is-capture = *
     *                Two piece:
     *                              This case-insensitive directive is in the format [column][row][column][row][column][row][column][row]:
     *                              column = [a-h]
     *                              row = [1-8]
     * @throws ParseException Thrown if the command is of the incorrect syntax or out of bounds.
     * @throws IllegalMoveException Thrown if the move is successfully parsed, but is not valid.
     */
    public static void parseCommand(String command) throws ParseException, IllegalMoveException {
        Directive userDirective = parseDirective(command);

        // If the input is bad throw a ParseException
        if (userDirective == null) {
            throw new ParseException("Bad input at: " + command + ". (Syntax error)", 0);
        }

        Matcher matcher = null;
        switch (userDirective) {
            case SINGLE_PIECE_MOVEMENT:
                matcher = singlePieceMovementPattern.matcher(command);
                matcher.find();

                Board.getInstance().tryMove(
                        getRow(matcher.group(2)), getColumn(matcher.group(1)),
                        getRow(matcher.group(4)), getColumn(matcher.group(3)),
                        getIsCapture(matcher.group(5))
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

    private static Directive parseDirective(String directive) {
        if (singlePieceMovementPattern.matcher(directive).matches()) {
            return Directive.SINGLE_PIECE_MOVEMENT;
        }
        if (twoPieceMovementPattern.matcher(directive).matches()) {
            return Directive.TWO_PIECE_MOVEMENT;
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

    private static boolean getIsCapture(String asterisk) {
        if (asterisk == null) return false;
        else return true;
    }

    private enum Directive {
        SINGLE_PIECE_MOVEMENT,
        TWO_PIECE_MOVEMENT
    }
}
