package com.view.authentication;

import com.model.User;
import com.model.Card;
import com.view.authentication.Captcha;
import java.util.Scanner;

import com.Main;

public class EditProfileMenu {

  public void run(Scanner scanner, User user) {
    System.out.println("Welcome to profile menu!");
    while (!com.Main.input.equals("back")) {
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
          showProfile(user);
          break;

        case "2":
          changeUsername(scanner, user);
          break;

        case "3":
          changeNickname(scanner, user);
          break;

        case "4":
          changePassword(scanner, user);
          break;

        case "5":
          changeEmail(scanner, user);
          break;  

        case "back":
          break;
      }
    }
  }

  private void showProfile(User user) {
    System.out.println("Username: " + user.getUsername());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Nickname: " + user.getNickname());
    System.out.println("Balance: " + user.getBalance() + "$");
    String securityQuastion;
    if (user.getSecurityQuestionID() == 1) {
      securityQuastion = "What is your father’s name ?";
    } else if (user.getSecurityQuestionID() == 2) {
      securityQuastion = "What is your favorite color ?";
    } else {
      securityQuastion = "What was the name of your first pet?";
    }
    System.out.println("Security quastion: " + securityQuastion);
    System.out.println("Security quastion's answer: " + user.getSecurityQuestionAnswer());
    System.out.println();
    for (Card i : user.cards) {
      com.Main.crud.showCard(i);
    }
  }

  private void changeUsername(Scanner scanner, User user) {
    System.out.println("Please enter your new username");
    while (true) {
      String newUsername = scanner.nextLine();
      if (isUsernameValid(newUsername)) {
        user.setUsername(newUsername, Main.loggedInUserId);
        System.out.println("Username changed successfully!");
        return;
      } else {
        System.out.println("Invalid username! Try again");
      }
    }
  }

  private void changeNickname(Scanner scanner, User user) {
    System.out.println("Please enter your new nickname");
    String newNickname = scanner.nextLine();
    user.setNickname(newNickname);
    System.out.println("Nickname changed successfully!");
  }

  private void changePassword(Scanner scanner, User user) {
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
      if (isPasswordValid(newPassword) && !newPassword.equals(oldPassword)) {
        break;
      }
      else if(!isPasswordValid(newPassword)){
        System.out.println("This password is invalid \n Try again");
      }
      else{
        System.out.println("Please enter a new password!");
      }
    }

    while (true) {
      captcha = new Captcha().generateCaptcha();
      Main.input = scanner.nextLine();
      if (Integer.parseInt(Main.input) == captcha) {
        break;
      }
      else{
        System.out.println("Captcha WRONG \n Try again");
      }
    }

    System.out.println("Please enter your new password again (to verify it)");
    while (true) {
      Main.input = scanner.nextLine();
      if(Main.input.equals(newPassword)){
        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
        break;
      }
      else{
        System.out.println("Current password is incorrect!\n Try again");
      }
    }
  }

  private void changeEmail(Scanner scanner, User user){
    System.out.println("Please enter your new email address");
    while (true) {
      Main.input = scanner.nextLine(); 
      if(isEmailValid(Main.input)){
        user.setEmail(Main.input);
        System.out.println("Email address changed successfully!");
        return;
      }
      else{
        System.out.println("This address is not valid! \n Try again");
      }
    }
  }

  private boolean isUsernameValid(String username) {
    if (username.matches("[a-zA-Z0-9_]+")) {
      return true;
    }
    return false;
  }

  private boolean isPasswordValid(String password) {
    if (password.length() >= 8) {
      if (password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")
          && password.matches(".*[^a-zA-Z0-9].*")) {
        return true;
      }
      System.out.println(
          "Password must contain at least one lowercase letter, one uppercase letter and one special character!");
    }
    System.out.println("Password must be at least 8 characters long!");
    return false;
  }

  private boolean isEmailValid(String email) {
    if (email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.com$")) {
        return true;
    }
    return false;
}

}