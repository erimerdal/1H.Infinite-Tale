package game;

import map.Tile;
import map.Province;
import map.GenericUnit;

import java.util.ArrayList;

public class TileInfo {
	public String owner;
	public Tile tile;
	public boolean isVisible;
	
	public TileInfo(Tile tile) {
		this.tile = tile;
		isVisible = false;
	}
}