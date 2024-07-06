package com.view;

import com.model.User;
import java.util.Scanner;

import com.Main;
import com.controller.EditProfileController;

public class EditProfileMenu {

  private EditProfileController controller = new EditProfileController();

  public void run(Scanner scanner, User user) {
    System.out.println("Welcome to profile menu!");
    while (!Main.input.equals("back")) {
      System.out.println("What do you want to do?");
      System.out.println("1. See your profile");
      System.out.println("2. Change your username");
      System.out.println("3. Change your nickname");
      System.out.println("4. Change your password");
      System.out.println("5. Change your email address");

      Main.input = scanner.nextLine();

      while (!(Main.input.equals("1") || Main.input.equals("2") || Main.input.equals("3")
          || Main.input.equals("4") || Main.input.equals("5") || Main.input.equals("back"))) {
        System.out.println("Please enter a valid number!");
        Main.input = scanner.nextLine();
      }
      switch (Main.input) {
        case "1":
          controller.showProfile(user);
          break;

        case "2":
          controller.changeUsername(scanner, user);
          break;

        case "3":
          controller.changeNickname(scanner, user);
          break;

        case "4":
          controller.changePassword(scanner, user);
          break;

        case "5":
          controller.changeEmail(scanner, user);
          break;

        case "back":
          break;
      }
    }
  }

}