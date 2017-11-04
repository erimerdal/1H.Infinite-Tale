package map;

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

    private int calcAttackBonus(Tile fromTile)
    {
        return fromTile.getTerrain().getTotalAttBonus();
    }

    private int calcDefBonus(Tile toTile)
    {
        return toTile.getTerrain().getTotalDefBonus();
    }

    public GenericUnit[] calcDefCasulties()
    {
        return getDefenderArmy();
    }

    public GenericUnit[] calcAttCasulties()
    {
        return getAttackerArmy();
    }






}
