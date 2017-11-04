package ui;

import game.GameManager;
import game.MapWrapper;
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
    private Scene gameScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hello World");

        SplitPane mainPane = new SplitPane();
        mainPane.setOrientation(Orientation.HORIZONTAL);

        StackPane mapPane = new StackPane();
        mapPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        SplitPane.setResizableWithParent(mapPane, false);
        mapPane.setMinSize(1024, 768);

        FlowPane headerPane = new FlowPane();
        headerPane.setMinHeight(50);
        headerPane.setMaxHeight(50);

        SplitPane leftPane = new SplitPane();
        leftPane.setOrientation(Orientation.VERTICAL);
        leftPane.getItems().add(headerPane);
        leftPane.getItems().add(mapPane);

        SplitPane infoPane = new SplitPane();
        infoPane.setOrientation(Orientation.VERTICAL);
        infoPane.getItems().add(new Button("first"));
        infoPane.getItems().add(new Button("second"));

        mainPane.getItems().add(leftPane);
        mainPane.getItems().add(infoPane);

        gameScene = new Scene(mainPane, 1280, 960);
        primaryStage.setScene(gameScene);
        primaryStage.show();

        GameManager gameManager = new GameManager();

        InputManager inputManager = new InputManager(this, gameManager);
        Map map = new Map(mapPane, inputManager);
        InformationHeader informationHeader = new InformationHeader(headerPane, inputManager);

        mapPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(map.getLastClicked() + " tıklandı");
            }
        });

        map.updateMap(new MapData());

        GridPane settingsPane = new GridPane();
        SettingsManager settingsManager = new SettingsManager(settingsPane, inputManager);
        settingsScene = new Scene(settingsPane, 1280, 960);
    }

    public void openSettings() {
        primaryStage.setScene(settingsScene);
        primaryStage.show();
    }

    public void closeSettings() {
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
