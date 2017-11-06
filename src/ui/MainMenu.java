package ui;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainMenu{
    MusicManager musicManager = new MusicManager();
    public String username;
    private GridPane mainMenuPane;
    private InputManager inputManager;

    public MainMenu(GridPane mainMenuPane, InputManager inputManager) {
        this.mainMenuPane = mainMenuPane;
        this.inputManager = inputManager;

        String playGame = "NEW GAME";

        Button playGameButton = new Button(playGame);

        mainMenuPane.setPadding(new Insets(15));
        mainMenuPane.setHgap(5);
        mainMenuPane.setVgap(5);
        mainMenuPane.setAlignment(Pos.CENTER);

        mainMenuPane.add(new Label("Username:"), 0, 0);
        TextField textField = new TextField();
        mainMenuPane.add(textField, 1, 0);
        username = textField.getText();

        mainMenuPane.add(playGameButton,1,5);

        //mainMenuPane.setStyle("-fx-background-color: black");
        mainMenuPane.setStyle("-fx-background-image: url(\"mainMenu.jpg\");-fx-background-repeat: stretch;   \n" +
                "    -fx-background-size: 1300 1000;\n" +
                "    -fx-background-position: center center;\n" +
                "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");

        playGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    inputManager.startGame();
                }
            }
        });

        Button goSettingsButton = new Button("SETTINGS");
        goSettingsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    inputManager.openSettings();
                }
            }
        });

        mainMenuPane.add(goSettingsButton,1,7);

        Button quitButton = new Button("QUIT");
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.quit();
                }
            }
        });

        mainMenuPane.add(quitButton, 1, 9);
    }
}
