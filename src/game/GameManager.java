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
	    mapManager = new MapManager(0,0);
	    curTurn = 0;
		
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
	}
	
	public MapData getPlayerMap() {
		return mapWrappers.get(0).getMapData();
	}
	
	public TileInfo getTileInfo(int tileId) {
		return mapWrappers.get(0).getTileInfo(tileId);
	}
	
	public FactionData getFactionInfo(int factionId) {
		return factions.get(0).getFactionData(factionId);
	}
	
	private void endTurn() {
	    for(int i = 0; i < factions.size(); i++)
	        factions.get(i).playTurn();

		curTurn++;
	}
	
	private BattleInfo moveUnit(int from, int to) {
	    return mapWrappers.get(0).moveUnits(from, to);
	}
	
	private boolean recruitUnit(int amount, int loc) {
        return factions.get(0).recruit(amount, loc);
	}
}