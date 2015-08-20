package edu.neumont.pro180.chess.core.model;

/**
 * Created by Tyler Berry on 8/7/2015.
 */
public enum Color {
    LIGHT,
    DARK;

    public Color swap() {
        return swap(this);
    }

    public static Color swap(Color color) {
        if (color.equals(Color.LIGHT)) {
            return Color.DARK;
        } else {
            return Color.LIGHT;
        }
    }
}
