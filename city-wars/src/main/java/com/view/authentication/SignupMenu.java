package com.view.authentication;

import java.util.Scanner;
import java.util.regex.Matcher;

import com.Main;
import com.view.Command;
import com.controller.AuthenticationController;

public class SignupMenu {

  private AuthenticationController controller;

  public SignupMenu() {
    this.controller = new AuthenticationController();
  }

  public void run(Scanner scanner) {
    System.out.println("Welcome to the signup page!\n" +
        "\u001B[32muser create -u <username> -p <password> <password confirmation> –email <email> -n <nickname>\u001B[0m \n"
        +
        "Or you can type \u001B[32muser create -u <username> -p random –email <email> -n <nickname>\u001B[0m to generate \u001B[32random\u001B[0 password");
    Main.input = scanner.nextLine();

    Matcher matcher;

    if ((matcher = Command.SIGNUP.getMatcher(Main.input)) != null) {
      controller.signup(matcher, scanner, matcher.group("password"));
    } else if ((matcher = Command.SIGNUP_RANDOM.getMatcher(Main.input)) != null) {
      controller.signup(matcher, scanner, controller.generateRandomPassword());
    } else {
      System.out.println("Invalid command!");
    }

  }
}