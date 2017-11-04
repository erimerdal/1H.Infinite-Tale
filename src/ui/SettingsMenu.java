
package UI;


import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class SettingsMenu extends Application{
    Button button = new Button();
    MusicManager musicManager = new MusicManager();
     public static String username;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SETTINGS");
        button.setText("Mute");


        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,800,800);
        primaryStage.setScene(scene);

        Label label1 = new Label("Player Name:");
        TextField textField = new TextField ();
        StackPane.setAlignment(label1, Pos.TOP_CENTER);
        StackPane.setAlignment(textField, Pos.TOP_RIGHT);
        username = textField.getText();



        layout.getChildren().addAll(label1, textField);


        primaryStage.show();

    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            musicManager.setMuted();
        }
    };
    public static void main(String[] args) {
        launch(args);
    }

}
