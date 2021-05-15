package com.game;

import javafx.print.PageLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws InterruptedException
    {
        GameData gamedata = new GameData();

//        GameOutput.showWelcomeMessage();
//        GameOutput.showHowToPlayMessage();


        boolean play = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (play)
        {
            int result = handlePlayerInput(gamedata, reader);
            if (result != 0)
                play = false;
        }
    }

    public static int   handlePlayerInput(GameData gameData, BufferedReader reader)
    {
        String input = "";
        try
        {
            input = reader.readLine();
        }
        catch (IOException ex)
        {
            System.out.println("Input ERROR!");
            return -1;
        }
        if (input.isEmpty())
            return 0;
        String[] inputArr = input.split("\\s+");
        if (inputArr.length < 1)
            return 0;

        Player player = gameData.getPlayer();
        if (inputArr[0].equals(Command.QUIT.toString()))
        {
            System.out.println("Bye!");
            return -1;
        }
        else if (inputArr[0].equals(Command.LOOK_AROUND.toString()))
            player.lookAround();
        else if (inputArr[0].equals(Command.GO.toString()))
        {
            if (inputArr.length == 2)
                player.go(inputArr[1]);
            else
                System.out.println("Wrong arguments");
        }
        else if (inputArr[0].equals(Command.TAKE.toString()))
        {
            if (inputArr.length == 2)
                player.take(inputArr[1]);
            else
                System.out.println("Wrong arguments");
        }
        else if (inputArr[0].equals(Command.COMBO.toString()))
        {
            if (inputArr.length == 3)
                player.combine(inputArr[1], inputArr[2], gameData);
            else
                System.out.println("Wrong arguments");
        }
        else if (inputArr[0].equals(Command.INVENTORY.toString()))
            player.inventory();
        else if (inputArr[0].equals(Command.HELP.toString()))
            GameOutput.showCommands();
        else
            System.out.println("Wrong command");

        return 0;
    }
}
