
package map;
import java.util.Random;

public class BattleInfo {
    private int attackerId;
    private int defenderId;
    private GenericUnit[] attackerArmy;
    private GenericUnit[] defenderArmy;
    private boolean hasAttackerWon;

    public BattleInfo()
    {
        attackerId = 0;
        defenderId = 0;
        attackerArmy = new GenericUnit[0];
        defenderArmy = new GenericUnit[0];
        hasAttackerWon = false;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public int getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(int defenderId) {
        this.defenderId = defenderId;
    }

    public GenericUnit[] getAttackerArmy() {
        return attackerArmy;
    }

    public void setAttackerArmy(GenericUnit[] attackerArmy) {
        this.attackerArmy = attackerArmy;
    }

    public GenericUnit[] getDefenderArmy() {
        return defenderArmy;
    }

    public void setDefenderArmy(GenericUnit[] defenderArmy) {
        this.defenderArmy = defenderArmy;
    }

    public boolean getHasAttackerWon() {
        return hasAttackerWon;
    }

    public void setHasAttackerWon(boolean hasAttackerWon) {
        this.hasAttackerWon = hasAttackerWon;
    }

    private int calcAttackBonus(Tile toTile)
    {
        return toTile.getTerrain().getTotalAttBonus();
    }

    private int calcDefBonus(Tile toTile)
    {
        return toTile.getTerrain().getTotalDefBonus();
    }

    public boolean calcCasulties(Tile toTile)
    {
        // Calculate defense and attack bonuses

        int highDef = 100 + calcDefBonus(toTile);
        int lowDef = calcDefBonus(toTile);
        int highAtt = 100 + calcAttackBonus(toTile);
        int lowAtt = calcAttackBonus(toTile);

        // Now armies start hitting each other. Defense starts first
        // And rolls a dice depending on their bonuses.
        // First army to go under 5 men loses.

        while(true)
        {
            Random def = new Random();
            int defBonus = def.nextInt(highDef-lowDef) + lowDef;
            System.out.println("Defender rolled: " + defBonus);
            System.out.println("Defender army size: " + defenderArmy.length);
            int defKills = (defenderArmy.length * defBonus) / 100;
            System.out.println("Defense kills: " + defKills);
            if(defKills < 5)
                defKills = 5;

            int newAttackerLength = attackerArmy.length - defKills;
            // Checks if attacker army went below 5 or 0
            if(newAttackerLength < 5) {
                System.out.println("Number of attacker army: " + newAttackerLength);
                System.out.println("Attacker has lost.");
                // If this case happens, attackers lost.
                setHasAttackerWon(false);
                break;
            }
            GenericUnit[] newAttackerArmy = new GenericUnit[newAttackerLength];
            for(int i = 0; i < newAttackerLength; i++)
            {
                newAttackerArmy[i] = attackerArmy[i];
            }
            attackerArmy = newAttackerArmy;
            Random att = new Random();

            int attBonus = att.nextInt(highAtt-lowAtt) + lowAtt;
            System.out.println("Random attacker rolled: " + attBonus);
            System.out.println("Attacker army size: " + attackerArmy.length);
            int attKills = (attackerArmy.length * attBonus / 100);
            System.out.println("Attack kills: " + attKills);
            if(attKills < 5)
                attKills = 5;

            int newDefenderLength = defenderArmy.length - attKills;
            if(newDefenderLength < 5) {
                System.out.println("Number of defender army: " + newDefenderLength);
                System.out.println("Attacker has won.");
                // If this case happens, attackers lost.
                setHasAttackerWon(true);
                break;
            }
            GenericUnit[] newDefenderArmy = new GenericUnit[newDefenderLength];
            for(int j = 0; j < newDefenderLength; j++)
            {
                newDefenderArmy[j] = defenderArmy[j];
            }
            defenderArmy = newDefenderArmy;
        }

        // Since they might have gone below 5 or even 0, we reset the loser's soldiers to 0.
        if(hasAttackerWon)
        {
            GenericUnit[] newDefenderArmy = new GenericUnit[0];
            defenderArmy = newDefenderArmy;
        }
        else
        {
            GenericUnit[] newAttackerArmy = new GenericUnit[0];
            attackerArmy = newAttackerArmy;
        }

        return hasAttackerWon;
    }

}
