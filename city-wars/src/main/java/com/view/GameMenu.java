package com.view;

import java.util.Scanner;

import com.Main;
import com.model.User;
import com.view.authentication.*;;

public class GameMenu {
  private User opponent = null;
  private int opponentId = -1;
  private String welcome = "Welcome to the game menu!\n" + "Please select an option:\n" + "1. Play 1v1 game\n"
      + "2. Play bet game\n" + "3. Exit\n";

  public void run(Scanner scanner) {

    while (opponentId == -1) {
      System.out.println("your opponent have to login first!\n");
      LoginMenu loginMenu = new LoginMenu();
      opponent = loginMenu.run(scanner);
      opponentId = Main.crud.getUserId(opponent.getUsername());
    }

    System.out.println(welcome);

    Main.input = scanner.nextLine();

    switch (Main.input) {
      case "1":
        System.out.println("1v1 game");
        break;
      case "2":
        BetMode betMode = new BetMode();
        try {
          betMode.run(scanner, Main.loggedInUser, opponent);
        } catch (Exception e) {
          // Handle the exception here
          e.printStackTrace();
        }
        break;
      case "3":
        System.out.println("exit");
        break;
    }

  }
}
