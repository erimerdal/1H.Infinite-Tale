
package ui;



import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;




public class SettingsMenu extends Application{

    MusicManager musicManager = new MusicManager();
     public static String username;



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SETTINGS");
        String volume = "Mute";
        String noVolume = "Unmute";
        Button muteButton = new Button(volume);




        GridPane layout = new GridPane();

        layout.setPadding(new Insets(15));
        layout.setHgap(5);
        layout.setVgap(5);
        layout.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(layout,500,600);

        layout.add(new Label("Username:"), 0, 0);
        TextField textField = new TextField();
        layout.add(textField, 1, 0);
        username = textField.getText();

        layout.add(muteButton,1,5);

        layout.setStyle("-fx-background-color: black");
        muteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(muteButton.getText()=="Mute")
                {
                    muteButton.setText(noVolume);
                }
                else
                {
                    muteButton.setText(volume);
                }
                musicManager.setMuted();

            }
        });

        primaryStage.setScene(myScene);
        primaryStage.show();


    }
/*
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            musicManager.setMuted();

        }
    };
    */


    public static void main(String[] args) {
        launch(args);
    }



}
