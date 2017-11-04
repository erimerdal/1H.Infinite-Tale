package ui;

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

public class UIManager extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");

        SplitPane mainPane = new SplitPane();
        mainPane.setOrientation(Orientation.HORIZONTAL);

        StackPane mapPane = new StackPane();
        mapPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        SplitPane.setResizableWithParent(mapPane, false);
        mapPane.setMinSize(1024, 768);

        SplitPane infoPane = new SplitPane();
        infoPane.setOrientation(Orientation.VERTICAL);
        infoPane.getItems().add(new Button("first"));
        infoPane.getItems().add(new Button("second"));

        mainPane.getItems().add(mapPane);
        mainPane.getItems().add(infoPane);
        mainPane.setDividerPositions(0.8);

        primaryStage.setScene(new Scene(mainPane, 1280, 960));
        primaryStage.show();

        Map map = new Map(mapPane);

        mapPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(map.getLastClicked() + " tıklandı");
            }
        });

        map.updateMap(new MapData());
    }

    public static void main(String[] args)
    {
        game.MapWrapper mapWrapper = new MapWrapper();
        game.Faction erim = new game.Faction("Erim", 0, mapWrapper, true );
        game.Faction artificial = new game.Faction( "AI" , 1, mapWrapper, false);
        System.out.println(erim.getTreasury());

        launch(args);
    }
}
