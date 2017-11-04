package map;

// FUNCTION OF LASTMESSAGE?

public class Province {

    private int id;
    private int ownerId;
    private int currentPop;
    private static int popLimit;
    private String lastMessage;

    public Province()
    {
        id = 0;
        ownerId = -1;
        currentPop = 0;
        popLimit = 1000;
        lastMessage = "";
    }

    public static int getPopLimit() {
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
}
