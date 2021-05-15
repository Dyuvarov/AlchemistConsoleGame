package com.game;

public class Item implements Cloneable
{
    public Item(String name, String description, int level)
    {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public Item(String name, String description)
    {
        this.name = name;
        this.description = null;
        this.level = 1;
    }

    public Item clone() throws CloneNotSupportedException   { return ((Item)super.clone()); }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Item item = (Item)obj;
        return ((item.name.equals(this.name)) && (item.level == this.level));
    }

    @Override
    public int hashCode()
    {
        final int prime = 13;
        return (prime * (name.hashCode() + this.level));
    }

    public String   getName() {  return name; }
    public String   getDescription()  { return description; }
    public int      getLevel() {  return level; }


    private String  name;
    private String  description;
    private int     level;

    private Item()  {}
}
