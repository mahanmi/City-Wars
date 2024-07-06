package com.view.authentication;

import java.util.Scanner;
import java.util.regex.Matcher;

import com.Main;
import com.view.Command;
import com.controller.AuthenticationController;

public class SignupMenu {

  private AuthenticationController controller;
  private final String welcome = "Welcome to the signup page!\n" +
      "\u001B[32muser create -u <username> -p <password> <password confirmation> -email <email> -n <nickname>\u001B[0m \n"
      +
      "Or you can type \u001B[32muser create -u <username> -p random -email <email> -n <nickname>\u001B[0m to generate \u001B[31mrandom\u001B[0m password\n"
      + "enter \u001B[34mexit\u001B[0m to exit";

  public SignupMenu() {
    this.controller = new AuthenticationController();
  }

  public void run(Scanner scanner) {
    System.out.println(welcome);

    Matcher matcher;

    while (Main.loggedInUserId == -1) {
      Main.input = scanner.nextLine();
      if ((matcher = Command.SIGNUP.getMatcher(Main.input)) != null) {
        controller.signup(matcher, scanner, false);
      } else if ((matcher = Command.SIGNUP_RANDOM.getMatcher(Main.input)) != null) {
        controller.signup(matcher, scanner, true);
      } else if (Main.input.equals("exit")) {
        System.out.println("Returning to login menu");
        return;
      } else {
        System.out.println("Invalid command!");
      }
    }
  }
}