package com.game;

public class Combo
{

    public Combo(Item item1, Item item2, Item result, String message)
    {
        this.item1 = item1;
        this.item2 = item2;
        this.result = result;
        this.message = message;
    }

    public Item getItem1()
    {
        return item1;
    }

    public Item getItem2()
    {
        return item2;
    }

    public Item getResult()
    {
        return result;
    }

    public String getMessage()
    {
        return message;
    }


    private Item    item1;
    private Item    item2;
    private Item    result;
    private String  message;

    private Combo() {}

}
