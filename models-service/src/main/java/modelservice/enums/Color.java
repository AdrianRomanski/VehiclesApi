package modelservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum Color{
    BLACK(1, "Black"),
    SILVER(2, "Silver"),
    WHITE(3,"White"),
    GREY(4,"Grey"),
    BLUE(5,"Blue"),
    GREEN(6,"Green"),
    RED(7,"Red"),
    BEIGE(8, "Beige"),
    BROWN(9, "Brown"),
    LIGHT_BLUE(10,"Light Blue");

    private final int colorNumber;
    private final String color;

    private static final Map<Integer, String> colors = new HashMap<>();

    static {
        for (Color c : Color.values()) {
            colors.put(c.colorNumber, c.color);
        }
    }

    Color(int colorNumber, String color) {
        this.colorNumber = colorNumber;
        this.color = color;
    }

    public static Map<Integer, String> getColors() { return colors; }

    public String getColor() {
        return color;
    }
}