package map;

import java.util.ArrayList;
import java.util.Random;

public class Tile {
    private int id;
    private Province owner;
    private Terrain terrain;
    private ArrayList<GenericUnit> troops;

    public Tile(int id, Province owner)
    {
        this.id = id;
        WeatherType weatherType = new WeatherType(true,false,false);

        Random rand = new Random();
        int r = rand.nextInt(20);
        TerrainType terrainType;
        if(r < 6)
            terrainType = new TerrainType( true,false,false);
        else if(r < 19)
            terrainType = new TerrainType( false,true,false);
        else
            terrainType = new TerrainType( false,false,true);

        terrain = new Terrain(weatherType, terrainType);
        troops = new ArrayList<>();
        this.owner = owner;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public void setTerrain(Terrain terrain)
    {
        this.terrain = terrain;
    }
    public void setTroops(ArrayList<GenericUnit> troops)
    {
        this.troops = troops;
    }
    public int getId()
    {
        return id;
    }
    public Terrain getTerrain()
    {
        return terrain;
    }
    public ArrayList<GenericUnit> getTroops()
    {
        return troops;
    }
    public Province getOwner() {
        return owner;
    }

    public boolean addUnits(int number)
    {
        return addUnits(number, owner.getOwnerId());
    }

    public boolean addUnits(int number, int ownerId)
    {
        if(number < 1)
            return false;
        GenericUnit unit;
        for(;number > 100;number -= 100) {
            unit = new GenericUnit(100);
            unit.setOwnerId(ownerId);
            troops.add(unit);
        }
        unit = new GenericUnit(number);
        unit.setOwnerId(ownerId);
        troops.add(unit);
        return true;
    }

    public boolean removeUnits(int number)
    {
        if(number > getTotalUnits())
            return false;

        if(number == getTotalUnits()) {
            troops.clear();
            return true;
        }

        clearEmptyTroops();
        owner.setCurrentPop(owner.getCurrentPop() + number);
        for(int i = 0; i < troops.size() && number > 0; i++) {
            if(number < troops.get(i).getCount()) {
                troops.get(i).setCount(troops.get(i).getCount() - number);
                return true;
            }

            number -= troops.get(i).getCount();
            troops.remove(i);
            i--;
        }

        return true;
    }

    public int getTotalUnits() {
        int sum = 0;
        clearEmptyTroops();
        for(int i = 0; i < troops.size(); i++)
            sum += troops.get(i).getCount();

        return sum;
    }

    public boolean getUnitsMoved() {
        for(int i = 0; i < troops.size(); i++)
            if(troops.get(i).getIsMoved())
                return true;

        return false;
    }

    public void setUnitsMoved(boolean isMoved) {
        for(int i = 0; i < troops.size(); i++)
            troops.get(i).setIsMoved(isMoved);
    }

    public int getTotalWage() {
        int sum = 0;
        clearEmptyTroops();
        for(int i = 0; i < troops.size(); i++)
            sum += troops.get(i).getWage();

        return sum;
    }

    private void clearEmptyTroops() {
        for(int i = 0; i < troops.size(); i++) {
            if(troops.get(i).getCount() < 1) {
                troops.remove(i);
                i--;
                continue;
            }
        }
    }
}
