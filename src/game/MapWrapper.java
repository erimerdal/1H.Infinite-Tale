package game;

import map.BattleInfo;
import map.MapData;
import map.MapManager;
import map.Tile;

public class MapWrapper {
    private int id;
    private int factionId;
    private MapManager mapManager;

    public MapWrapper(int id, int factionId, MapManager mapManager){
        this.id = id;
        this.factionId = factionId;
        this.mapManager = mapManager;
    }

    public BattleInfo moveUnits(int from, int to) {
        Tile owned = mapManager.getTileById(from);

        if(owned.getTotalUnits() < 1 || owned.getTroops().get(0).getOwnerId() != factionId)
            return null;

        return mapManager.moveUnits(from, to);
    }

    public boolean recruitUnits(int amount, int loc) {
        Tile lctn = mapManager.getTileById(loc);
        if(lctn.getOwner().getOwnerId() != factionId)
            return false;

        return mapManager.recruitUnits(lctn, amount);
    }

    public int calculateMoney() {
        return collectTaxes() - payWages();
    }

    public MapData getMapData() {
        return mapManager.getMapData(factionId);
    }

    public TileInfo getTileInfo(int id) {
        return new TileInfo(mapManager.getTileById(id));
    }

    public int getId() {
        return id;
    }

    public int getFactionId() {
        return factionId;
    }

    private int collectTaxes() {
        return 0;
    }
    private int payWages() {
        return 0;
    }
}
