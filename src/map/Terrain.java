package map;

public class Terrain {

    private WeatherType weather;
    private TerrainType terrain;

    public Terrain(WeatherType weather, TerrainType terrain)
    {

        this.weather = weather;
        this.terrain = terrain;
    }

    public int getTotalAttBonus()
    {
        return weather.getAttackBonus() + terrain.getAttackBonus();
    }
    public int getTotalDefBonus()
    {
        return weather.getDefenseBonus() + terrain.getDefenseBonus();
    }
}
