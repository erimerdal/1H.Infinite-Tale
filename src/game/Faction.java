package game;

import map.GenericUnit;
import map.MapData;
import map.Province;
import map.Tile;

import java.util.*;

public class Faction {
    // Same for the AI and the player
    private static final int MAX_INCOME = 50000;
    private static final int MAX_TREASURY = 500000;

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
    private HashMap<Integer, Integer> requiredDefence;
    private HashMap<Integer, Integer> recruitOrders;

    public Faction(String name, int id, MapWrapper mapWrapper) {
        this(name, id, mapWrapper, false);
    }

    public Faction(String name, int id, MapWrapper mapWrapper, boolean isPlayer) {
        this.name = name;
        this.id = id;
        this.mapWrapper = mapWrapper;
        this.isPlayer = isPlayer;

        color = MapColor.GRAY;
        treasury = 5000;
        isDefeated = false;
        ownProv = new ArrayList<>();
        visibleProv = new ArrayList<>();
        hiddenProv = new ArrayList<>();
        ownTile = new ArrayList<>();
        visibleTile = new ArrayList<>();
        hiddenTile = new ArrayList<>();
        requiredDefence = new HashMap();
        recruitOrders = new HashMap();
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

        if (treasury > MAX_TREASURY)
            treasury = MAX_TREASURY;

        if(isPlayer)
            return;

        recruitOrders.clear();
        // First defence
        boolean isAllDefended = true;
        for(int i = 0; i < ownTile.size(); i++) {
            Tile tile = ownTile.get(i);
            if(requiredDefence.get(tile.getId()) > tile.getTotalUnits()) {
                if(!supplyUnits(requiredDefence.get(tile.getId()) - tile.getTotalUnits(), tile))
                    isAllDefended = false;
            }
        }

        updateMapData();
        if(!isAllDefended)
            isAllDefended = recruitOrdered();

        // Don't do offence if defence measures were not taken
        if(!isAllDefended)
            return;

        // skip turn if economy isn't good
        if((getIncome() - getExpense()) < 100 || treasury < (ownTile.size() * 100))
            return;

        // Offence
        for(int i = 0; i < ownTile.size(); i++) {
            Tile tile = ownTile.get(i);

            if(tile.getTotalUnits() > 0 && !tile.getUnitsMoved()) {
                // check if tile requires defence, if not use soldiers for offence
                if(requiredDefence.get(tile.getId()) == 0) {
                    ArrayList<Tile> nbs = mapWrapper.getNeighbourTiles(tile.getId());
                    for(int j = 0; j < nbs.size(); j++) {
                        Tile nb = nbs.get(j);

                        if(nb.getOwner().getOwnerId() != id && nb.getTotalUnits() < tile.getTotalUnits()) {
                            mapWrapper.moveUnits(tile.getId(), nb.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean recruit(int amount, int loc) {
        if(amount < 0)
            return false;

        if(treasury < (new GenericUnit(0)).getCost() * amount)
            return false;

        if(!mapWrapper.recruitUnits(amount, loc))
            return false;

        treasury -= (new GenericUnit(0)).getCost() * amount;
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

    private boolean recruitOrdered() {
        int profit = getIncome() - getExpense();

        boolean recruitedAll = true;
        for(int i = 0; i < ownTile.size(); i++) {
            Tile tile = ownTile.get(i);
            if(recruitOrders.containsKey(tile.getId())) {
                if(recruitOrders.get(tile.getId()) <= 0) {
                    recruitOrders.remove(tile.getId());
                    continue;
                }

                if(profit < 0)
                    return false;

                int amount = recruitOrders.get(tile.getId());

                if(treasury < amount * 10)
                    return false;

                if(tile.getOwner().getCurrentPop() > (amount + 100)) {
                    if(!recruit(amount, tile.getId()))
                        return false;

                    recruitOrders.remove(tile.getId());
                }
                else
                    recruitedAll = false;
            }
        }

        return recruitedAll;
    }

    private boolean supplyUnits(int amount, Tile loc) {
        ArrayList<Integer> visited = new ArrayList<>();
        LinkedList<Tile[]> visitQue = new LinkedList<>();
        ArrayList<Tile> nbs = mapWrapper.getNeighbourTiles(loc.getId());
        visited.add(loc.getId());
        for(int i = 0; i < nbs.size(); i++) {
            Tile tile = nbs.get(i);
            visited.add(tile.getId());
            if(tile.getOwner().getOwnerId() == id)
                visitQue.addLast(new Tile[]{tile, loc});
        }

        while (amount > 0 && visitQue.size() > 0) {
            Tile[] toVisit = visitQue.pollFirst();
            Tile tile = toVisit[0];
            Tile parent = toVisit[1];
            if(requiredDefence.get(tile.getId()) == 0) {
                if(tile.getTotalUnits() > 0 && !tile.getUnitsMoved()) {
                    amount -= tile.getTotalUnits();
                    mapWrapper.moveUnits(tile.getId(), parent.getId());
                }
            }

            nbs = mapWrapper.getNeighbourTiles(tile.getId());
            for(int i = 0; i < nbs.size(); i++) {
                Tile child = nbs.get(i);
                visited.add(child.getId());
                if(child.getOwner().getOwnerId() == id && !visited.contains(child.getId()))
                    visitQue.addLast(new Tile[]{child, tile});
            }
        }

        if(amount <= 0)
            return true;

        if((getIncome() - getExpense()) < 0)
            return false;

        if(treasury < amount * 10)
            return false;

        if(amount <= (loc.getOwner().getCurrentPop() - 100)) {
            return recruit(amount, loc.getId());
        }

        int recruitHere = loc.getOwner().getCurrentPop() - 100;
        if(!recruit(recruitHere, loc.getId()))
            return false;

        nbs = mapWrapper.getNeighbourTiles(loc.getId());
        for(int i = 0; i < nbs.size(); i++) {
            Tile tile = nbs.get(i);
            if(tile.getOwner().getOwnerId() != id || tile.getOwner().getId() == loc.getOwner().getId()) {
                nbs.remove(i);
                i--;
            }
        }

        amount -= recruitHere;
        for(int i = 0; i < nbs.size(); i++) {
            int oldValue = recruitOrders.getOrDefault(nbs.get(i).getId(), 0);

            recruitOrders.put(nbs.get(i).getId(), oldValue + (amount / nbs.size()));
        }

        return false;
    }

    private void updateMapData() {
        MapData md = mapWrapper.getMapData();
        ownProv = md.ownedProvince;
        visibleProv = md.visible;
        hiddenProv = md.hidden;
        ownTile = md.ownedTile;
        visibleTile = md.open;
        hiddenTile = md.closed;

        // set defence priorities
        requiredDefence.clear();
        for(int i = 0; i < ownTile.size(); i++) {
            ArrayList<Tile> nbs = mapWrapper.getNeighbourTiles(ownTile.get(i).getId());

            int required = 0;
            for(int j = 0; j < nbs.size(); j++) {
                Tile tile = nbs.get(j);
                if(tile.getOwner().getOwnerId() != id) {
                    if(tile.getTroops().size() > 0 && tile.getTroops().get(0).getOwnerId() != id)
                        required += tile.getTotalUnits();
                }
            }
            if(required > 100)
                required = 100;
            requiredDefence.put(ownTile.get(i).getId(), required);
        }

        if(ownProv.size() < 1)
            isDefeated = true;
    }

    private int getIncome() {
        int inc = 0;

        for(int i = 0; i < ownProv.size(); i++) {
            inc += ownProv.get(i).getCurrentPop();
        }

        inc += 1000;

        if (inc > MAX_INCOME)
            inc = MAX_INCOME;

        return inc;
    }

    private int getExpense() {
        int exp = 0;

        for(int i = 0; i < ownTile.size(); i++) {
            exp += ownTile.get(i).getTotalWage();
        }

        return exp;
    }

    private int getTotalUnits() {
        int sum = 0;

        for(int i = 0; i < ownTile.size(); i++) {
            sum += ownTile.get(i).getTotalUnits();
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
