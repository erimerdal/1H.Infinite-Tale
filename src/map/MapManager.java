package map;

public class MapManager {
    private Province[] provinces;
    private Tile[] tiles;
    public MapManager()
    {
        provinces = new Province[0];
        tiles = new  Tile[0];
    }
    public MapData getMapData()
    {
        MapData data = new MapData();
        return data;
    }

    public boolean moveUnits(Tile toTile, Tile fromTile)
    {
        GenericUnit[] movingSoldiers = fromTile.getTroops();
        GenericUnit[] stayingSoldiers = toTile.getTroops();
        fromTile.removeUnits(movingSoldiers.length);
        toTile.addUnits(movingSoldiers.length);
        /* If it is enemy tile we should start a war. */
        if(toTile.getOwner() != fromTile.getOwner())
        {
            BattleInfo battle = new BattleInfo();
            battle.setAttackerArmy(movingSoldiers);
            battle.setDefenderArmy(stayingSoldiers);
            battle.calcCasulties(toTile);
        }
        return true;
    }

    public boolean recruitUnits(Tile thatTile, int number)
    {
        boolean resultRecruit = thatTile.addUnits(number);
        return resultRecruit;
    }

    public Tile[] getTileByLocation()
    {
        // What is this function supposed to do?
        return tiles;
    }

    public Province[] getProvincesByOwner()
    {
        // About owners?
        return provinces;
    }

    public boolean updatePopulation(Tile thatTile)
    {
        boolean add = false;
        boolean remove = false;
        add = thatTile.addUnits(0);
        remove = thatTile.removeUnits(0);
        if( add && remove )
            return true;
        return false;
    }

    public Province[] getProvinces() {
        return provinces;
    }

    public void setProvinces(Province[] provinces) {
        this.provinces = provinces;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }
}
