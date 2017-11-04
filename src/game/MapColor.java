package game;

import javafx.scene.paint.Color;

public enum MapColor {
    RED(new Color(0.8, 0,0,1)),
    GREEN(new Color(0, 0.6,0,1)),
    BLUE(new Color(0, 0,0.8,1)),
    YELLOW(new Color(0.8, 0.8,0,1)),
    ORANGE(new Color(0.8, 0.4,0,1)),
    PURPLE(new Color(0.6, 0,0.6,1)),
    WHITE(new Color(1, 1,1,1)),
    GRAY(new Color(0.8, 0.8,0.8,1))
    ;

    private Color color;
    private MapColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public Color getFogColor() {
        return new Color(
                (color.getRed() + 1) / 2,
                (color.getGreen() + 1) / 2,
                (color.getBlue() + 1) / 2,
                1
        );
    }
}
