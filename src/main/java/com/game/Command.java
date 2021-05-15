package com.game;

/**
 * commands that user can use to make actions in game
 */
public enum Command
{
    LOOK_AROUND ("look"),
    GO ("go"),
    TAKE ("take"),
    COMBO ("combo"),
    INVENTORY ("inventory"),
    QUIT ("quit"),
    HELP ("help");


    private String title;

    Command(String title)   { this.title = title; }

    @Override
    public String toString()
    {
        return (title);
    }
}
