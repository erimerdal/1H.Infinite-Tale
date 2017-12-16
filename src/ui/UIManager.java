package ui;

import game.FactionData;
import game.GameManager;
import game.TileInfo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import map.MapData;

public class UIManager extends Application {
    private Stage primaryStage;
    private Scene settingsScene;
    private Scene loreScene;
    private Scene gameScene;
    private Scene mainMenuScene;
    private Scene creditsScene;
    private TileInfoWindow tileInfoWindow;
    private FactionInfoWindow factionInfoWindow;
    private InformationHeader informationHeader;
    private Map map;
    private SettingsManager settingsManager;
    private LoreManager loreManager;
    private CreditsManager creditsManager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Infinite Tale Game");

        SplitPane mainPane = new SplitPane();
        mainPane.setOrientation(Orientation.HORIZONTAL);

        ScrollPane mapPane = new ScrollPane();

        FlowPane headerPane = new FlowPane();
        headerPane.setMinHeight(30);
        headerPane.setMaxHeight(30);

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
        infoPane.setMinWidth(200);
        infoPane.setMaxWidth(200);

        mainPane.getItems().add(leftPane);
        mainPane.getItems().add(infoPane);

        gameScene = new Scene(mainPane, 1280, 960);

        primaryStage.setScene(gameScene);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(960);
        primaryStage.show();

        GameManager gameManager = new GameManager();

        InputManager inputManager = new InputManager(this, gameManager);
        map = new Map(mapPane, inputManager);
        informationHeader = new InformationHeader(headerPane, inputManager);
        tileInfoWindow = new TileInfoWindow(tileInfoPane, inputManager);
        factionInfoWindow = new FactionInfoWindow(factionInfoPane, inputManager);

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
        // TESTING
        primaryStage.setScene(gameScene);
        setMusicMute(true);
        primaryStage.show();

        GridPane lorePane = new GridPane();
        loreManager = new LoreManager(lorePane, inputManager);
        loreScene = new Scene(lorePane, 1280,960);

        GridPane creditsPane = new GridPane();
        creditsManager = new CreditsManager(creditsPane, inputManager);
        creditsScene = new Scene(creditsPane, 1280,960);

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

    public void openCredits() {
        primaryStage.setScene(creditsScene);
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
