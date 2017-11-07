package ui;

import javafx.scene.layout.GridPane;

public class SettingsManager {

    private SettingsMenu menu;

    public SettingsManager(GridPane settingsPane, InputManager inputManager) {
        menu = new SettingsMenu(settingsPane, inputManager);
    }

    public void setMusicMute(boolean mute) {
        menu.setMusicMute(mute);
    }
}