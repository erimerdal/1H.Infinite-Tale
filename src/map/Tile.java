package map;

import java.util.ArrayList;

public class Tile {
    private int id;
    private Province owner;
    private Terrain terrain;
    private ArrayList<GenericUnit> troops;

    public Tile(int id, Province owner)
    {
        this.id = id;
        WeatherType weatherType = new WeatherType(false,false,false);
        TerrainType terrainType = new TerrainType( false,false,false);
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
        // First checks if Province can handle this number of units.
        if(owner.recruitUnits(number)) {
            for(int i = 0; i < number; i++)
                troops.add(new GenericUnit());
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeUnits(int number)
    {
        for(int i = 0; i < number; i++)
            if(troops.size() > 0)
                troops.remove(troops.size() - 1);

        int ownerPop = owner.getCurrentPop();
        owner.setCurrentPop(ownerPop + number);
        return true;
    }

}
