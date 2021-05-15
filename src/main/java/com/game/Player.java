package com.game;
import java.util.List;

public class Player
{
    public Player(Location location)
    {
        this.location = location;
        inventory = new Inventory();
    }

    /**
     * Outputs name and description of current location,
     * names of items on current location,
     * directions to go
     */
    public void lookAround()
    {
        System.out.println(location.toString());
        System.out.print("Items here: ");
        location.getInventory().show();
        System.out.println("Directions to go (Direction: Location)");
        location.showDirections();
    }

    /**
     * Changes player location if there is location in location.directions for this direction
     * Else shows error message
     * @param strDir - direction to go
     */
    public void go(String strDir)
    {
        Direction playerdir;
        try
        {
            playerdir = Direction.valueOf(strDir);
        }
        catch(IllegalArgumentException ex)
        {
            playerdir = null;
        }

        if (playerdir == null || !(location.getDirections().containsKey(playerdir)))
        {
            System.out.println("Bad direction");
            return ;
        }
        this.location = location.getDirections().get(playerdir);
    }

    /**
     * Puts the item in players inventory and removes it from location
     * @param strItem
     */
    public void take(String strItem)
    {
        Item item = location.getInventory().find(strItem);
        if (item != null)
        {
            inventory.add(item);
            location.getInventory().remove(item);
            System.out.println(item.getName() + " now in your inventory!");
        }
        else
            System.out.println("Bad item!");
    }

    /**
     * Trying to combine items if item1 and item2 are in inventory.
     * If there is combination with these items
     * new item will be added in the inventory, else nothing will be added.
     * item1 and item2 will be deleted from the inventory
     * @param item1 - item name to combine
     * @param item2 - item name to combine
     */
    public void combine(String item1, String item2, GameData gameData)
    {
        Item it1 = inventory.find(item1);
        if (it1 == null)
        {
            GameOutput.noItemInInventoryMessage(item1);
            return ;
        }
        inventory.remove(it1);
        Item it2 = inventory.find(item2);
        if (it2 == null)
        {
            GameOutput.noItemInInventoryMessage(item2);
            inventory.add(it1);                         //add item1 back in inventory if second item wrong
            return ;
        }
        inventory.remove(it2);
        if (it1.getLevel() != it2.getLevel())
        {
            System.out.println("You cant combine items with different levels!");
            return ;
        }
        GameOutput.SlowOutput("Trying to combine...........................\n");
        List<Combo> combinations = gameData.getCombinations();
        Combo combo = combinations.stream()
                                            .filter(x -> ((x.getItem1().equals(it1) && x.getItem2().equals(it2)) ||
                                                            x.getItem1().equals(it2) && x.getItem2().equals(it1)))
                                            .findFirst()
                                            .orElse(null);
        if (combo == null)
        {
            System.out.println("BOOOM! Items exploded in your hands. Bad choice, try something else.");
        }
        else
        {
            try
            {
                this.inventory.add(combo.getResult().clone());
            }
            catch(CloneNotSupportedException ex)
            {
                System.out.println(ex.getMessage());
            }
            System.out.println(combo.getMessage());
            if (combo.getResult().getName() == "Gold")
            {
                System.out.println("Thats all. You win!");
                System.exit(0);
            }
        }
        if (it1.getLevel() == 1)
            gameData.dropItemOnMap(it1, gameData.getLocations());
        if (it2.getLevel() == 1)
            gameData.dropItemOnMap(it2, gameData.getLocations());
    }

    public void inventory()
    {
        System.out.println("Your inventory: ");
        inventory.show();
    }

    private Location    location;
    private Inventory   inventory;
}
