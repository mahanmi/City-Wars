package com.controller;

import com.*;
import com.model.User;

import java.util.Scanner;

public class MenuController {
    public void run(Scanner scanner) {
        User user = Main.crud.getUserById(Main.loggedInUserId);
        if (user.isFirstTime()) {
            System.out.println("Welcome to the game! You have been given 1000 coins to start with.");
            user.setFirstTime(false);
        }
        System.out.println("Welcome to the main menu! What would you like to do?");
        System.out.println("1. Play a game");
        System.out.println("2. View your cards");
        System.out.println("3. View your battle log");
        System.out.println("4. Go to store");
        System.out.println("5. Go to your profile");
        System.out.println("6. Log out");

        Main.input = scanner.nextLine();
        switch (Main.input) {
            case "1":
                // GameMenu.run(scanner);
                break;
            case "2":
                // User.getUserById(Main.loggedInUserId).showCards();
                break;
            case "3":
                // BattleLogMenu.run(scanner);
                break;
            case "4":
                // StoreMenu.run(scanner);
                break;
            case "5":
                // ProfileMenu.run(scanner);
                break;
            case "6":
                Main.loggedInUserId = -1;
                // LoginMenu.run(scanner);
                break;
        }
    }
}