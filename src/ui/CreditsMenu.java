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

public class CreditsMenu{
    private GridPane creditsPane;
    private InputManager inputManager;

    public CreditsMenu(GridPane creditsPane, InputManager inputManager) {
        this.creditsPane = creditsPane;
        this.inputManager = inputManager;


        creditsPane.setPadding(new Insets(15));
        creditsPane.setHgap(5);
        creditsPane.setVgap(5);
        creditsPane.setAlignment(Pos.BOTTOM_CENTER);

        creditsPane.setStyle("-fx-background-image: url(\"creditsMenu.jpg\");-fx-background-repeat: stretch;   \n" +
                "    -fx-background-size: 1300 1000;\n" +
                "    -fx-background-position: center center;\n" +
                "    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");

        Button returnMainMenuButton = new Button("RETURN");
        returnMainMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    inputManager.returnMain();
                }
            }
        });

        creditsPane.add(returnMainMenuButton, 1, 9);
    }
}

