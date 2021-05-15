package com.game;

public class GameOutput
{
    final static int DELAY = 40;
    public static void SlowOutput(String msg)
    {
        for (char c : msg.toCharArray())
        {
            System.out.print(c);
            try {Thread.sleep(DELAY);}
            catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void  noItemInInventoryMessage(String item)
    {
        System.out.println("You haven't " + item + ". You need to take it in your inventory first.");
    }


    public static void  showWelcomeMessage()
    {
        String message = "For many years, alchemists around the world tried to create gold, " +
                "but no one has succeeded.\nYears passed, people began to laugh at alchemists and many " +
                "alchemists abandoned their ideas.\n" +
                "But not you! You are Flamel - alchemist from a little village.\n" +
                "People in your village are starving, and you can save them. If you learn to create gold " +
                "your people will never be hungry again.\n" +
                "You are already so close, but you are missing something...\n" +
                "That is your last chance. Gold or death. Don't let your people down!\n";
        SlowOutput(message);
    }
    public static void showHowToPlayMessage()
    {
        String message = "\nHow to play:\n" +
                "Items dropped on 4 locations. You need to create gold using them.\n" +
                "Take them in your inventory and try to combine. NOTE: you can combine only 2 items.\n" +
                "If combination is successful you will get new item in your inventory.\n" +
                "Else you will loose items that you combined and won't get anything,\n" +
                "you will have to find them again and try different combination.\n" +
                "Type 'help' to watch commands list\n" +
                "Good luck, alchemist!\n";
        SlowOutput(message);
    }

    public static void showCommands()
    {
        String message = "look - shows current location, items on this location, possible directions to go\n" +
                "go [SOUTH/NORTH/WEST/EAST]- change location\n" +
                "take [item] - take item in inventory\n" +
                "combo [item1] [item2] - try to combine items\n" +
                "quit - close game\n";
        System.out.println(message);
    }

}