package map;

import java.util.ArrayList;
import java.util.Random;

public class MapManager {
    private ArrayList<Province> provinces;
    private ArrayList<Tile> tiles;
    private int mapWidth = 8;
    public MapManager()
    {
        provinces = new ArrayList<>();
        tiles = new ArrayList<>();
        // Hardcoded map
        /*
        @TODO
            Implement data loading
         */
        Random rand = new Random();

        for(int i = 0; i < 10; i++) {
            Province prov = new Province(provinces.size());
            prov.setCurrentPop(100);
            prov.setOwnerId(0);
            provinces.add(prov);

            for(int j = 0; j < 5; j++) {
                Tile tile = new Tile(tiles.size(), prov);
                int r = rand.nextInt(4);
                if(r > 2)
                    tile.addUnits((int)(rand.nextInt(200)));
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
                int r = rand.nextInt(4);
                if(r > 2)
                    tile.addUnits((int)(rand.nextInt(200)));
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
                int r = rand.nextInt(4);
                if(r > 2)
                    tile.addUnits((int)(rand.nextInt(200)));
                tiles.add(tile);
            }
        }

        setNeighbours();
    }
    public MapData getMapData(int factionId)
    {
        MapData data = new MapData();
        for(int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if(tile.getOwner().getOwnerId() == factionId)
                data.ownedTile.add(tile);
            else {
                ArrayList<Integer> ownerNbs = tile.getOwner().getNeighbours();
                boolean visible = false;
                for(int j = 0; j < ownerNbs.size() && !visible; j++) {
                    if(provinces.get(ownerNbs.get(j)).getOwnerId() == factionId) {
                        data.open.add(tile);
                        visible = true;
                        break;
                    }
                }
                if(visible)
                    continue;

                data.closed.add(tile);
            }
        }
        for(int i = 0; i < provinces.size(); i++) {
            if(provinces.get(i).getOwnerId() == factionId)
                data.ownedProvince.add(provinces.get(i));
            else {
                ArrayList<Integer> ownerNbs = provinces.get(i).getNeighbours();
                boolean visible = false;
                for(int j = 0; j < ownerNbs.size() && !visible; j++) {
                    if(provinces.get(ownerNbs.get(j)).getOwnerId() == factionId) {
                        data.visible.add(provinces.get(i));
                        visible = true;
                        break;
                    }
                }
                if(visible)
                    continue;

                data.hidden.add(provinces.get(i));
            }
        }
        return data;
    }

    public BattleInfo moveUnits(int from, int to)
    {
        Tile fromTile = tiles.get(from);
        Tile toTile = tiles.get(to);

        if(fromTile == null || toTile == null)
            return null;

        int att = fromTile.getTotalUnits();
        int def = toTile.getTotalUnits();

        if(att < 1 || fromTile.getUnitsMoved())
            return null;

        int attId = fromTile.getTroops().get(0).getOwnerId();

        if (def < 1 || attId == toTile.getTroops().get(0).getOwnerId()) {
            toTile.addUnits(att, attId);
            toTile.setUnitsMoved(true);
            fromTile.removeUnits(att);
            return null;
        }
        /*
        @TODO
            Implement battle
         */

        // simple battle implementation
        BattleInfo battleInfo = new BattleInfo();
        battleInfo.attackerId = attId;
        battleInfo.defenderId = toTile.getOwner().getOwnerId();
        battleInfo.attackerArmy = att;
        battleInfo.defenderArmy = def;
        if(att > def) {
            toTile.removeUnits(def);
            toTile.addUnits(att - def, attId);
            toTile.setUnitsMoved(true);
            fromTile.removeUnits(att);
            battleInfo.hasAttackerWon = true;
        }
        else {
            fromTile.removeUnits(att);
            toTile.removeUnits(def - att);
        }


        return battleInfo;
    }

    public boolean recruitUnits(Tile thatTile, int number)
    {
        if(!thatTile.getOwner().recruitUnits(number))
            return false;

        thatTile.addUnits(number);
        return true;
    }

    public ArrayList<Tile> getTilesByLocation(int provinceId)
    {
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int i = 0; i < this.tiles.size(); i++)
            if(this.tiles.get(i).getOwner().getId() == provinceId)
                tiles.add(this.tiles.get(i));

        return tiles;
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

    public void endTurn() {
        for (int i = 0; i < tiles.size(); i++)
            tiles.get(i).setUnitsMoved(false);

        for (int i = 0; i < provinces.size(); i++)
            provinces.get(i).setCurrentPop((int)(provinces.get(i).getCurrentPop() * 1.1));
    }

    private void setNeighbours() {
        for(int i = 0; i < provinces.size(); i++) {
            ArrayList<Integer> nb = new ArrayList<>();
            int ownerId = provinces.get(i).getOwnerId();
            ArrayList<Tile> owned = getTilesByLocation(provinces.get(i).getId());
            for(int j = 0; j < owned.size(); j++) {
                ArrayList<Tile> nbTiles = getNeighbourTiles(owned.get(j).getId());

                for(int k = 0; k < nbTiles.size(); k++) {
                    Province owner = nbTiles.get(k).getOwner();
                    if(owner.getOwnerId() != ownerId && !nb.contains(owner.getId()))
                        nb.add(owner.getId());
                }
            }

            provinces.get(i).setNeighbours(nb);
        }
    }

    private ArrayList<Tile> getNeighbourTiles(int id) {
        ArrayList<Tile> nb = new ArrayList<>();
        int index = 0;

        //Top-left nb
        index = id - mapWidth;
        if(index > -1 && id % (mapWidth * 2 - 1) != 0)
            nb.add(tiles.get(index));

        //Top nb
        index = id - mapWidth * 2 + 1;
        if(index > -1)
            nb.add(tiles.get(index));

        //Top-right nb
        index = id - mapWidth + 1;
        if(index > -1 && id % (mapWidth * 2 - 1) != 9)
            nb.add(tiles.get(index));

        //Bottom-right nb
        index = id + mapWidth;
        if(index < tiles.size() && id % (mapWidth * 2 - 1) != 9)
            nb.add(tiles.get(index));

        //Bottom nb
        index = id + mapWidth * 2 - 1;
        if(index < tiles.size())
            nb.add(tiles.get(index));

        //Bottom-left nb
        index = id + mapWidth - 1;
        if(index < tiles.size() && id % (mapWidth * 2 - 1) != 0)
            nb.add(tiles.get(index));

        return nb;
    }
}
