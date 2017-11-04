package ui;

import javafx.scene.layout.GridPane;

public class SettingsManager {
    private String userName;
    private SettingsMenu menu;

    public SettingsManager(GridPane settingsPane, InputManager inputManager) {
        menu = new SettingsMenu(settingsPane, inputManager);
    }

    public void setUsername()
    {
        userName = menu.username;
    }
    public String getUsername(){
        return userName;
    }
}