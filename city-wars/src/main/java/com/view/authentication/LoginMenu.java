package com.view.authentication;

import java.util.Scanner;
import java.util.regex.Matcher;

import com.controller.AuthenticationController;

import com.Main;
import com.view.Command;

public class LoginMenu {

  private AuthenticationController controller;
  private final String Welcome = "Welcome to the login page!\n" +
      "\u001B[32muser login -u <username> -p <password>\u001B[0m\n" +
      "enter \u001B[34msignup\u001B[0m to go to the signup page\n" +
      "enter \u001B[34mforgot password\u001B[0m reset your password \n" +
      "enter \u001B[34mexit\u001B[0m to return to the main menu";

  private static final int BASE_LOCKOUT_TIME = 5;

  private long lockoutEndTime = 0;
  private int failedAttempts = 0;

  private int remainingTime() {
    return (int) ((lockoutEndTime - System.currentTimeMillis()) / 1000);
  }

  public LoginMenu() {
    this.controller = new AuthenticationController();
  }

  public void run(Scanner scanner) {

    System.out.println(Welcome);

    Matcher matcher;

    while (Main.loggedInUserId == -1) {
      Main.input = scanner.nextLine();
      if (lockoutEndTime > System.currentTimeMillis()) {
        System.out.println("Try again in " + remainingTime() + " seconds");
      } else {
        if ((matcher = Command.SIGNUP_PAGE.getMatcher(Main.input)) != null) {
          SignupMenu signupMenu = new SignupMenu();
          signupMenu.run(scanner);
        } else if ((matcher = Command.LOGIN.getMatcher(Main.input)) != null) {
          controller.login(matcher);
          if (Main.loggedInUserId == -1) {
            failedAttempts++;
            lockoutEndTime = System.currentTimeMillis() + BASE_LOCKOUT_TIME * failedAttempts * 1000;
            System.out.println("Try again in " + 5 * failedAttempts + " seconds");
          }
        } else if ((matcher = Command.FORGOT_PASSWORD.getMatcher(Main.input)) != null) {
          controller.resetPassword(scanner);
          System.out.println(Welcome);
        } else if ((matcher = Command.EXIT.getMatcher(Main.input)) != null) {
          System.out.println("Returning to main menu");
          return;
        } else {
          System.out.println("Invalid command!");
        }
      }
    }
  }
}
