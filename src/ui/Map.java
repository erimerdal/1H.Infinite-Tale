package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import map.MapData;
import game.FactionData;
import game.MapColor;
import map.Tile;

import java.util.ArrayList;

public class Map {
    private MapData mapData;
    private InputManager inputManager;
    private ArrayList<MapColor> colors;
    private StackPane mapPane;
    private ArrayList<MapTile> mapTiles;
    private ArrayList<Label> soldierLabels;
    private ArrayList<Polygon> highlighters;
    private int mapWidth = 8;
    private int mapHeight = 20;
    private int numOfTiles = 150;
    private int lastClicked = 0;
    private double side = 50;

    public Map(ScrollPane mp, InputManager im) {

        mapPane = new StackPane();
        mapPane.setMinSize(1150,910);
        mp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mp.setPannable(true);
        mp.setContent(new Group(mapPane));

        inputManager = im;
        colors = new ArrayList<>();
        mapData = new MapData();
        mapTiles = new ArrayList<>();
        soldierLabels = new ArrayList<>();
        highlighters = new ArrayList<>();
        for(int i = 0; i < numOfTiles; i++) {
            mapTiles.add(createTile(i));
        }

        colors.add(MapColor.RED);
        colors.add(MapColor.BLUE);
        colors.add(MapColor.PURPLE);

        setNeighbours();
        drawMap();

        mp.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double scaleRatio = (double)newValue / mapPane.getWidth();

                if(scaleRatio > mapPane.getScaleX()) {
                    mapPane.setScaleX(scaleRatio);
                    mapPane.setScaleY(scaleRatio);
                }
            }
        });

        mp.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double scaleRatio = (double)newValue / mapPane.getHeight();

                if(scaleRatio > mapPane.getScaleY()) {
                    mapPane.setScaleX(scaleRatio);
                    mapPane.setScaleY(scaleRatio);
                }
            }
        });


        mapPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if(event.getDeltaY() > 0) {
                    mapPane.setScaleX(mapPane.getScaleX() * 1.1);
                    mapPane.setScaleY(mapPane.getScaleY() * 1.1);
                }
                else {
                    double widthScale = mp.getWidth() / mapPane.getWidth();
                    double heightScale = mp.getHeight() / mapPane.getHeight();

                    if(heightScale > widthScale)
                        widthScale = heightScale;

                    if(widthScale < (mapPane.getScaleX() / 1.1))
                        widthScale = mapPane.getScaleX() / 1.1;

                    mapPane.setScaleX(widthScale);
                    mapPane.setScaleY(widthScale);
                }

                event.consume();
            }
        });
    }

    public void updateMap(MapData md) {
        if(md == null)
            return;

        mapData = md;
        for(int i = 0; i < md.ownedTile.size(); i++) {
            Tile tile = md.ownedTile.get(i);
            if(tile == null)
                continue;

            mapTiles.get(tile.getId()).setHidden(false);
            mapTiles.get(tile.getId()).setTileColor(colors.get(tile.getOwner().getOwnerId()));
            if(tile.getTotalUnits() > 0)
                mapTiles.get(tile.getId()).setSoldierColor(colors.get(tile.getTroops().get(0).getOwnerId()));
            mapTiles.get(tile.getId()).updateTile(tile);
        }
        for(int i = 0; i < md.open.size(); i++) {
            Tile tile = md.open.get(i);
            if(tile == null)
                continue;

            mapTiles.get(tile.getId()).setHidden(false);
            mapTiles.get(tile.getId()).setTileColor(colors.get(tile.getOwner().getOwnerId()));
            if(tile.getTotalUnits() > 0)
                mapTiles.get(tile.getId()).setSoldierColor(colors.get(tile.getTroops().get(0).getOwnerId()));
            mapTiles.get(tile.getId()).updateTile(tile);
        }
        for(int i = 0; i < md.closed.size(); i++) {
            Tile tile = md.closed.get(i);
            if(tile == null)
                continue;

            mapTiles.get(tile.getId()).setHidden(true);
            mapTiles.get(tile.getId()).setTileColor(colors.get(tile.getOwner().getOwnerId()));
            if(tile.getTotalUnits() > 0)
                mapTiles.get(tile.getId()).setSoldierColor(colors.get(tile.getTroops().get(0).getOwnerId()));
            mapTiles.get(tile.getId()).updateTile(tile);
        }
        drawMap();
    }

    public void addFaction(FactionData faction) {
        // @TODO implementation
    }

    private void drawMap() {
        mapPane.getChildren().setAll(mapTiles);
        mapPane.getChildren().addAll(soldierLabels);
        mapPane.getChildren().addAll(highlighters);
    }

    private MapTile createTile(int id) {
        double r3 = Math.sqrt(3) * side / 2;
        double topLeftX = 0 - (mapWidth - 1) * 3 * side / 2;
        double topLeftY = 0 - (mapHeight * r3 + r3) / 2;
        MapTile mapTile = new MapTile(id, side, r3);

        mapTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    if (lastClicked < mapTiles.size())
                        mapTiles.get(lastClicked).setSelected(false);

                    lastClicked = mapTile.getTileId();
                    mapTiles.get(lastClicked).setSelected(true);
                    highlightProvince(mapTile.getProvId());
                    inputManager.showTileInfo(lastClicked);
                }
                else if(event.getButton() == MouseButton.SECONDARY) {
                    MapTile last = mapTiles.get(lastClicked);
                    last.setSelected(false);
                    if(!last.isHidden() && last.getNumOfUnits() > 0 && last.isNeighbour(mapTile.getTileId())) {
                        inputManager.moveUnits(last.getTileId(), mapTile.getTileId());
                    }
                }
            }
        });

        int col = id % (mapWidth * 2 - 1);
        int row = col / mapWidth;
        row = row + id / (mapWidth * 2 - 1) * 2;
        col %= mapWidth;
        if(row % 2 == 0) {
            mapTile.setTranslateX(topLeftX + col * side * 3);
        }
        else {
            mapTile.setTranslateX(topLeftX + col * side * 3 + side * 3 / 2);
        }
        mapTile.setTranslateY(topLeftY + row * r3 + r3);

        mapTile.updateLabelLoc();
        mapTile.updateHighlighterLoc();
        soldierLabels.add(mapTile.getSoldierLabel());
        highlighters.add(mapTile.getHighlighter());

        return mapTile;
    }

    public int getLastClicked() {
        return lastClicked;
    }

    private void setNeighbours() {
        for(int i = 0; i < mapTiles.size(); i++) {
            ArrayList<MapTile> nb = new ArrayList<>();
            int index = 0;

            //Top-left nb
            index = i - mapWidth;
            if(index > -1 && i % (mapWidth * 2 - 1) != 0)
                nb.add(mapTiles.get(index));

            //Top nb
            index = i - mapWidth * 2 + 1;
            if(index > -1)
                nb.add(mapTiles.get(index));

            //Top-right nb
            index = i - mapWidth + 1;
            if(index > -1 && i % (mapWidth * 2 - 1) != 7)
                nb.add(mapTiles.get(index));

            //Bottom-right nb
            index = i + mapWidth;
            if(index < mapTiles.size() && i % (mapWidth * 2 - 1) != 7)
                nb.add(mapTiles.get(index));

            //Bottom nb
            index = i + mapWidth * 2 - 1;
            if(index < mapTiles.size())
                nb.add(mapTiles.get(index));

            //Bottom-left nb
            index = i + mapWidth - 1;
            if(index < mapTiles.size() && i % (mapWidth * 2 - 1) != 0)
                nb.add(mapTiles.get(index));

            mapTiles.get(i).setNeighbours(nb);
        }
    }

    private void highlightProvince(int provId) {
        for(int i = 0; i < mapTiles.size(); i++) {
            if(mapTiles.get(i).getProvId() == provId)
                mapTiles.get(i).setProvSelected(true);
            else
                mapTiles.get(i).setProvSelected(false);
        }
    }
}
