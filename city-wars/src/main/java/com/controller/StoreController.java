package com.controller;

import java.util.Scanner;

import com.Main;
import com.model.Card;
import com.model.User;

public class StoreController {
  public void buySingleCard(Scanner scanner, User user) {
    System.out.println("You have " + user.getBalance() + " coins.");
    user.showNotOwnedCards();
    System.out.println("Enter the name of the card you want to buy:");
    String cardName = scanner.nextLine();
    if(cardName.equals("back")) return;
    Card card = Main.crud.getCard(cardName);
    if (card == null) {
      System.out.println("Card not found.");
      return;  
    } if (user.getBalance() < 3 * card.getUpgradeCost()) {
      System.out.println("You don't have enough coins to buy this card.");
      return;
    }
    user.addCard(card);
    user.setBalance(user.getBalance() - 3 * card.getUpgradeCost());
    System.out.println("You have successfully bought the card.");
  }

  public void upgradeCard(Scanner scanner, User user) {
    user.showCards();
    System.out.println("Enter the name of the card you want to upgrade:");
    String cardName = scanner.nextLine();
    if(cardName.equals("back")) return;
    Card card = null;
    for (Card i : user.cards) {
      if (i.getName().equals(cardName)) {
        card = i;
        break;
      }
    }
    if (card == null) {
      System.out.println("Card not found.");
      return;
    } else if (user.getBalance() < card.getUpgradeCost() * Math.pow(1.25, card.getLevel() - 1)) {
      System.out.println("You don't have enough coins to upgrade this card.");
      return;
    }
    user.setBalance((int) (user.getBalance() - card.getUpgradeCost() * Math.pow(1.25, card.getLevel() - 1)));
    user.upgradeCard(card);
    System.out.println("You have successfully upgraded the card.");
  }
}
