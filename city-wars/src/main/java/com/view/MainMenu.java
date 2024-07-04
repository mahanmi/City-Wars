package com.view;

import java.util.Scanner;

import com.Main;
import com.controller.MenuController;
import com.view.authentication.LoginMenu;

public class MainMenu {
  private MenuController controller;

  public MainMenu() {
    this.controller = new MenuController();
  }

  public void run(Scanner scanner) {
    

    System.out.println("Welcome to the main menu!\n");

    controller.loginProcess(scanner);

    while (!Main.input.equals("6")) {

      System.out.println("What would you like to do?");
      System.out.println("1. Play a game");
      System.out.println("2. View your cards");
      System.out.println("3. View your battle log");
      System.out.println("4. Go to store");
      System.out.println("5. Go to your profile");
      System.out.println("6. Log out");
      System.out.println("7. Exit");

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
          controller.logout();
          break;
        case "7":
          controller.exit();
          break;
      }
    }
  }
}
