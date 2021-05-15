package com.game;

public enum Direction
{
    WEST ("WEST"),
    EAST ("EAST"),
    NORTH ("NORTH"),
    SOUTH ("SOUTH");

    private String title;

    Direction(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return title;
    }
}
