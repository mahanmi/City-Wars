package com.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import com.model.User;
import com.model.game.Prize;
import de.vandermeer.asciitable.AsciiTable;
import com.Main;
import com.model.Card;
import com.view.BetMode;

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

            if (name.startsWith("\u001B[33m")) {
                at.addRow(i, name.replaceAll("\\u001B\\[[;\\d]*m", ""), "-", "-", "-");
            }

            else {
                at.addRow(i, name, String.valueOf(power), String.valueOf(duration), String.valueOf(damage));
            }
            at.addRule();
            i++;
        }

        String rend = at.render();
        System.out.println(rend);
    }

    public int[] placeCard(Scanner scanner, User player, User player2, ArrayList<Card> board, ArrayList<Card> board2,
            ArrayList<Card> hand1, ArrayList<Card> hand2,
            Card card, int round, boolean hide) {
        BetMode betMode = new BetMode();
        int[] result = new int[2];
        result[1] = 0;

        if (card.getName().equals("\u001B[33mHole Changer\u001B[0m")) {
            holeChanger(board, board2);
        } else if (card.getName().equals("\u001B[33mRepair\u001B[0m")) {
            repair(board);
        } else if (card.getName().equals("\u001B[33mSkip Rounds\u001B[0m")) {
            round--;
        } else if (card.getName().equals("\u001B[33mHand Hider\u001B[0m")) {
            result[1] = 1;
        } else if (card.getName().equals("\u001B[33mCard Swapper\u001B[0m")) {
            cardSwapper(scanner, hand1, hand2);
        }       

        else {
            int position = 22;
            outerloop: while (position < 0 || position > 20) {
                betMode.showBoard(board, board2, player, player2);
                System.out.println("Please select the position you want to play this card in:");
                while (true) {
                    try {
                        System.out.println("Enter a number between 1 and " + (22 - card.getDuration()));
                        position = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number");
                        continue;
                    }
                }
                if (position < 1 || position > 22 - card.getDuration()) {
                    System.out.println("You can't place a card here");
                    continue;
                }
                for (int i = position - 1; i < position - 1 + card.getDuration(); i++) {
                    if (board.get(i) != null) {
                        System.out.println("You can't place a card here");
                        continue outerloop;
                    }
                }
                for (int i = position - 1; i < position - 1 + card.getDuration(); i++) {
                    board.set(i, card);
                }
                break;
            }
        }
        betMode.showBoard(board, board2, player, player2);
        result[0] = round;
        return result;
    }

    public void chooseCharacter(Scanner scanner, User player) {
        System.out.println(player.getNickname() + " choose your character:");
        System.out.println("1. Warrior    2. Mage    3. Archer    4. Rogue");
        int character = Integer.parseInt(scanner.nextLine());
        player.setCharacter(character);
    }

    public int[] timeline(ArrayList<Card> board1, ArrayList<Card> board2, User player1, User player2, int hp1,
            int hp2) {
        int D1 = 0;
        int D2 = 0;

        int[] result = new int[3];

        for (int i = 0; i < board1.size(); i++) {
            System.out.println((i + 1) + ":");
            Card card1 = board1.get(i);
            Card card2 = board2.get(i);

            if (card1 == null || card1.getDuration() == 0) {
                D1 = 0;
            } else {
                D1 = card1.getDamage() / card1.getDuration();
            }

            if (card2 == null || card2.getDuration() == 0) {
                D2 = 0;
            } else {
                D2 = card2.getDamage() / card2.getDuration();
            }

            if (card1 != null && card2 == null) {
                if (card1.getCharacter() == player1.getCharacter()) {
                    hp2 -= D1 + 2;
                } else if (card1.getName().equals("\u001B[33mHeal\u001B[0m")) {
                    hp1 += card1.getDamage();
                } else {
                    hp2 -= D1;
                }
                System.out.println(player2.getNickname() + "'s HP: \u001B[31m" + hp2 + "\u001B[0m");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else if (card1 == null && card2 != null) {
                if (card2.getCharacter() == player2.getCharacter()) {
                    hp1 -= D2 + 2;
                } else if (card2.getName().equals("\u001B[33mHeal\u001B[0m")) {
                    hp2 += card2.getDamage();
                } else {
                    hp1 -= D2;
                }
                System.out.println(player1.getNickname() + "'s HP: \u001B[31m" + hp1 + "\u001B[0m");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (card1 != null && card2 != null) {
                if (card1.getPower() > card2.getPower()) {
                    if (card1.getCharacter() == player1.getCharacter()) {
                        D1 += 2;
                    } 
                    if (card2.getName().equals("\u001B[33mHeal\u001B[0m")) {
                        hp2 += card2.getDamage();
                        System.out.println(player2.getNickname() + "'s HP: \u001B[32m" + hp2 + "\u001B[0m");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!card2.getName().equals("\u001B[33mShield\u001B[0m")) {
                        hp2 -= D1;
                        System.out.println(player2.getNickname() + "'s HP: \u001B[31m" + hp2 + "\u001B[0m");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (card2.getName().equals("\u001B[33mShield\u001B[0m")) {
                        System.out.println(player2.getNickname() + "'s HP: \u001B[34m" + hp2 + "\u001B[0m");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                    }        
   
                } else if (card1.getPower() < card2.getPower()) {
                    if (card2.getCharacter() == player2.getCharacter()) {
                        D2 += 2;
                    } 
                    if (card1.getName().equals("\u001B[33mHeal\u001B[0m")) {
                        hp1 += card1.getDamage();
                        System.out.println(player1.getNickname() + "'s HP: \u001B[32m" + hp1 + "\u001B[0m");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!card1.getName().equals("\u001B[33mShield\u001B[0m")) {
                        hp1 -= D2;
                        System.out.println(player1.getNickname() + "'s HP: \u001B[31m" + hp1 + "\u001B[0m");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if (card1.getName().equals("\u001B[33mShield\u001B[0m")) {
                        System.out.println(player1.getNickname() + "'s HP: \u001B[34m" + hp1 + "\u001B[0m");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                    }        
                }
                else {
                    if (card1.getName().equals("\u001B[33mHeal\u001B[0m")) {
                        hp1 += card1.getDamage();
                        System.out.println(player1.getNickname() + "'s HP: \u001B[32m" + hp1 + "\u001B[0m");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                    if (card2.getName().equals("\u001B[33mHeal\u001B[0m")) {
                        hp2 += card2.getDamage();
                        System.out.println(player2.getNickname() + "'s HP: \u001B[32m" + hp2 + "\u001B[0m");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
                }
            }

            if (hp1 <= 0) {
                hp1 = 0;
                System.out
                        .println(player1.getNickname() + "'s HP: \u001B[32m" + hp1 + "\u001B[0m \t"
                                + player2.getNickname()
                                + "'s HP: \u001B[32m" + hp2 + "\u001B[0m");
                System.out.println("\u001B[33m" + player2.getNickname() + " wins!\u001B[0m");
                result[0] = 2;
                result[1] = hp1;
                result[2] = hp2;
                return result;
            }
            if (hp2 <= 0) {
                hp2 = 0;
                System.out
                        .println(player1.getNickname() + "'s HP: \u001B[32m" + hp1 + "\u001B[0m \t"
                                + player2.getNickname()
                                + "'s HP: \u001B[32m" + hp2 + "\u001B[0m");
                System.out.println(player1.getNickname() + " wins!");
                result[0] = 1;
                result[1] = hp1;
                result[2] = hp2;
                return result;
            }
        }
        System.out
                .println(player1.getNickname() + "'s HP: \u001B[32m" + hp1 + "\u001B[0m \t" + player2.getNickname()
                        + "'s HP: \u001B[32m" + hp2 + "\u001B[0m");

        result[0] = 0;
        result[1] = hp1;
        result[2] = hp2;
        return result;
    }

    public Prize winPrize(User winner, User loser, int bet) {
        if (winner.getLevel() < loser.getLevel()) {
            return new Prize(40 * (loser.getLevel() - winner.getLevel()), 2 * bet);
        } else if (winner.getLevel() > loser.getLevel()) {
            return new Prize((int) (40 / (winner.getLevel() - loser.getLevel())), 2 * bet);
        } else {
            return new Prize(30, 2 * bet);
        }
    }

    public Prize losePrize(User winner, User loser, int bet) {
        if (winner.getLevel() < loser.getLevel()) {
            return new Prize(0, 0);
        } else if (winner.getLevel() > loser.getLevel()) {
            return new Prize(10 * (winner.getLevel() - loser.getLevel()), 0);
        } else {
            return new Prize(5 * (winner.getLevel() - loser.getLevel()), 0);
        }
    }

    public void holeChanger(ArrayList<Card> board, ArrayList<Card> board2) {
        boolean flag1 = false;
        boolean flag2 = false;
        int index;
        Random rand = new Random();

        for (int i = 0; i < 21; i++) {
            if (board.get(i) != null && board.get(i).getName().equals("Hole") && !flag1) {
                board.set(i, null);
                while (true) {
                    index = rand.nextInt(21);
                    if (board.get(index) == null) {
                        board.set(index, new Card("Hole", 0, 1, 0, 0, 0, 0));
                        flag1 = true;
                        break;
                    }
                }
            }
            if (board2.get(i) != null && board2.get(i).getName().equals("Hole") && !flag2) {
                board2.set(i, null);
                while (true) {
                    index = rand.nextInt(21);
                    if (board2.get(index) == null) {
                        board2.set(index, new Card("Hole", 0, 1, 0, 0, 0, 0));
                        flag2 = true;
                        break;
                    }
                }
            }
            if (flag1 && flag2) {
                break;
            }
        }
    }

    public void repair(ArrayList<Card> board) {
        for (int i = 0; i < 21; i++) {
            if (board.get(i) != null && board.get(i).getName().equals("Hole")) {
                board.set(i, null);
            }
        }
    }

    public void cardSwapper(Scanner scanner, ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int index1, index2;
        Card temp;

        showHand(hand1);
        System.out.println("Please select the index of the card you want to swap:");
        index1 = Integer.parseInt(scanner.nextLine());

        showHand(hand2);
        System.out.println("Please select the index of the card you want to swap with:");
        index2 = Integer.parseInt(scanner.nextLine());

        index1--;
        index2--;

        temp = new Card(hand1.get(index1).getName(), hand1.get(index1).getPower(),
                hand1.get(index1).getDuration(), hand1.get(index1).getDamage(), hand1.get(index1).getUpgradeLevel(),
                hand1.get(index1).getUpgradeCost(), hand1.get(index1).getCharacter());
        hand1.set(index1, hand2.get(index2));
        hand2.set(index2, temp);
    }
}