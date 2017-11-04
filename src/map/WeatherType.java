package map;

public class WeatherType {

    private boolean cold;
    private boolean hot;
    private boolean warm;

    // I declared flat true to make our code error-prone
    // If somehow somebody manages to use the class without declaring
    // Hills,mountain or flat, then warm will be the usual case.
    public WeatherType(boolean cold, boolean hot, boolean warm)
    {
        this.cold = cold;
        this.hot = hot;
        this.warm = warm;
    }
    public int getAttackBonus()
    {
        if(cold)
        {
            return -15;
        }
        else if(hot)
        {
            return 15;
        }
        else
        {
            return 0;
        }
    }
    public int getDefenseBonus()
    {
        if(cold)
        {
            return 15;
        }
        else if(hot)
        {
            return -15;
        }
        else
        {
            return 0;
        }
    }
}
