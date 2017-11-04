package ui;

import game.MapColor;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import map.Terrain;
import map.Tile;

public class MapTile extends Polygon {
    private int tileId;
    private MapColor tileColor;
    private int numOfUnits;
    private boolean hidden;
    private Color color;
    private Terrain terrain;
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
        tileColor = MapColor.RED;
        color = tileColor.getFogColor();
        numOfUnits = 0;
        hidden = true;
        /*
        @TODO
            terrain initialization
         */

        setFill(color);
        setStroke(MapColor.GRAY.getColor());
        setStrokeType(StrokeType.INSIDE);
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(color.brighter());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(color);
            }
        });
    }

    public int getTileId() {
        return tileId;
    }

    public boolean updateTile(Tile tile) {
        if(tileId != tile.getId())
            return false;

        setNumOfUnits(tile.getTroops().length);
        setTerrain(tile.getTerrain());

        return true;
    }

    public void setTileColor(MapColor c) {
        tileColor = c;
        setHidden(hidden);
        setFill(color);
    }

    public void setNumOfUnits(int n) {
        numOfUnits = n;
        /*
        @TODO
            Implement visuals for soldiers on map
         */
    }

    public void setHidden(boolean h) {
        hidden = h;
        if(hidden) {
            color = tileColor.getFogColor();
            setStroke(MapColor.GRAY.getColor());
            return;
        }

        color = tileColor.getColor();
        setStroke(MapColor.WHITE.getColor());
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
        /*
        @TODO
            Implement visuals for different terrain types
         */
    }
}
