package com.controller;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

import org.apache.commons.lang3.RandomStringUtils;

import com.Main;
import com.model.User;
import com.view.authentication.SignupMenu;
import com.view.Captcha;

public class AuthenticationController {

    public void run(Scanner scanner) {
        SignupMenu signupMenu = new SignupMenu();
        signupMenu.run(scanner);
    }

    public void signup(Matcher matcher, Scanner scanner, String password) {
        String username = matcher.group("username");
        String passwordConfirmation = (matcher.group("passwordConfirmation") != null)
                ? matcher.group("passwordConfirmation")
                : password;
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        int securityQuestionID;
        String securityQuestionAnswer;

        if (username != null && password != null && passwordConfirmation != null && email != null && nickname != null) {
            if (isUsernameValid(username)) {

                if (Main.crud.getUserIdByUsername(username) == -1) {
                    if (isPasswordValid(password)) {

                        if (password.equals(passwordConfirmation)) {
                            if (isEmailValid(email)) {
                                // Ask security question
                                System.out.println("User created successfully. Please choose a security question :\n" +
                                        "1-What is your father’s name ?\n" +
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
                                Main.crud.addUser(new User(username, passwordConfirmation, email, nickname, "NULL",
                                        securityQuestionID, securityQuestionAnswer));

                                System.out.println("User created successfully!");
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

    public String generateRandomPassword() {
        char[] possibleCharacters = (new String(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?"))
                .toCharArray();
        Random random = new Random();
        int randomStrLength = random.nextInt() % 10 + 8;
        String randomStr = RandomStringUtils.random(randomStrLength, 0, possibleCharacters.length - 1, false, false,
                possibleCharacters, new SecureRandom());
        return randomStr;
    }
}
