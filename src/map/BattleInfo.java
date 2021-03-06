
package map;
import java.util.ArrayList;

public class BattleInfo {
    public int attackerId;
    public int defenderId;
    public int attackerArmy;
    public int defenderArmy;
    public boolean hasAttackerWon;
    public Tile attackerTile;
    public Tile defenderTile;

    public BattleInfo()
    {
        attackerId = 0;
        defenderId = 0;
        attackerArmy = 0;
        defenderArmy = 0;
        attackerTile = null;
        defenderTile = null;
        hasAttackerWon = false;
    }
}
