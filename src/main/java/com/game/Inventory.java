package com.game;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory
{
    private List<Item> items;

    public Inventory()
    {
        items = new ArrayList<Item>();
    }
    public void add(Item item) { items.add(item); }
    public void remove(Item item) { items.remove(item); }
    public void show()
    {
        int i = 0;
        for (Item item : items)
        {
            System.out.print(item.getName() + "[lvl: " + item.getLevel() + "]");
            if (i != (items.size() - 1))
                System.out.print(", ");
            ++i;
        }
        System.out.println(" ");
    }
    public Item find(String name)
    {
        if (items == null)
            return null;
        return items.stream()
                    .filter(x->x.getName().equals(name))
                    .findAny()
                    .orElse(null);
    }

    public List<Item> getItems()
    {
        return items;
    }
}
