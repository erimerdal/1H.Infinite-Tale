package sample;

public class GenericUnit {

    private int wage;
    private int cost;
    private boolean isBeingRecruited;
    private boolean isMoved;
    private int ownerId;

    public GenericUnit()
    {
        wage = 0;
        cost = 0;
        isBeingRecruited = false;
        isMoved = false;
        ownerId = -1;
    }

    public void setWage(int wage)
    {
        this.wage = wage;
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

}
