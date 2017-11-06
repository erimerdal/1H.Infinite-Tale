package ui;



import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class SettingsMenu{
    MusicManager musicManager = new MusicManager();

    private GridPane settingsPane;
    private InputManager inputManager;

    public SettingsMenu(GridPane settingsPane, InputManager inputManager) {
        this.settingsPane = settingsPane;
        this.inputManager = inputManager;

        String volume = "Mute";
        String noVolume = "Unmute";
        Button muteButton = new Button(volume);

        settingsPane.setPadding(new Insets(15));
        settingsPane.setHgap(5);
        settingsPane.setVgap(5);
        settingsPane.setAlignment(Pos.CENTER);

        settingsPane.add(muteButton,1,5);

        //settingsPane.setStyle("-fx-background-color: black");
        settingsPane.setStyle("-fx-background-image: url(\"settingsMenu.jpg\");-fx-background-repeat: stretch;   \n" +
                "    -fx-background-size: 1300 1000;\n" +
                "    -fx-background-position: center center;\n" +
                "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
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

        Button closeButton = new Button("Continue Game");
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    inputManager.closeSettings();
                }
            }
        });
        settingsPane.add(closeButton,1,7);


        Button backMainButton = new Button("Back to Main Menu");
        backMainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    inputManager.backMain();
                }
            }
        });
        settingsPane.add(backMainButton,1,9);



        Button quitGameButton = new Button("Quit Game");
        quitGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.quit();
                }
            }
        });
        settingsPane.add(quitGameButton, 1, 11);
    }
}
