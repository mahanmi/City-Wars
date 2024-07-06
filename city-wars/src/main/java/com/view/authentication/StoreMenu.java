package com.view.authentication;

import com.Main;
import com.model.User;
import com.model.Card;
import java.util.Scanner;

public class StoreMenu {
    public void run(Scanner scanner, User user){
        System.out.println("Welcome to the store");
        do {
            System.out.println("What do you want to do?");
            System.out.println("1. Buy a new card");
            System.out.println("2. Upgrade a card");
            Main.input = scanner.nextLine();

            switch (Main.input){
                case "1":
                    buySingleCard(scanner, user);
                    break;
                case "2":
                    upgradeCard(scanner, user);
                    break;
                case "back":
                    break;
                default:
                    System.out.println("Invalid input.");
            }  
        } while (!Main.input.equals("back"));
    }

    public void buySingleCard(Scanner scanner, User user){
        System.out.println("You have " + user.getBalance() + " coins.");
        user.showNotOwnedCards();
        System.out.println("Enter the name of the card you want to buy:");
        String cardName = scanner.nextLine();
        Card card = Main.crud.getCard(cardName);
        if (card == null){
            System.out.println("Card not found.");
            return;
        }
        if (user.getBalance() < 5*card.getUpgradeCost()){
            System.out.println("You don't have enough coins to buy this card.");
            return;
        }
        user.addCard(card);
        user.setBalance(user.getBalance() - 5*card.getUpgradeCost());
        System.out.println("You have successfully bought the card.");
    }

    public void upgradeCard(Scanner scanner, User user){
        user.showCards();
        System.out.println("Enter the name of the card you want to upgrade:");
        String cardName = scanner.nextLine();
        Card card = null;
        for (Card i : user.cards){
            if (i.getName().equals(cardName)){
                card = i;
                break;
            }
        }
        if (card == null){
            System.out.println("Card not found.");
            return;
        }
        if (user.getBalance() < card.getUpgradeCost() * Math.pow(1.25, card.getLevel() - 1)){
            System.out.println("You don't have enough coins to upgrade this card.");
            return;
        }
        user.setBalance((int) (user.getBalance() - card.getUpgradeCost() * Math.pow(1.25, card.getLevel() - 1)));
        user.upgradeCard(card);
        System.out.println("You have successfully upgraded the card.");
    }
}