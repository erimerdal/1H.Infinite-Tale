package ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import map.MapData;
import game.FactionData;
import game.MapColor;
import map.Tile;

import java.util.ArrayList;

public class Map {
    private MapData mapData;
    private ArrayList<MapColor> colors;
    private StackPane mapPane;
    private ArrayList<MapTile> mapTiles;
    private int mapWidth = 10;
    private int numOfTiles = 250;
    private int lastClicked = 0;

    public Map(StackPane mp) {
        mapPane = mp;
        colors = new ArrayList<>();
        mapData = new MapData();
        mapTiles = new ArrayList<>();
        for(int i = 0; i < numOfTiles; i++) {
            mapTiles.add(createTile(i));
        }
        setNeighbours();
        drawMap();
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
            mapTiles.get(tile.getId()).setTileColor(colors.get(tile.getOwner().getId()));
            mapTiles.get(tile.getId()).updateTile(tile);
        }
        for(int i = 0; i < md.open.size(); i++) {
            Tile tile = md.open.get(i);
            if(tile == null)
                continue;

            mapTiles.get(tile.getId()).setHidden(false);
            mapTiles.get(tile.getId()).setTileColor(colors.get(tile.getOwner().getId()));
            mapTiles.get(tile.getId()).updateTile(tile);
        }
        for(int i = 0; i < md.closed.size(); i++) {
            Tile tile = md.closed.get(i);
            if(tile == null)
                continue;

            mapTiles.get(tile.getId()).setHidden(true);
            mapTiles.get(tile.getId()).setTileColor(colors.get(tile.getOwner().getId()));
            mapTiles.get(tile.getId()).updateTile(tile);
        }
        drawMap();
    }

    public void addFaction(FactionData faction) {

    }

    public int highlight(double x, double y) {
        System.out.println(x);
        return 0;
    }

    private void drawMap() {
        mapPane.getChildren().setAll(mapTiles);
    }

    private MapTile createTile(int id) {
        double topLeftX = 0 - mapPane.getWidth() / 2;
        double topLeftY = 0 - mapPane.getHeight() / 2;
        double side = mapPane.getWidth() / (mapWidth * 3 - 1);
        double r3 = Math.sqrt(3) * side / 2;
        MapTile mapTile = new MapTile(id, side, r3);

        mapTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    if (lastClicked < mapTiles.size())
                        mapTiles.get(lastClicked).setSelected(false);

                    lastClicked = mapTile.getTileId();
                    mapTiles.get(lastClicked).setSelected(true);
                }
                else if(event.getButton() == MouseButton.SECONDARY) {
                    MapTile last = mapTiles.get(lastClicked);
                    if(!last.isHidden() && last.getNumOfUnits() > 0 && last.isNeighbour(mapTile.getTileId())) {
                        System.out.println(last.getTileId() +  " sends soldiers to " + mapTile.getTileId());
                        /*
                        @TODO
                            Implement move units
                         */
                    }
                }
            }
        });

        int col = id % (mapWidth * 2 - 1);
        int row = col / mapWidth;
        row = row + id / (mapWidth * 2 - 1) * 2;
        col %= mapWidth;

        if(row % 2 == 0) {
            mapTile.setTranslateX(topLeftX + col * side * 3 + side);
        }
        else {
            mapTile.setTranslateX(topLeftX + col * side * 3 + side * 5 / 2);
        }
        mapTile.setTranslateY(topLeftY + row * r3 + r3);

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
            if(index > -1 && i % (mapWidth * 2 - 1) != 9)
                nb.add(mapTiles.get(index));

            //Bottom-right nb
            index = i + mapWidth;
            if(index < mapTiles.size() && i % (mapWidth * 2 - 1) != 9)
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
}
