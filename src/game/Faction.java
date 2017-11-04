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
        treasury += getIncome() - getExpense();

        if(isPlayer)
            return;

        /*
        @TODO
            Implement AI
         */
    }

    public boolean recruit(int amount, Tile loc) {
        /*
        @TODO
            Implementation
         */
        return true;
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
        /*
        @TODO
            Implement data update
         */
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
            GenericUnit[] troops = ownTile.get(i).getTroops();
            if(troops.length > 0) {
                exp += troops.length * troops[0].getWage();
            }
        }

        return exp;
    }

    private int getTotalUnits() {
        int sum = 0;
        for(int i = 0; i < ownTile.size(); i++) {
            sum += ownTile.get(i).getTroops().length;
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
