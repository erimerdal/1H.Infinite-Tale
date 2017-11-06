package map;

public class GenericUnit {
    public final int MAX_COUNT = 100;
    public final int BASE_WAGE = 1;
    private int wage;
    private int cost;
    private boolean isBeingRecruited;
    private boolean isMoved;
    private int ownerId;
    private int count;

    public GenericUnit(int count)
    {
        wage = 0;
        cost = 10;
        isBeingRecruited = false;
        isMoved = true;
        ownerId = -1;
        setCount(count);
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }
    public void setOwnerId(int ownerId)
    {
        this.ownerId = ownerId;
    }
    public void setIsBeingRecruited(boolean isBeingRecruited)
    {
        this.isBeingRecruited = isBeingRecruited;
    }
    public void setIsMoved(boolean isMoved)
    {
        this.isMoved = isMoved;
    }
    public int getWage()
    {
        return wage;
    }
    public int getCost()
    {
        return cost;
    }
    public int getOwnerId()
    {
        return ownerId;
    }
    public boolean getIsMoved()
    {
        return isMoved;
    }
    public boolean getIsBeingRecruited()
    {
        return isBeingRecruited;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        if(count < 0 || count > MAX_COUNT)
            return;

        this.count = count;
        wage = count * BASE_WAGE;
    }
}
