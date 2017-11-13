package ui;

import game.FactionData;
import game.GameManager;
import game.MapWrapper;
import game.TileInfo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import map.MapData;

import java.util.Set;

public class UIManager extends Application {
    private Stage primaryStage;
    private Scene settingsScene;
    private Scene loreScene;
    private Scene gameScene;
    private Scene mainMenuScene;
    private TileInfoWindow tileInfoWindow;
    private FactionInfoWindow factionInfoWindow;
    private InformationHeader informationHeader;
    private Map map;
    private SettingsManager settingsManager;
    private LoreManager loreManager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Infinite Tale Game");

        SplitPane mainPane = new SplitPane();
        mainPane.setOrientation(Orientation.HORIZONTAL);

        StackPane mapPane = new StackPane();
        mapPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        SplitPane.setResizableWithParent(mapPane, false);
        mapPane.setMinSize(1024, 768);

        FlowPane headerPane = new FlowPane();
        headerPane.setMinHeight(35);
        headerPane.setMaxHeight(35);

        SplitPane leftPane = new SplitPane();
        leftPane.setOrientation(Orientation.VERTICAL);
        leftPane.getItems().add(headerPane);
        leftPane.getItems().add(mapPane);

        GridPane tileInfoPane = new GridPane();
        GridPane factionInfoPane = new GridPane();

        SplitPane infoPane = new SplitPane();
        infoPane.setOrientation(Orientation.VERTICAL);
        infoPane.getItems().add(tileInfoPane);
        infoPane.getItems().add(factionInfoPane);

        mainPane.getItems().add(leftPane);
        mainPane.getItems().add(infoPane);

        gameScene = new Scene(mainPane, 1280, 960);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        GameManager gameManager = new GameManager();

        InputManager inputManager = new InputManager(this, gameManager);
        map = new Map(mapPane, inputManager);
        informationHeader = new InformationHeader(headerPane, inputManager);
        tileInfoWindow = new TileInfoWindow(tileInfoPane, inputManager);
        factionInfoWindow = new FactionInfoWindow(factionInfoPane, inputManager);

        mapPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(map.getLastClicked() + " tıklandı");
            }
        });

        map.updateMap(gameManager.getPlayerMap());
        updateHeader(gameManager.getFactionInfo(0));

        GridPane settingsPane = new GridPane();
        settingsManager = new SettingsManager(settingsPane, inputManager);
        settingsScene = new Scene(settingsPane, 1280, 960);

        //////////

        GridPane mainMenuPane = new GridPane();
        MainMenuManager mainMenuManager = new MainMenuManager(mainMenuPane, inputManager);
        mainMenuScene = new Scene(mainMenuPane, 1280,960);

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();

        GridPane lorePane = new GridPane();
        LoreManager loreManager = new LoreManager(lorePane, inputManager);
        loreScene = new Scene(lorePane, 1280,960);

        //////////
    }

    public void openSettings() {
        primaryStage.setScene(settingsScene);
        primaryStage.show();
    }

    public void closeSettings() {
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    public void startGame() {
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    public void openLore() {
        primaryStage.setScene(loreScene);
        primaryStage.show();
    }

    public void quit() {
        System.exit(0);
    }

    public void backMain(){
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    public void setMusicMute(boolean mute) {
        settingsManager.setMusicMute(mute);
    }

    public void showTileInfo(TileInfo tileInfo) {
        tileInfoWindow.update(tileInfo);
    }

    public void showFactionInfo(FactionData factionData) {
        factionInfoWindow.update(factionData);
    }

    public void updateHeader(FactionData factionData) {
        informationHeader.update(factionData);
    }

    public void updateMap(MapData mapData) {
        map.updateMap(mapData);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
