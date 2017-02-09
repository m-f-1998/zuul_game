import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashSet<Item> items;
    
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashSet<Item>();
    }
    
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemString();
    }
    
    private String getItemString() 
    {
        String returnString = "Items:";
        for(Iterator<Item> iter = items.iterator(); iter.hasNext(); )
            returnString += " " + iter.next().getDescription();
        
        return returnString;     
    }
    
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(Iterator<String> iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
}

