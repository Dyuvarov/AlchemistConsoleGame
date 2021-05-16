package com.game;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
    private  Inventory   emptyInv;
    private  Inventory   allItemsInv;
    private  Inventory   oneItemInv;

    @Before
    public  void setUpInventories()
    {
        emptyInv = new Inventory();

        allItemsInv = new Inventory();
        allItemsInv.add(new Item("Orichalcum", "Magic metal"));
        allItemsInv.add(new Item("Deadwater", "Water to heal any wounds"));
        allItemsInv.add(new Item("Toadstone", "Magic stone from toad's head"));
        allItemsInv.add(new Item("Lammerwine", "Youth elixir", 2));
        allItemsInv.add(new Item("Philostone", "Wish-fulfilling stone", 2));
        allItemsInv.add(new Item("Gold", "The goal of every alchemist", 3));

        oneItemInv = new Inventory();
        oneItemInv.add(new Item("Gold", "The goal of every alchemist", 3));
    }

    @Test
    public void findTestNullString() throws Exception
    {
        Item result = emptyInv.find(null);
        Assert.assertNull(result);
        result = allItemsInv.find(null);
        Assert.assertNull(result);
        result = oneItemInv.find(null);
        Assert.assertNull(result);
    }

    @Test
    public void findTestEmptyString () throws Exception
    {
        Item result = emptyInv.find("");
        Assert.assertNull(result);
        result = allItemsInv.find("");
        Assert.assertNull(result);
        result = oneItemInv.find("");
        Assert.assertNull(result);
    }

    @Test
    public void findTestCorrectItem() throws Exception
    {
        Item result = allItemsInv.find("Deadwater");
        Assert.assertEquals(result, new Item("Deadwater", "Water to heal any wounds"));
        result = oneItemInv.find("Gold");
        Assert.assertEquals(result, new Item("Gold", "The goal of every alchemist", 3));
    }

    @Test
    public void findTestWrongItem()
    {
        Item result = emptyInv.find("Orichalcum");
        Assert.assertNull(result);
        result = emptyInv.find("lol");
        Assert.assertNull(result);
        result = allItemsInv.find("lol");
        Assert.assertNull(result);
        result = oneItemInv.find("gold");
        Assert.assertNull(result);
        result = oneItemInv.find("lol");
        Assert.assertNull(result);
    }
}
