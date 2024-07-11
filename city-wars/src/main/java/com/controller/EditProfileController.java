package com.controller;

import java.util.Scanner;

import com.Main;
import com.model.Card;
import com.model.User;
import com.view.authentication.Captcha;

public class EditProfileController {

  private AuthenticationController authenticationController = new AuthenticationController();

  public void showProfile(User user) {
    System.out.println("Username: " + user.getUsername());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Nickname: " + user.getNickname());
    System.out.println("Level: " + user.getLevel());
    System.out.println("Balance: " + user.getBalance() + "$");
    String securityQuestion;
    if (user.getSecurityQuestionID() == 1) {
      securityQuestion = "What is your father's name ?";
    } else if (user.getSecurityQuestionID() == 2) {
      securityQuestion = "What is your favorite color ?";
    } else {
      securityQuestion = "What was the name of your first pet?";
    }
    System.out.println("Security question: " + securityQuestion);
    System.out.println("Security question's answer: " + user.getSecurityQuestionAnswer());
    System.out.println();
    for (Card i : user.cards) {
      System.out.println(i.getCardInfo());
    }
  }

  public void changeUsername(Scanner scanner, User user) {
    System.out.println("Please enter your new username");
    while (true) {
      String newUsername = scanner.nextLine();
      if (authenticationController.isUsernameValid(newUsername)) {
        user.setUsername(newUsername, Main.loggedInUserId);
        System.out.println("Username changed successfully!");
        return;
      } else {
        System.out.println("Invalid username! Try again");
      }
    }
  }

  public void changeNickname(Scanner scanner, User user) {
    System.out.println("Please enter your new nickname");
    String newNickname = scanner.nextLine();
    user.setNickname(newNickname);
    System.out.println("Nickname changed successfully!");
  }

  public void changePassword(Scanner scanner, User user) {
    String oldPassword;
    String newPassword;
    int captcha;

    System.out.println("Enter you old password");
    while (true) {
      oldPassword = scanner.nextLine();
      if (oldPassword.equals(user.getPassword())) {
        break;
      }
      System.out.println("Password WRONG\n Try again");
    }

    System.out.println("Enter your new password");
    while (true) {
      newPassword = scanner.nextLine();
      if (authenticationController.isPasswordValid(newPassword) && !newPassword.equals(oldPassword)) {
        break;
      } else if (newPassword.equals(oldPassword)) {
        System.out.println("Please enter a new password!");
      } else {
        System.out.println("Try again");
      }
    }

    while (true) {
      captcha = new Captcha().generateCaptcha();
      Main.input = scanner.nextLine();
      if (Integer.parseInt(Main.input) == captcha) {
        break;
      } else {
        System.out.println("Captcha WRONG \n Try again");
      }
    }

    System.out.println("Please enter your new password again (to confirm it)");
    while (true) {
      Main.input = scanner.nextLine();
      if (Main.input.equals(newPassword)) {
        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
        break;
      } else {
        System.out.println("password confirmation is incorrect!\n Try again");
      }
    }
  }

  public void changeEmail(Scanner scanner, User user) {
    System.out.println("Please enter your new email address");
    while (true) {
      Main.input = scanner.nextLine();
      if (authenticationController.isEmailValid(Main.input)) {
        user.setEmail(Main.input);
        System.out.println("Email address changed successfully!");
        return;
      } else {
        System.out.println("This address is not valid! \n Try again");
      }
    }
  }

}
