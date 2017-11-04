package ui;

public class SettingsManager {
    String userName;
    SettingsMenu menu = new SettingsMenu();

    public void setUsername()
    {
        userName = SettingsMenu.username;
    }
    public String getUsername(){
        return userName;
    }

}