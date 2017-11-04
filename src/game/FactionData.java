package game;

public class FactionData {
    public int id;
    public String name;
    public MapColor mapColor;
    public int treasury;
    public int income;
    public int expense;
    public int population;
    public int totalUnits;
    public int totalProvinces;

    public FactionData() {
        id = -1;
        name = "";
        mapColor = MapColor.GRAY;
        treasury = 0;
        income = 0;
        expense = 0;
        population = 0;
        totalUnits = 0;
        totalProvinces = 0;
    }
}