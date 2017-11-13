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

public class LoreMenu{
    private GridPane loreMenuPane;
    private InputManager inputManager;

    public LoreMenu(GridPane loreMenuPane, InputManager inputManager) {
        this.loreMenuPane = loreMenuPane;
        this.inputManager = inputManager;


        loreMenuPane.setPadding(new Insets(15));
        loreMenuPane.setHgap(5);
        loreMenuPane.setVgap(5);
        loreMenuPane.setAlignment(Pos.BOTTOM_CENTER);

        //mainMenuPane.setStyle("-fx-background-color: black");
        loreMenuPane.setStyle("-fx-background-image: url(\"loreMenu.jpg\");-fx-background-repeat: stretch;   \n" +
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

        loreMenuPane.add(returnMainMenuButton, 1, 9);
    }
}
