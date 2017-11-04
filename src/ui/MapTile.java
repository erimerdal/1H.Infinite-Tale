package ui;

import game.MapColor;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import map.Terrain;
import map.Tile;

import java.util.ArrayList;

public class MapTile extends Polygon {
    private int tileId;
    private MapColor tileColor;
    private int numOfUnits;
    private boolean hidden;
    private Color color;
    private Terrain terrain;
    private boolean selected;
    private boolean target;
    private ArrayList<MapTile> neighbours;
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
        selected = false;
        target = false;
        neighbours = new ArrayList<>();
        /*
        @TODO
            terrain initialization
         */

        setFill(color);
        setStroke(Color.LIGHTGRAY);
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

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setHidden(boolean h) {
        hidden = h;
        if(hidden) {
            color = tileColor.getFogColor();
            setFill(color);
            setStroke(Color.LIGHTGRAY);
            return;
        }

        color = tileColor.getColor();
        setFill(color);
        setStroke(Color.WHITE);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
        /*
        @TODO
            Implement visuals for different terrain types
         */
    }

    public void setSelected(boolean s) {
        selected = s;
        if(s) {
            setStrokeWidth(5);
        }
        else {
            setStrokeWidth(1);
        }
        setHidden(hidden);

        if(!hidden && numOfUnits > 0)
            for(int i = 0; i < neighbours.size(); i++) {
                neighbours.get(i).setTarget(s);
            }
    }

    public void setTarget(boolean target) {
        this.target = target;
        if(target)
            setStroke(Color.GREEN);
        else
            if(!selected)
                setHidden(hidden);
    }

    public void setNeighbours(ArrayList<MapTile> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isNeighbour(int id) {
        for(int i = 0; i < neighbours.size(); i++) {
            if(neighbours.get(i).getTileId() == id)
                return true;
        }
        return false;
    }
}
