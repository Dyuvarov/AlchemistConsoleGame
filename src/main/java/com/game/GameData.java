package com.game;
import com.sun.javafx.css.Combinator;
import java.util.Arrays;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GameData
{
    GameData()
    {
        Random rnd = new Random(); //to select items randomly for combinations and locations

        //creating items
        allItems = new ArrayList<Item>();
        allItems.add(new Item("Orichalcum", "Magic metal"));
        allItems.add(new Item("Deadwater", "Water to heal any wounds"));
        allItems.add(new Item("Toadstone", "Magic stone from toad's head"));
        allItems.add(new Item("Lammerwine", "Youth elixir", 2));
        allItems.add(new Item("Philostone", "Wish-fulfilling stone", 2));
        allItems.add(new Item("Gold", "The goal of every alchemist", 3));

        lvl1Items = new ArrayList<Item>();
        lvl1Items = allItems.stream()
                .filter(x -> x.getLevel() == 1)
                .collect(Collectors.toList());

        //creating combinations
        combinations = new ArrayList<Combo>();
        Item item1 = lvl1Items.get(rnd.nextInt(3));
        Item item2 = lvl1Items.get(rnd.nextInt(3));
        combinations.add(new Combo(item1, item2, allItems.get(3), "SUCCESS! Lammerwine was created!"));
        //item 1 will be different then used it previous combo.  We don't need 2 same combinations
        item1 = lvl1Items.stream()
                                .filter(x -> (!(combinations.get(0).getItem1().getName().equals(x.getName()))
                                                && !(combinations.get(0).getItem2().getName().equals(x.getName()))))
                                .findAny().get();
        item2 = lvl1Items.get(rnd.nextInt(3));
        combinations.add(new Combo(item1, item2, allItems.get(4), "SUCCESS! PhiloStone was created!"));
        combinations.add(new Combo(allItems.get(3), allItems.get(4), allItems.get(5), "UNBELIEVABLE! YOU ARE THE FIRST " +
                "ALCHEMIST SUCCESSFULLY CREATED GOLD!"));

        //creating locations
        Location darkForest = new Location("Dark forest", null);
        Location bearDen = new Location("Bear's den", null);
        Location dungeon = new Location("Dungeon", null);
        Location lake = new Location("Lake", null);
        locations = new ArrayList<>(Arrays.asList(darkForest, bearDen, dungeon, lake));

        //Dark Forest Directions
        HashMap<Direction, Location> darkForestDirections = new HashMap<Direction, Location>();
        darkForestDirections.put(Direction.EAST, bearDen);
        darkForestDirections.put(Direction.SOUTH, dungeon);
        darkForest.setDirections(darkForestDirections);

        //Bear's Den Directions
        HashMap<Direction, Location> bearDenDirections = new HashMap<>();
        bearDenDirections.put(Direction.WEST, darkForest);
        bearDenDirections.put(Direction.NORTH, lake);
        bearDen.setDirections(bearDenDirections);

        //Dungeon Directions
        HashMap<Direction, Location> dungeonDirections = new HashMap<>();
        dungeonDirections.put(Direction.NORTH, darkForest);
        dungeon.setDirections(dungeonDirections);

        //Lake Directions
        HashMap<Direction, Location> lakeDirections = new HashMap<>();
        lakeDirections.put(Direction.SOUTH, bearDen);
        lake.setDirections(lakeDirections);


        //lets drop some items on locations
        HashSet<Item> addedItems;
        for (Item cItem : lvl1Items)
            dropItemOnMap(cItem, locations);

        //creating player
        player = new Player(darkForest);
    }

    /**
     * drops random count of item (from 0 to 2) on each location
     * @param item - item tp copy and drop
     * @param locations - locations, where items will be dropped
     */
    public void dropItemOnMap(Item item, List<Location> locations)
    {
        Random rnd = new Random();
        for (Location location : locations)
        {
            int count = rnd.nextInt(3);
            try
            {
                for (int i = 0; i < count; i++)
                    location.putItem(item.clone());
            }
            catch (CloneNotSupportedException exeption)
            {
                System.out.println(exeption.getMessage());
            }
        }
    }

    public List<Combo> getCombinations()
    {
        return combinations;
    }

    public Player getPlayer()
    {
        return player;
    }

    public List<Location> getLocations()
    {
        return locations;
    }

    private List<Item>      allItems;
    private List<Item>      lvl1Items;
    private List<Combo>     combinations;
    private List<Location>  locations;
    private Player          player;
}
