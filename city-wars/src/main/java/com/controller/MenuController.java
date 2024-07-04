package com.controller;

import com.*;
import com.model.*;
import com.view.authentication.LoginMenu;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class MenuController {

    public void loginProcess(Scanner scanner) {
        while (!haveLoggedIn()) {
            System.out.println("You are not logged in. Please log in to continue.");
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.run(scanner);
        }
    }

    public boolean haveLoggedIn() {
        if (Main.loggedInUserId == -1) {
            return false;
        }
        return true;
    }

    public void greetNewUser(User user, Scanner scanner) {

        System.out.println("Welcome to the game! You have been given \u001B[33m1000\u001B[0m coins to start with.");
        ArrayList<Card> starterCards = starterCards();
        System.out.println("your cards will be displayed one by one press enter to continue\n" +
                "(enter \u001B[34mshow all\u001B[0m to see all cards at once)\n"
                + "You have been given the following cards to start with:");
        Main.input = scanner.nextLine();
        user.addCards(starterCards);
        if (Main.input.equals("show all")) {
            printCards(starterCards);
        } else {
            for (Card card : starterCards) {
                System.out.println(card);
                scanner.nextLine();
            }
        }
        System.out.println("\u001B[32mYou can view your cards at any time by selecting view my cards option in mainMenu.\u001B[0m");
        System.out.println("You can start playing the game now. Good luck!\n");
    }

    private ArrayList<Card> starterCards() {
        ArrayList<Card> allCards = Main.crud.getAllCards();
        ArrayList<Card> starterCards = new ArrayList<Card>();
        Random rand = new Random();
        for (int i = 0; i < Main.STARTER_CARDS; i++) {
            int randomIndex = rand.nextInt(allCards.size());
            starterCards.add(allCards.get(randomIndex));
            allCards.remove(randomIndex);
        }
        return starterCards;
    }

    private void printCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            System.out.print(card);
        }
    }

    public void logout() {
        Main.loggedInUserId = -1;
        Main.loggedInUser = null;
        System.out.println("Logged out successfully!\n");
    }

    public void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}