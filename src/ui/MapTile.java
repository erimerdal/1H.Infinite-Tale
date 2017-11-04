package ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

public class MapTile extends Polygon {
    private int tileId;
    public MapTile(int id, double side, double r3) {
        super(new double[]{
                0, r3,
                side / 2, 0,
                side * 3 / 2, 0,
                side * 2, r3,
                side * 3 / 2, r3 * 2,
                side / 2, r3 * 2
        });
        tileId = id;

        setFill(new Color(1,0,0,1));
        setStroke(new Color(1,1,1,1));
        setStrokeType(StrokeType.INSIDE);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(new Color(1,0.3,0.3,1));
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(new Color(1,0,0,1));
            }
        });
    }

    public int getTileId() {
        return tileId;
    }
}
