package sample;

public class MapData {
    public Province[] visible;
    public Province[] hidden;
    public Tile[] open;
    public Tile[] closed;
    public Province[] ownedProvince;
    public Tile[] ownedTile;

    public MapData()
    {
        visible = new Province[1];
        hidden = new Province[1];
        open = new Tile[1];
        closed = new Tile[1];
        ownedProvince = new Province[1];
        ownedTile = new Tile[1];
    }
}
