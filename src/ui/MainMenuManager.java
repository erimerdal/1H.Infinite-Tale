package ui;

import javafx.scene.layout.GridPane;

public class MainMenuManager {

    private MainMenu menu;

    public MainMenuManager(GridPane mainMenuPane, InputManager inputManager) {
        menu = new MainMenu(mainMenuPane, inputManager);
    }

}
