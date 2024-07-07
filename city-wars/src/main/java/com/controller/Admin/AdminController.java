package com.controller.Admin;

import java.util.ArrayList;
import java.util.Scanner;
import com.Main;
import com.model.*;

public class AdminController {
    final String password = "admin";

    public void adminMenu(Scanner scanner) {
        while (!Main.input.equals("5")) {
            System.out.println("What would you like to do?");
            System.out.println("1. Add a card");
            System.out.println("2. Edit a card");
            System.out.println("3. Delete a card");
            System.out.println("4. View all players");
            System.out.println("5. Logout");

            Main.input = scanner.nextLine();
            switch (Main.input) {
                case "1":
                    addCard(scanner);
                    break;
                case "2":
                    editCard(scanner);
                    break;
                case "3":
                    deleteCard(scanner);
                    break;
                case "4":
                    Main.crud.showAllUsers();
                    break;
                case "5":
                    Main.loggedInUserId = -1;
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    public void addCard(Scanner scanner) {
        System.out.println("Enter the name of the card:");
        String name = scanner.nextLine();
        System.out.println("Enter the power of the card:");
        int power = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the duration of the card:");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the damage of the card:");
        int damage = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the upgrade level of the card:");
        int upgradeLevel = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the upgrade cost of the card:");
        int upgradeCost = Integer.parseInt(scanner.nextLine());

        Card card = new Card(name, power, duration, damage, upgradeLevel, upgradeCost);

        Main.crud.addCard(card);
        System.out.println("Card added successfully.");
    }

    public void editCard(Scanner scanner) {
        ArrayList<Card> cards = Main.crud.getAllCards();
        for (Card i : cards) {
            System.out.println(i.getId() + ". " + i.getName());
        }
        System.out.println("Enter the id of the card you want to edit:");
        Main.input = scanner.nextLine();
        if (Main.input.equals("back")) {
            return;
        }
        Card card = cards.get(Integer.parseInt(Main.input) - 1);
        int id = card.getId();
        System.out.println(card.getCardInfo());

        System.out.println("Enter card's new name: [default: " + card.getName() + "]");
        Main.input = scanner.nextLine();
        String name = (Main.input.equals("")) ? card.getName() : Main.input;
        System.out.println("Enter card's new power: [default: " + card.getPower() + "]");
        Main.input = scanner.nextLine();
        int power = (Main.input.equals("")) ? card.getPower() : Integer.parseInt(Main.input);
        System.out.println("Enter card's new duration: [default: " + card.getDuration() + "]");
        Main.input = scanner.nextLine();
        int duration = (Main.input.equals("")) ? card.getDuration() : Integer.parseInt(Main.input);
        System.out.println("Enter card's new damage: [default: " + card.getDamage() + "]");
        Main.input = scanner.nextLine();
        int damage = (Main.input.equals("")) ? card.getDamage() : Integer.parseInt(Main.input);
        System.out.println("Enter card's new upgrade level: [default: " + card.getUpgradeLevel() + "]");
        Main.input = scanner.nextLine();
        int upgradeLevel = (Main.input.equals("")) ? card.getUpgradeLevel()
                : Integer.parseInt(Main.input);
        System.out.println("Enter card's new upgrade cost: [default: " + card.getUpgradeCost() + "]");
        Main.input = scanner.nextLine();
        int upgradeCost = (Main.input.equals("")) ? card.getUpgradeCost()
                : Integer.parseInt(Main.input);

        System.out.println("Are you sure you want to edit this card? (y/n)[default: n]");
        Main.input = scanner.nextLine();
        if (Main.input.equals("y")) {
            Card newCard = new Card(id, name, power, duration, damage, upgradeLevel, upgradeCost);
            Main.crud.updateCard(newCard);
            System.out.println("Card edited successfully.");
        } else {
            return;
        }
    }

    public void deleteCard(Scanner scanner) {
        ArrayList<Card> cards = Main.crud.getAllCards();
        for (Card i : cards) {
            System.out.println(i.getId() + ". " + i.getName());
        }
        System.out.println("Enter the id of the card you want to delete:");
        Main.input = scanner.nextLine();
        if (Main.input.equals("back")) {
            return;
        }
        int id = Integer.parseInt(scanner.nextLine());
        Card card = Main.crud.getCard(id);
        System.out.println(card.getCardInfo());

        System.out.println("Are you sure you want to delete this card? (y/n)[default: n]");
        Main.input = scanner.nextLine();
        if (Main.input.equals("y")) {
            Main.crud.deleteCard(id);
            System.out.println("Card deleted successfully.");
        } else {
            return;
        }
    }
}