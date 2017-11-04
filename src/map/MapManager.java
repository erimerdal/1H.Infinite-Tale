package map;

import java.util.ArrayList;

public class MapManager {
    private ArrayList<Province> provinces;
    private ArrayList<Tile> tiles;
    public MapManager()
    {
        provinces = new ArrayList<>();
        tiles = new ArrayList<>();
        // Hardcoded map
        /*
        @TODO
            Implement data loading
         */

        for(int i = 0; i < 10; i++) {
            Province prov = new Province(provinces.size());
            prov.setCurrentPop(100);
            prov.setOwnerId(0);
            provinces.add(prov);

            for(int j = 0; j < 5; j++) {
                Tile tile = new Tile(tiles.size(), prov);
                tiles.add(tile);
            }
        }

        for(int i = 0; i < 10; i++) {
            Province prov = new Province(provinces.size());
            prov.setCurrentPop(100);
            prov.setOwnerId(1);
            provinces.add(prov);

            for(int j = 0; j < 5; j++) {
                Tile tile = new Tile(tiles.size(), prov);
                tiles.add(tile);
            }
        }

        for(int i = 0; i < 10; i++) {
            Province prov = new Province(provinces.size());
            prov.setCurrentPop(100);
            prov.setOwnerId(2);
            provinces.add(prov);

            for(int j = 0; j < 5; j++) {
                Tile tile = new Tile(tiles.size(), prov);
                tiles.add(tile);
            }
        }
    }
    public MapData getMapData(int factionId)
    {
        MapData data = new MapData();
        for(int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if(tile.getOwner().getOwnerId() == factionId)
                data.ownedTile.add(tile);
            else
                data.closed.add(tile);
        }
        for(int i = 0; i < provinces.size(); i++) {
            if(provinces.get(i).getOwnerId() == factionId)
                data.ownedProvince.add(provinces.get(i));
            else
                data.hidden.add(provinces.get(i));
        }
        return data;
    }

    public BattleInfo moveUnits(int from, int to)
    {
        Tile fromTile = tiles.get(from);
        Tile toTile = tiles.get(to);

        if(fromTile == null || toTile == null)
            return null;

        GenericUnit[] movingSoldiers = fromTile.getTroops();
        GenericUnit[] stayingSoldiers = toTile.getTroops();
        fromTile.removeUnits(movingSoldiers.length);
        toTile.addUnits(movingSoldiers.length);
        /* If it is enemy tile we should start a war. */
        BattleInfo battle = new BattleInfo();
        if(toTile.getOwner() != fromTile.getOwner())
        {
            battle.setAttackerArmy(movingSoldiers);
            battle.setDefenderArmy(stayingSoldiers);
            battle.calcCasulties(toTile);
        }
        return battle;
    }

    public boolean recruitUnits(Tile thatTile, int number)
    {
        boolean resultRecruit = thatTile.addUnits(number);
        return resultRecruit;
    }

    public ArrayList<Tile> getTileByLocation()
    {
        // What is this function supposed to do?
        return tiles;
    }

    public ArrayList<Province> getProvincesByOwner()
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

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Tile getTileById(int id) {
        return tiles.get(id);
    }
}
