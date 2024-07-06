package com.view;

import java.util.Scanner;

import com.Main;
import com.controller.MatchHistoryController;
import com.model.game.Sort;

public class MatchHistory {
  private MatchHistoryController controller = new MatchHistoryController();
  private String help = "if you want to sort the match history by \u001B[34mtime / opponent / result / prize\u001B[0m, type 'sort' followed by the sorting method and order\n"
      + "\u001B[34m-a\u001B[0m for ascending order and \u001B[34m-d\u001B[0m for descending order[ example : \u001B[34msort time -a\u001B[0m ]\n"

      + "type \u001B[34mpage n\u001B[0m to go to page n";

  private int page = 1;
  private Sort sort = Sort.TIME;
  private boolean ascending = false;

  public void run(Scanner scanner) {
    System.out.println(controller.userMatchHistory(Main.loggedInUser, Main.loggedInUserId, page, sort, ascending));
    System.out.println(help);
    Main.input = scanner.nextLine();
    while (!Main.input.equals("back")) {
      try {
        if (Main.input.startsWith("sort")) {
          sort = Sort.valueOf(Main.input.split(" ")[1].toUpperCase());
          ascending = Main.input.split(" ")[2].equals("-a");
          System.out
              .println(controller.userMatchHistory(Main.loggedInUser, Main.loggedInUserId, page, sort, ascending));
        } else if (Main.input.startsWith("page")) {
          page = Integer.parseInt(Main.input.split(" ")[1]);
          System.out
              .println(controller.userMatchHistory(Main.loggedInUser, Main.loggedInUserId, page, sort, ascending));
        } else {
          System.out.println("Invalid command");
        }
        Main.input = scanner.nextLine();
      } catch (Exception e) {
        System.out.println("Invalid command");
        Main.input = scanner.nextLine();
      }
    }
  }
}
