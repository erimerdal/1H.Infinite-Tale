package map;

import java.util.ArrayList;

public class MapData {
    public ArrayList<Province> visible;
    public ArrayList<Province> hidden;
    public ArrayList<Tile> open;
    public ArrayList<Tile> closed;
    public ArrayList<Province> ownedProvince;
    public ArrayList<Tile> ownedTile;

    public MapData()
    {
        visible = new ArrayList<Province>();
        hidden = new ArrayList<Province>();
        open = new ArrayList<Tile>();
        closed = new ArrayList<Tile>();
        ownedProvince = new ArrayList<Province>();
        ownedTile = new ArrayList<Tile>();
    }
}
