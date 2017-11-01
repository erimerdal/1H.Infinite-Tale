package sample;

public class Tile {
    private int id;
    private Province owner;
    private Terrain terrain;
    private GenericUnit[] troops;

    public Tile()
    {
        id = -1;
        WeatherType weatherType = new WeatherType(false,false,false);
        TerrainType terrainType = new TerrainType( false,false,false);
        terrain = new Terrain(weatherType, terrainType);
        troops = new GenericUnit[0];
        owner = new Province();
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public void setTerrain(Terrain terrain)
    {
        this.terrain = terrain;
    }
    public void setTroops(GenericUnit[] troops)
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
    public GenericUnit[] getTroops()
    {
        return troops;
    }
    public boolean addUnits(int number)
    {
        // First checks if Province can handle this number of units.
        if(owner.recruitUnits(number)) {
            // First checks current troop size.
            int currentTroopSize = getTroops().length;
            System.out.println("Current troop size: " + currentTroopSize);
            GenericUnit[] newTroops = new GenericUnit[number+currentTroopSize];
            // Now we copy all units in the initial troop array to our new troop array.
            // We do this because maybe in next iteration we might have different units
            // Which would ease us to modify this code.
            if(currentTroopSize != 0)
            {
                for(int count = 0; count < currentTroopSize; count++)
                {
                    newTroops[count] = troops[count];
                }
            }
            for(int fill = currentTroopSize; fill < number+currentTroopSize; fill++)
            {
                newTroops[fill] = new GenericUnit();
            }
            setTroops(newTroops);
            System.out.println("New troop size: " + getTroops().length);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeUnits(int number)
    {
        if(owner.getCurrentPop() > number)
        {
            if(getTroops().length > number)
            {
                //System.out.println("Initial troop size: " + getTroops().length);
                GenericUnit[] newTroops = new GenericUnit[getTroops().length - number];
                for(int count = 0; count < getTroops().length - number; count++)
                {
                    newTroops[count] = troops[count];
                }
                troops = newTroops;
            }
            else
            {
                GenericUnit[] newTroops = new GenericUnit[0];
                troops = newTroops;
            }
            int ownerPop = owner.getCurrentPop();
            owner.setCurrentPop(ownerPop - number);
            //System.out.println("Final troops size: " + troops.length);
            return true;
        }
        else
        {
            GenericUnit newTroops[] = new GenericUnit[0];
            troops = newTroops;
            owner.setCurrentPop(0);
            return false;
        }

    }

}
