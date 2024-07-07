package com.controller;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

import org.apache.commons.lang3.RandomStringUtils;

import com.Main;
import com.model.User;
import com.view.authentication.Captcha;
import com.controller.Admin.AdminController;

public class AuthenticationController {

    public void login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if (username != null && password != null) {
            int userId = Main.crud.getUserId(username);
            if (userId != -1) {
                User user = Main.crud.getUser(userId);
                if (user.getPassword().equals(password)) {
                    Main.loggedInUserId = userId;
                    Main.loggedInUser = user;
                    System.out.println("\u001B[32muser logged in successfully!\u001B[0m");
                } else {
                    System.out.println("Password and Username don’t match!");
                }
            } else {
                System.out.println("Username doesn’t exist!");
            }
        } else {
            System.out.println("You must fill all fields!");
        }
    }

    public void adminLogin(Matcher matcher) {
        String password = matcher.group("password");

        if (password != null) {
            if (password.equals("admin")) {
                System.out.println("\u001B[32madmin logged in successfully!\u001B[0m");
                Main.loggedInUserId = 0;
                AdminController adminController = new AdminController();
                adminController.adminMenu(new Scanner(System.in));
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("You must fill all fields!");
        }
    }

    public void signup(Matcher matcher, Scanner scanner, boolean isRandomPassword) {
        String username = matcher.group("username");
        String password = (!isRandomPassword) ? matcher.group("password") : generateRandomPassword();
        String passwordConfirmation = (!isRandomPassword) ? matcher.group("passwordConfirmation") : password;
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        int securityQuestionID;
        String securityQuestionAnswer;

        if (username != null && password != null && passwordConfirmation != null && email != null && nickname != null) {
            if (isUsernameValid(username)) {
                if (Main.crud.getUserId(username) == -1) {
                    if (isPasswordValid(password)) {
                        if (password.equals(passwordConfirmation)) {
                            if (isEmailValid(email)) {
                                // Ask security question
                                System.out.println("User created successfully. Please choose a security question :\n" +
                                        "1-What is your father's name ?\n" +
                                        "2-What is your favorite color ?\n" +
                                        "3-What was the name of your first pet?");
                                Main.input = scanner.nextLine();
                                while (!(Main.input.equals("1") || Main.input.equals("2") || Main.input.equals("3"))) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    Main.input = scanner.nextLine();
                                }
                                securityQuestionID = Integer.parseInt(Main.input);
                                System.out.println("Please enter the answer to your security question :");
                                securityQuestionAnswer = scanner.nextLine();
                                Captcha captcha = new Captcha();
                                int captchaCode, userCaptcha;
                                System.out.println("Please enter the following captcha code :");
                                captchaCode = captcha.generateCaptcha();
                                Main.input = scanner.nextLine();
                                userCaptcha = Integer.parseInt(Main.input);
                                while (captchaCode != userCaptcha) {
                                    System.out.println("Invalid captcha code, please try again.");
                                    System.out.println("Please enter the following captcha code :");
                                    captchaCode = captcha.generateCaptcha();
                                    Main.input = scanner.nextLine();
                                    userCaptcha = Integer.parseInt(Main.input);
                                }

                                User user = new User(username, password, email, nickname, "",
                                        securityQuestionID, securityQuestionAnswer);

                                Main.crud.addUser(user);

                                Main.loggedInUserId = Main.crud.getUserId(username);
                                Main.loggedInUser = user;

                                System.out.println("User created & Logged in successfully!");
                                MenuController menuController = new MenuController();
                                menuController.greetNewUser(user, scanner);
                            } else {
                                System.out.println("Email is invalid!");
                            }
                        } else {
                            System.out.println("Passwords do not match!");
                        }
                    }
                } else {
                    System.out.println("Username already exists!");
                }
            } else {
                System.out.println("Username is invalid!");
            }
        } else {
            System.out.println("You must fill all fields!");
        }
    }

    protected boolean isUsernameValid(String username) {
        if (username.matches("[a-zA-Z0-9_]+")) {
            return true;
        }
        return false;
    }

    protected boolean isPasswordValid(String password) {
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

    protected boolean isEmailValid(String email) {
        if (email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.com$")) {
            return true;
        }
        return false;
    }

    private String generateRandomPassword() {
        char[] possibleCharacters = (new String(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*_=+"))
                .toCharArray();
        Random random = new Random();
        int randomStrLength = random.nextInt() % 10 + 17;
        String randomStr = RandomStringUtils.random(randomStrLength, 0, possibleCharacters.length - 1, false, false,
                possibleCharacters, new SecureRandom());
        while (!isPasswordValid(randomStr)) {
            randomStr = RandomStringUtils.random(randomStrLength, 0, possibleCharacters.length - 1, false, false,
                    possibleCharacters, new SecureRandom());
        }
        System.out.println("Your random password is : \u001B[31m" + randomStr + "\u001B[0m");
        return randomStr;
    }

    public void resetPassword(Scanner scanner) {

        System.out.print("Enter your Username :");
        Main.input = scanner.nextLine();
        User user = Main.crud.getUser(Main.input);
        if (askSecurityQuestion(scanner, user)) {
            System.out.print("Enter New Password :");
            String password = scanner.nextLine();
            System.out.print("Password confirmation :");
            String passwordConfirmation = scanner.nextLine();
            while (isPasswordValid(password) && !password.matches(passwordConfirmation)) {
                if (password.matches(passwordConfirmation)) {
                    if (isPasswordValid(password)) {
                        user.setPassword(password);
                        System.out.println("your password changed successfully!");
                    }

                    System.out.println("Password is invalid!");
                } else {
                    System.out.println("Passwords do not match!");
                }
            }
        } else {
            System.out.println("Wrong Answer! Are you really " + user.getNickname() + "?\n" + "Try Again!");
        }

    }

    private boolean askSecurityQuestion(Scanner scanner, User user) {
        String question;
        switch (user.getSecurityQuestionID()) {
            case 1:
                question = "What is your father's name ?";
                break;

            case 2:
                question = "What is your favorite color ?";

            case 3:
                question = "What was the name of your first pet?";

            default:
                System.out.println("Something went wrong in initializing SecurityQuestionID");
                return false;
        }
        System.out.println(question);
        if (scanner.nextLine().matches(user.getSecurityQuestionAnswer())) {
            return true;
        }
        return false;
    }
}
