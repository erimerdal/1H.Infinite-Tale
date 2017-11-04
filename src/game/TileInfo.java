package game;

import map.Tile;
import map.Province;
import map.GenericUnit;

import java.util.ArrayList;

public class TileInfo {
	public Tile tile;
	public Province ownerProvince;
	public GenericUnit[] units;
	
	public TileInfo(Tile tile) {
		this.tile = tile;
		
		ownerProvince = tile.getOwner();
		
		units = tile.getTroops();
	}
}