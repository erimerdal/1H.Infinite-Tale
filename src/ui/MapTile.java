package ui;

import game.MapColor;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import map.Terrain;
import map.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MapTile extends Polygon {
    private int tileId;
    private int provId;
    private MapColor tileColor;
    private int numOfUnits;
    private boolean hidden;
    private Color color;
    private Terrain terrain;
    private boolean selected;
    private boolean target;
    private boolean provSelected;
    private ArrayList<MapTile> neighbours;
    private Label soldierLabel;
    private MapColor soldierColor;
    private Polygon highlighter;

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
        provId = -1;
        tileColor = MapColor.RED;
        soldierColor = tileColor;
        color = tileColor.getFogColor();
        numOfUnits = 0;
        hidden = true;
        selected = false;
        target = false;
        provSelected = false;
        neighbours = new ArrayList<>();
        soldierLabel = new Label("");
        soldierLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 12;-fx-background-color: white;");
        soldierLabel.setTextFill(soldierColor.getColor());
        highlighter = new Polygon();
        highlighter.getPoints().setAll(getPoints());
        highlighter.setScaleX(0.9);
        highlighter.setScaleY(0.9);
        highlighter.setFill(new Color(0,0,0,0));
        highlighter.setStroke(new Color(0.2,1,0.2,0.8));
        highlighter.setStrokeType(StrokeType.INSIDE);
        highlighter.setStrokeWidth(0);
        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(3);
        boxBlur.setHeight(3);
        boxBlur.setIterations(3);
        highlighter.setEffect(boxBlur);
        highlighter.setMouseTransparent(true);
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

    public int getProvId() {
        return provId;
    }

    public boolean updateTile(Tile tile) {
        if(tileId != tile.getId())
            return false;

        setNumOfUnits(tile.getTotalUnits());
        setTerrain(tile.getTerrain());
        provId = tile.getOwner().getId();

        return true;
    }

    public void setTileColor(MapColor c) {
        tileColor = c;
        setHidden(hidden);
        setFill(color);
    }

    public void setSoldierColor(MapColor c) {
        soldierColor = c;
        soldierLabel.setTextFill(soldierColor.getColor());
    }

    public void setNumOfUnits(int n) {
        numOfUnits = n;
        if(n > 0 && !hidden) {
            soldierLabel.setText(" S: " + n + " ");
            // Tried to add soldier picture but weirdly label went şeffaf instead of picture.
            //soldierLabel.setStyle("-fx-background-image: url(\"/Users/erimerdal/Desktop/TerminalKanser/src/soldiers.jpg\");");
        }
        else
            soldierLabel.setText("");
        /*
        @TODO
            Implement visuals for soldiers on map
         */
    }

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setProvSelected(boolean selected) {
        provSelected = selected;
        Color col = (Color) getFill();
        if(selected) {
            setStroke(col.invert());
            setStrokeWidth(3);
        }
        else {
            setHidden(hidden);
            setStrokeWidth(1);
        }
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
            highlighter.setStrokeWidth(6);
            highlighter.setStroke(new Color(1, 1, 1,0.8));
        }
        else
            if(target) {
                highlighter.setStrokeWidth(4);
                highlighter.setStroke(new Color(0.2,1,0.2,0.8));
            }
            else
                highlighter.setStrokeWidth(0);

        if(!hidden && numOfUnits > 0)
            for(int i = 0; i < neighbours.size(); i++) {
                neighbours.get(i).setTarget(s);
            }
    }

    public void setTarget(boolean target) {
        this.target = target;
        if(target) {
            highlighter.setStrokeWidth(4);
            highlighter.setStroke(new Color(0.2,1,0.2,0.8));
        }
        else
            if(!selected)
                highlighter.setStrokeWidth(0);
            else {
                highlighter.setStrokeWidth(6);
                highlighter.setStroke(new Color(1, 1, 1,0.8));
            }
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

    public Label getSoldierLabel() {
        return soldierLabel;
    }

    public Polygon getHighlighter() {
        return highlighter;
    }

    public void updateLabelLoc() {

        soldierLabel.setTranslateX(getTranslateX());
        soldierLabel.setTranslateY(getTranslateY());
    }

    public void updateHighlighterLoc() {
        highlighter.setTranslateX(getTranslateX());
        highlighter.setTranslateY(getTranslateY());
    }
}
