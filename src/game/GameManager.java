package game;

import map.MapManager;
import map.MapData;
import map.BattleInfo;

import java.util.ArrayList;

public class GameManager {
	final public int FACS = 3;
	
	public int curTurn;

	private ArrayList<Faction> factions;
	private ArrayList<MapWrapper> mapWrappers;
	
	private MapManager mapManager;
	
	public GameManager() {
	    mapManager = new MapManager();
	    curTurn = 0;
	    mapWrappers = new ArrayList<>();
	    factions = new ArrayList<>();
		
		for (int i = 0; i < FACS; i++) {
			MapWrapper mw = new MapWrapper(i, i, mapManager);

			mapWrappers.add(mw);
		}
		factions.add(new Faction("Player", 0, mapWrappers.get(0), true));
		for (int i = 1; i < FACS; i++) {
			String name = "Faction " + i;
			
			MapWrapper mw = mapWrappers.get(i);
			factions.add(new Faction(name, i, mw));
		}

		factions.get(0).setColor(MapColor.RED);
        factions.get(1).setColor(MapColor.BLUE);
        factions.get(2).setColor(MapColor.PURPLE);
	}
	
	public MapData getPlayerMap() {
		return mapWrappers.get(0).getMapData();
	}
	
	public TileInfo getTileInfo(int tileId) {
		TileInfo tileInfo = factions.get(0).getTileInfo(tileId);
		tileInfo.owner = factions.get(tileInfo.tile.getOwner().getOwnerId()).getName();
		return tileInfo;
	}
	
	public FactionData getFactionInfo(int factionId) {
		return factions.get(factionId).getFactionData(0);
	}
	
	public void endTurn() {
	    for(int i = 0; i < factions.size(); i++)
	        factions.get(i).playTurn();

	    mapManager.endTurn();
		curTurn++;
	}
	
	public BattleInfo moveUnit(int from, int to) {
	    return mapWrappers.get(0).moveUnits(from, to);
	}
	
	public boolean recruitUnit(int amount, int loc) {
        return factions.get(0).recruit(amount, loc);
	}
}