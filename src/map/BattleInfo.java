
package map;
import java.util.ArrayList;

public class BattleInfo {
    public int attackerId;
    public int defenderId;
    public int attackerArmy;
    public int defenderArmy;
    public boolean hasAttackerWon;

    public BattleInfo()
    {
        attackerId = 0;
        defenderId = 0;
        attackerArmy = 0;
        defenderArmy = 0;
        hasAttackerWon = false;
    }
}
