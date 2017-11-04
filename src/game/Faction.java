package game;

import map.GenericUnit;
import map.MapData;
import map.Province;
import map.Tile;

import java.util.ArrayList;

public class Faction {
    private int id;
    private String name;
    private boolean isPlayer;
    private MapColor color;
    private int treasury;
    private MapWrapper mapWrapper;
    private boolean isDefeated;
    private ArrayList<Province> ownProv;
    private ArrayList<Province> visibleProv;
    private ArrayList<Province> hiddenProv;
    private ArrayList<Tile> ownTile;
    private ArrayList<Tile> visibleTile;
    private ArrayList<Tile> hiddenTile;

    public Faction(String name, int id, MapWrapper mapWrapper) {
        this(name, id, mapWrapper, false);
    }

    public Faction(String name, int id, MapWrapper mapWrapper, boolean isPlayer) {
        this.name = name;
        this.id = id;
        this.mapWrapper = mapWrapper;
        this.isPlayer = isPlayer;

        color = MapColor.GRAY;
        treasury = 0;
        isDefeated = false;
        ownProv = new ArrayList<>();
        visibleProv = new ArrayList<>();
        hiddenProv = new ArrayList<>();
        ownTile = new ArrayList<>();
        visibleTile = new ArrayList<>();
        hiddenTile = new ArrayList<>();
    }

    public FactionData getFactionData(int id) {
        updateMapData();

        FactionData fd = new FactionData();
        fd.id = this.id;
        fd.name = this.name;
        fd.mapColor = this.color;
        fd.totalProvinces = ownProv.size();

        if(id == this.id) {
            fd.treasury = this.treasury;
            fd.income = getIncome();
            fd.expense = getExpense();
            fd.totalUnits = getTotalUnits();
        }

        return fd;
    }

    public void playTurn() {
        updateMapData();

        if(isDefeated)
            return;

        treasury += getIncome() - getExpense();

        if(isPlayer)
            return;

        /*
        @TODO
            Implement AI
         */
    }

    public boolean recruit(int amount, int loc) {
        /*
        @TODO
            Implementation
         */
        return true;
    }

    public TileInfo getTileInfo(int tileId) {
        updateMapData();
        TileInfo ti = mapWrapper.getTileInfo(tileId);
        if(ti == null)
            return ti;

        if(ti.tile.getOwner().getOwnerId() == id)
            ti.isVisible = true;
        else {
            for(int i = 0; i < visibleTile.size(); i++) {
                if(visibleTile.get(i).getId() == tileId) {
                    ti.isVisible = true;
                    return ti;
                }
            }
        }

        return ti;
    }

    private boolean recruitMultiple(int amount, Province loc) {
        /*
        @TODO
            Implementation
         */
        return true;
    }

    private boolean supplyUnits(int amount, Tile loc) {
        /*
        @TODO
            Implementation
         */
        return true;
    }

    private void updateMapData() {
        MapData md = mapWrapper.getMapData();
        ownProv = md.ownedProvince;
        visibleProv = md.visible;
        hiddenProv = md.hidden;
        ownTile = md.ownedTile;
        visibleTile = md.open;
        hiddenTile = md.closed;

        if(ownProv.size() < 1)
            isDefeated = true;
    }

    private int getIncome() {
        int inc = 0;
        for(int i = 0; i < ownProv.size(); i++) {
            inc += ownProv.get(i).getCurrentPop();
        }

        return inc;
    }

    private int getExpense() {
        int exp = 0;
        for(int i = 0; i < ownTile.size(); i++) {
            ArrayList<GenericUnit> troops = ownTile.get(i).getTroops();
            if(troops.size() > 0) {
                exp += troops.size() * troops.get(0).getWage();
            }
        }

        return exp;
    }

    private int getTotalUnits() {
        int sum = 0;
        for(int i = 0; i < ownTile.size(); i++) {
            sum += ownTile.get(i).getTroops().size();
        }

        return sum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsPlayer() {
        return isPlayer;
    }

    public MapColor getColor() {
        return color;
    }

    public void setColor(MapColor color) {
        this.color = color;
    }

    public int getTreasury() {
        return treasury;
    }
}
