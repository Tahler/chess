package edu.neumont.pro180.chess.core.view;

/**
 * Created by Carver Anglin on 8/25/2015.
 */
public class ConsoleIO implements View {

    @Override
    public void print(Object message) {
        System.out.println(message);
    }
}
