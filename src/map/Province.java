package map;

// FUNCTION OF LASTMESSAGE?

import java.util.ArrayList;

public class Province {

    private int id;
    private int ownerId;
    private int currentPop;
    private int popLimit;
    private String lastMessage;
    private ArrayList<Integer> neighbours;

    public Province(int id)
    {
        this.id = id;
        ownerId = -1;
        currentPop = 0;
        popLimit = 1000;
        lastMessage = "";
        neighbours = new ArrayList<>();
    }

    public int getPopLimit() {
        return popLimit;
    }

    public int getCurrentPop() {
        return currentPop;
    }

    public void setCurrentPop(int currentPop) {
        this.currentPop = currentPop;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    private boolean canRecruit(int count)
    {
        if(getCurrentPop() + count > getPopLimit())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean recruitUnits(int count)
    {
        if(canRecruit(count))
        {
            int totalPop = count + getCurrentPop();
            setCurrentPop(totalPop);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = neighbours;
    }
}
