package ui;

import javafx.scene.layout.GridPane;

public class MainMenuManager {
    private String userName;
    private MainMenu menu;

    public MainMenuManager(GridPane mainMenuPane, InputManager inputManager) {
        menu = new MainMenu(mainMenuPane, inputManager);
    }

    public void setUsername()
    {
        userName = menu.username;
    }
    public String getUsername(){
        return userName;
    }

    public MusicManager returnMusicManager()
    {
        return menu.musicManager;
    }


}
