package game;

import map.MapManager;
import map.MapData;
import map.BattleInfo;

import java.util.ArrayList;

public class GameManager {
	final public int FACS = 3;
	
	public int curTurn;
	public int curFactionId;
	
	private ArrayList<Integer> factionOrder;
	private ArrayList<Faction> factions;
	private ArrayList<MapWrapper> mapWrappers;
	
	private String message;
	
	private MapManager mapManager;
	
	public GameManager(MapManager mapManager) {
		this(0, 0, mapManager);
	}
	
	public GameManager(int curTurn, int curFactionId, MapManager mapManager) {
		this.curTurn = curTurn;
		this.curFactionId = curFactionId;
		
		for (int i = 0; i < FACS; i++) {
			MapWrapper mw = new MapWrapper(i, i + 1, mapManager);
			
			mapWrappers.add(mw);
		}
		
		for (int i = 0; i < FACS; i++) {
			String name = "Faction " + (i + 1);
			
			MapWrapper mw = mapWrappers.get(i);
			
			if (i == FACS - 1)
				Faction f = new Faction(name, i + 1, mw);
			
			else
				Faction f = new Faction(name, i + 1, mw, true);
			
			factions.add(f);
		}
		
		// Factions are ordered 1 to FACS initially
		for (int i = 0; i < FACS; i++) {
			factionOrder.add(factions.get(i).getId());
		}
		
		message = "";
		
		this.mapManager = mapManager;
	}
	
	public MapData getPlayerMap() {
		return mapWrappers.get(curFactionId).getMapData();
	}
	
	public TileInfo getTileInfo() {
		return mapWrappers.get(curFactionId).getTileInfo();
	}
	
	public FactionData getFactionInfo() {
		return factions.get(curFactionId).getFactionData();
	}
	
	private void endTurn() {
		/*
		 * @TODO
		 * 	Implementation
		 */
		
		curTurn++;
	}
	
	private BattleInfo moveUnit() {
		/*
		 * @TODO
		 * 	Implementation
		 */
		
		// mapManager.moveUnits ???
	}
	
	private boolean recruitUnit() {
		/*
		 * @TODO
		 * 	Implementation
		 */
		
		// factions.get(curFactionId).recruit ???
	}
}