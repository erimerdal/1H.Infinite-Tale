package ui;

import game.FactionData;
import game.GameManager;
import game.TileInfo;
import map.BattleInfo;

public class InputManager {
    private static InputManager singleton = null;
    private UIManager uiManager;
    private GameManager gameManager;
    private boolean mapView = false;

    private InputManager(UIManager uiMan, GameManager gmMan) {
        uiManager = uiMan;
        gameManager = gmMan;
    }

    public static InputManager getInputManager(UIManager uiMan, GameManager gmMan) {
        if(singleton == null)
            singleton = new InputManager(uiMan, gmMan);

        return singleton;
    }

    public boolean moveUnits(int from, int to) {
        BattleInfo battleInfo = gameManager.moveUnit(from, to);
        /*
        @TODO
            Implement battle result window
         */
        uiManager.updateHeader(gameManager.getFactionInfo(0));
        uiManager.updateMap(gameManager.getPlayerMap());
        return (battleInfo != null);
    }

    public boolean recruitUnits(int amount, int loc) {
        boolean retValue = gameManager.recruitUnit(amount, loc);
        uiManager.updateHeader(gameManager.getFactionInfo(0));
        uiManager.updateMap(gameManager.getPlayerMap());
        return retValue;
    }

    public boolean showTileInfo(int id) {
        TileInfo tileInfo = gameManager.getTileInfo(id);
        if(tileInfo == null)
            return false;
        uiManager.showFactionInfo(gameManager.getFactionInfo(tileInfo.tile.getOwner().getOwnerId()));
        uiManager.showTileInfo(tileInfo);
        return true;
    }

    public void endTurn() {
        gameManager.endTurn();
        uiManager.updateHeader(gameManager.getFactionInfo(0));
        uiManager.updateMap(gameManager.getPlayerMap());
    }

    public void setMusicMute(boolean mute) {
        uiManager.setMusicMute(mute);
    }

    public void openSettings() {
        uiManager.openSettings();
    }

    public void closeSettings() {
        uiManager.closeSettings();
    }

    public void startGame() {
        uiManager.startGame();
    }

    public void returnMain(){
        uiManager.backMain();
    }

    public void openLore(){
        uiManager.openLore();
    }
    public void openCredits(){
        uiManager.openCredits();
    }

    public void backMain() {
        uiManager.backMain();
    }

    public void quit() {
        uiManager.quit();
    }

    public void swapMapView() {
        mapView = !mapView;
        uiManager.setMapView(mapView);
    }
}
