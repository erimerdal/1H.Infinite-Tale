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
    public String username;
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

        settingsPane.add(new Label("Username:"), 0, 0);
        TextField textField = new TextField();
        settingsPane.add(textField, 1, 0);
        username = textField.getText();

        settingsPane.add(muteButton,1,5);

        settingsPane.setStyle("-fx-background-color: black");
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

        Button closeButton = new Button("Close Settings");
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    inputManager.closeSettings();
                }
            }
        });
        settingsPane.add(closeButton,1,7);
    }
}
