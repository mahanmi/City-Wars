package com.view;

import java.util.Scanner;

import com.model.User;

public class GameMenu {
  private User opponent;
  private int opponentId = -1;

  public void run(Scanner scanner) {
    System.out.println("Welcome to the game menu!");
    System.out.println("Please select an option:");
    System.out.println("1. Play 1v1 game");
    System.out.println("2. Play bet game");
    System.out.println("3. Exit");

    int option = Integer.parseInt(scanner.nextLine());

    while (opponentId == -1) {
      System.out.println("your opponent have to login first!/n");
      opponentId = Integer.parseInt(scanner.nextLine());
      
    }

  }
}
