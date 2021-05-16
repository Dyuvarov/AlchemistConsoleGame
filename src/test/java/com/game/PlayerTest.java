package com.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlayerTest
{
    Location darkForest;
    Location lake;
    Location bearDen;
    Location dungeon;
    Location oldLocation;

    @Before
    public void setUpLocations()
    {
        darkForest = new Location("Dark forest", null);
        bearDen = new Location("Bear's den", null);
        dungeon = new Location("Dungeon", null);
        lake = new Location("Lake", null);

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
    }

    @Test
    public void goTestNullDirection() throws Exception
    {
        Player player = new Player(darkForest);
        oldLocation = player.getLocation();
        player.go(null);
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestEmptyDirection() throws Exception
    {
        Player player = new Player(darkForest);
        oldLocation = player.getLocation();
        player.go("");
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestWrongInput() throws Exception
    {
        Player player = new Player(darkForest);
        oldLocation = player.getLocation();
        player.go("lol");
        Assert.assertEquals(oldLocation, player.getLocation());

        player.go("north");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("east");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("west");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("south");
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestDarkForestWrongDirections() throws Exception
    {
        Player player = new Player(darkForest);
        oldLocation = player.getLocation();
        player.go("NORTH");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("WEST");
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestDungeonWrongDirections() throws Exception
    {
        Player player = new Player(dungeon);
        oldLocation = player.getLocation();
        player.go("EAST");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("WEST");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("SOUTH");
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestBearsDenWrongDirections() throws Exception
    {
        Player player = new Player(dungeon);
        oldLocation = player.getLocation();
        player.go("EAST");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("SOUTH");
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestLakeWrongDirections() throws Exception
    {
        Player player = new Player(lake);
        oldLocation = player.getLocation();
        player.go("EAST");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("WEST");
        Assert.assertEquals(oldLocation, player.getLocation());
        player.go("NORTH");
        Assert.assertEquals(oldLocation, player.getLocation());
    }

    @Test
    public void goTestDarkForestCorrectDirections() throws Exception
    {
        Player player = new Player(darkForest);
        player.go("SOUTH");
        Assert.assertEquals(dungeon, player.getLocation());

        player = new Player(darkForest);
        player.go("EAST");
        Assert.assertEquals(bearDen, player.getLocation());
    }

    @Test
    public void goTestDungeonCorrectDirections() throws Exception
    {
        Player player = new Player(dungeon);
        player.go("NORTH");
        Assert.assertEquals(darkForest, player.getLocation());
    }

    @Test
    public void goTestBearDenCorrectDirections() throws Exception
    {
        Player player = new Player(bearDen);
        player.go("WEST");
        Assert.assertEquals(darkForest, player.getLocation());

        player = new Player(bearDen);
        player.go("NORTH");
        Assert.assertEquals(lake, player.getLocation());
    }

    @Test
    public void goTestLakeCorrectDirections() throws Exception
    {
        Player player = new Player(lake);
        player.go("SOUTH");
        Assert.assertEquals(bearDen, player.getLocation());
    }
}
