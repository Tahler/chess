import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tyler Berry on 8/5/2015.
 */
public class Module0 {
    private static Pattern piecePlacementPattern = Pattern.compile("([kqbnrp])([ld])([a-h])([1-8])");
    private static Pattern singlePieceMovementPattern = Pattern.compile("([a-h])([1-8])([a-h])([1-8])(\\*)?");
    private static Pattern twoPieceMovementPattern = Pattern.compile("([a-h])([1-8])([a-h])([1-8])([a-h])([1-8])([a-h])([1-8])");

    private static Queue<String> script = new LinkedList<>();

    public static void main(String[] args) {
        if (args.length == 0) startUserInput();
        else startFileInput(args[0]);
    }

    private static void startUserInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = null;
            while (true) {
                // Get user input
                System.out.print('>');
                line = reader.readLine().toLowerCase().trim();

                // Quit on blank enter
                if (line.equals("")) break;

                Directives userDirective = parseDirective(line);
                if (userDirective == null) {
                    System.out.println("Bad input");
                    continue;
                }
                switch (userDirective) {
                    case PIECE_PLACEMENT:
                        Matcher matcher1 = piecePlacementPattern.matcher(line);
                        matcher1.find();
                        String readable1 = "Place " +
                                getColor(matcher1.group(2)) + " " +
                                getPiece(matcher1.group(1)) +
                                " on " + matcher1.group(3).toUpperCase() + matcher1.group(4);
                        script.add(readable1);
                        break;
                    case SINGLE_PIECE_MOVEMENT:
                        Matcher matcher2 = singlePieceMovementPattern.matcher(line);
                        matcher2.find();
                        String readable2 = "Move the piece on " +
                                matcher2.group(1).toUpperCase() + matcher2.group(2) +
                                " to " +
                                matcher2.group(3).toUpperCase() + matcher2.group(4);
                        script.add(readable2);
                        break;
                    case TWO_PIECE_MOVEMENT:
                        Matcher matcher3 = twoPieceMovementPattern.matcher(line);
                        matcher3.find();
                        String readable3 = "Move the piece on " +
                                matcher3.group(1).toUpperCase() + matcher3.group(2) +
                                " to " +
                                matcher3.group(3).toUpperCase() + matcher3.group(4) +
                                " and the piece on " +
                                matcher3.group(5).toUpperCase() + matcher3.group(6) +
                                " to " +
                                matcher3.group(7).toUpperCase() + matcher3.group(8);
                        script.add(readable3);
                        break;
                }
//                printDirectives();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printDirectives();
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

    private static void printDirectives() {
        String directive = null;
        while ((directive = script.poll()) != null) {
            System.out.println(directive);
        }
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

    private static void startFileInput(String fileName) {

    }

    private enum Directives {
        PIECE_PLACEMENT,
        SINGLE_PIECE_MOVEMENT,
        TWO_PIECE_MOVEMENT
    }

}
