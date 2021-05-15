package com.game;
import java.util.Map;

public class Location
{
    Location(String name, String description, Map<Direction, Location> directions)
    {
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.inventory = new Inventory();
    }

    Location(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.inventory = new Inventory();
    }

    /**
     * Adds item on the location
     * @param item - item to put
     */
    public void putItem(Item item)
    {
        inventory.add(item);
    }

    public void showDirections()
    {
        for (Direction dir : directions.keySet())
            System.out.println(dir.toString() + ": " + directions.get(dir).name);
    }

    @Override
    public String toString()
    {
        return ("Location: " + this.name + '\n' +
                ((this.description == null) ? "" : (this.description + "\n")));
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    public Map<Direction, Location> getDirections()
    {
        return directions;
    }

    public void setDirections(Map<Direction, Location> directions)
    {
        this.directions = directions;
    }

    private String name;
    private String description;
    private Inventory inventory;
    private Map<Direction, Location> directions;
}
