package com.view;

import com.Main;
import com.controller.StoreController;
import com.model.User;
import java.util.Scanner;

public class StoreMenu {
    private StoreController controller = new StoreController();

    public void run(Scanner scanner, User user) {
        System.out.println("Welcome to the store");
        do {
            System.out.println("What do you want to do?");
            System.out.println("1. Buy a new card");
            System.out.println("2. Upgrade a card");
            Main.input = scanner.nextLine();

            switch (Main.input) {
                case "1":
                    controller.buySingleCard(scanner, user);
                    break;
                case "2":
                    controller.upgradeCard(scanner, user);
                    break;
                case "back":
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        } while (!Main.input.equals("back"));
    }

}