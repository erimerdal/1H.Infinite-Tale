package ui;

import javafx.scene.layout.GridPane;

public class LoreManager {
    private MainMenu menu;

    public LoreManager(GridPane mainMenuPane, InputManager inputManager) {
        menu = new MainMenu(mainMenuPane, inputManager);
    }



}
