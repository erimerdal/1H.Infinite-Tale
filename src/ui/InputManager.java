package ui;

import game.GameManager;
import game.TileInfo;
import map.BattleInfo;

public class InputManager {
    private UIManager uiManager;
    private GameManager gameManager;

    public InputManager(UIManager uiMan, GameManager gmMan) {
        uiManager = uiMan;
        gameManager = gmMan;
    }

    public boolean moveUnits(int from, int to) {
        BattleInfo battleInfo = gameManager.moveUnit(from, to);
        /*
        @TODO
            Implement battle result window
         */
        uiManager.updateHeader(gameManager.getFactionInfo(0));
        return (battleInfo != null);
    }

    public boolean recruitUnits(int amount, int loc) {
        boolean retValue = gameManager.recruitUnit(amount, loc);
        uiManager.updateHeader(gameManager.getFactionInfo(0));
        return retValue;
    }

    public boolean showTileInfo(int id) {
        TileInfo tileInfo = gameManager.getTileInfo(id);
        uiManager.showTileInfo(tileInfo);
        return (tileInfo != null);
    }

    public void endTurn() {
        gameManager.endTurn();
    }

    public void openSettings() {
        uiManager.openSettings();
    }

    public void closeSettings() {
        uiManager.closeSettings();
    }
}
