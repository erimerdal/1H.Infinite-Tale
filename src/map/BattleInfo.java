
package map;
import java.util.ArrayList;

public class BattleInfo {
    public int attackerId;
    public int defenderId;
    public ArrayList<GenericUnit> attackerArmy;
    public ArrayList<GenericUnit> defenderArmy;
    public boolean hasAttackerWon;

    public BattleInfo()
    {
        attackerId = 0;
        defenderId = 0;
        attackerArmy = new ArrayList<>();
        defenderArmy = new ArrayList<>();
        hasAttackerWon = false;
    }
}
