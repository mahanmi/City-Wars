package com.controller;

import java.util.Scanner;
import java.util.ArrayList;
import com.model.User;

import de.vandermeer.asciitable.AsciiTable;

import com.model.Card;

public class BetModeController {
    public int welcome(Scanner scanner, User player1, User player2) {
        int bet;
        System.out.println("Welcome to the Bet Mode!");
        System.out.println("Player 1: " + player1.getNickname());
        System.out.println("Player 2: " + player2.getNickname());
        System.out.println("Please enter the amount you want to bet: (it should be less than "
                + Math.min(player1.getBalance(), player2.getBalance()) + "$)");
        while (true) {
            bet = Integer.parseInt(scanner.nextLine());
            if (bet > Math.min(player1.getBalance(), player2.getBalance())) {
                System.out.println("The bet amount should be less than "
                        + Math.min(player1.getBalance(), player2.getBalance()) + "$");
            } else {
                player1.setBalance(player1.getBalance() - bet);
                player2.setBalance(player2.getBalance() - bet);
                return bet;
            }
        }
    }

    public User firstPlayer(User player1, User player2) {
        if (Math.random() < 0.5) {
            System.out.println(player1.getNickname() + " will start the game");
            return player1;
        } else {
            System.out.println(player2.getNickname() + " will start the game");
            return player2;
        }
    }

    public void showHand(ArrayList<Card> hand) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Index", "Name", "Power", "Duration", "Damage");
        at.addRule();

        int i = 1;

        for (Card card : hand) {
            String name = card.getName();
            int power = card.getPower();
            int duration = card.getDuration();
            int damage = card.getDamage();

            at.addRow(i, name, String.valueOf(power), String.valueOf(duration), String.valueOf(damage));
            at.addRule();
            i++;
        }

        String rend = at.render();
        System.out.println(rend);
    }

    public void placeCard(User player, ArrayList<Card> board, ArrayList<Card> board2, Card card, int position)
            throws Exception {
        for (int i = position - 1; i < position - 1 + card.getDuration(); i++) {
            if (board.get(i) != null) {
                throw new Exception("You can't place a card here");
            }
        }
        if (card.getCharacter() == player.getCharacter()) {
            card.setDamage(card.getDamage() + (2 * card.getDuration()));
        }
        for (int i = position - 1; i < position - 1 + card.getDuration(); i++) {
            card.setDamage(card.getDamage() / card.getDuration());
            if (board.get(i).getPower() > board2.get(i).getPower()) {
                board2.get(i).setDamage(0);
                board.set(i, card);
            } else if (board.get(i).getPower() < board2.get(i).getPower()) {
                board.set(i, card);
                board.get(i).setDamage(0);
            } else {
                board.set(i, card);
                board.get(i).setDamage(0);
                board2.get(i).setDamage(0);
            }
        }
    }

    public void chooseCharacter(Scanner scanner, User player) {
        System.out.println(player.getNickname() + " choose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");
        System.out.println("4. Rogue");
        int character = Integer.parseInt(scanner.nextLine());
        player.setCharacter(character);
    }
}