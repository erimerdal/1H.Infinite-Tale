package ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import map.MapData;
import game.FactionData;
import game.MapColor;

import java.util.ArrayList;

public class Map {
    private MapData mapData;
    private ArrayList<MapColor> colors;
    private ArrayList<Integer> factionIds;
    private StackPane mapPane;
    private ArrayList<Polygon> tilePols;
    private int mapWidth = 10;
    private int lastClicked = 0;

    public Map(StackPane mp) {
        mapPane = mp;
        colors = new ArrayList<>();
        factionIds = new ArrayList<>();
        mapData = new MapData();
        tilePols = new ArrayList<>();
    }

    public void updateMap(MapData md) {
        mapData = md;
        //mapPane.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        for(int i = 0; i < 100; i++) {
            tilePols.add(createTile(i));
        }
        System.out.println(mapPane.getWidth());
        drawMap();
    }

    public void addFaction(FactionData faction) {

    }

    public int highlight(double x, double y) {
        System.out.println(x);
        return 0;
    }

    private void drawMap() {
        mapPane.getChildren().setAll(tilePols);
    }

    private MapTile createTile(int id) {
        double topLeftX = 0 - mapPane.getWidth() / 2;
        double topLeftY = 0 - mapPane.getHeight() / 2;
        double side = mapPane.getWidth() / (mapWidth * 3 - 1);
        double r3 = Math.sqrt(3) * side / 2;
        MapTile hex = new MapTile(id, side, r3);

        hex.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(lastClicked < tilePols.size())
                    tilePols.get(lastClicked).setStrokeWidth(1);

                lastClicked = hex.getTileId();
                tilePols.get(lastClicked).setStrokeWidth(3);
            }
        });

        int col = id % (mapWidth * 2 - 1);
        int row = col / mapWidth;
        row = row + id / (mapWidth * 2 - 1) * 2;
        col %= mapWidth;

        if(row % 2 == 0) {
            hex.setTranslateX(topLeftX + col * side * 3 + side);
        }
        else {
            hex.setTranslateX(topLeftX + col * side * 3 + side * 5 / 2);
        }
        hex.setTranslateY(topLeftY + row * r3 + r3);

        return hex;
    }

    public int getLastClicked() {
        return lastClicked;
    }
}
