package com.controller;

import java.util.Scanner;
import java.util.ArrayList;
import com.model.User;
import com.model.game.Prize;
import de.vandermeer.asciitable.AsciiTable;
import com.Main;
import com.model.Card;
import com.view.FriendlyMode;


public class FriendlyModeController {
    public void welcome(User player1, User player2) {
        System.out.println("Welcome to the Bet Mode!");
        System.out.println("Player 1: " + player1.getNickname());
        System.out.println("Player 2: " + player2.getNickname());
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

    public void placeCard(Scanner scanner, User player, User player2, ArrayList<Card> board, ArrayList<Card> board2, Card card) {
        FriendlyMode friendlyMode = new FriendlyMode();

        outerloop: while (true) {
            System.out.println("Please select the position you want to play this card in:");
            friendlyMode.showBoard(board, board2, player, player2);
            int position;
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
            return;
        }
    }

    public void chooseCharacter(Scanner scanner, User player) {
        System.out.println(player.getNickname() + " choose your character:");
        System.out.println("1. Warrior    2. Mage    3. Archer    4. Rogue");
        int character = Integer.parseInt(scanner.nextLine());
        player.setCharacter(character);
    }

    public int[] timeline(ArrayList<Card> board1, ArrayList<Card> board2, User player1, User player2, int hp1, int hp2) {
        int D1 = 0;
        int D2 = 0;

        int[] result = new int[3];
    
        for (int i = 0; i < board1.size(); i++) {
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
                } else {
                    hp2 -= D1;
                }
            } else if (card1 == null && card2 != null) {
                if (card2.getCharacter() == player2.getCharacter()) {
                    hp1 -= D2 + 2;
                } else {
                    hp1 -= D2;
                }
            } else if (card1 != null && card2 != null) {
                if (card1.getPower() > card2.getPower()) {
                    if (card1.getCharacter() == player1.getCharacter()) {
                        hp2 -= D1 + 2;
                    } else {
                        hp2 -= D1;
                    }
                } else if (card1.getPower() < card2.getPower()) {
                    if (card2.getCharacter() == player2.getCharacter()) {
                        hp1 -= D2 + 2;
                    } else {
                        hp1 -= D2;
                    }
                }
            }
    
            System.out.println(player1.getNickname() + "'s HP: " + hp1 + "\t" + player2.getNickname() + "'s HP: " + hp2);
           
            if (hp1 <= 0) {
                System.out.println("\u001B[33m" + player2.getNickname() + " wins!\u001B[0m");
                result[0] = 2;
                result[1] = hp1;
                result[2] = hp2;
                return result;
            }
            if (hp2 <= 0) {
                System.out.println(player1.getNickname() + " wins!");
                result[0] = 1;
                result[1] = hp1;
                result[2] = hp2;
                return result;
            }
    
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
       
        result[0] = 0;
        result[1] = hp1;
        result[2] = hp2;
        return result;
    }
    

    public Prize winPrize(User winner, User loser) {
        if (winner.getLevel() < loser.getLevel()) {
            return new Prize(40 * (loser.getLevel() - winner.getLevel() + 1), 0);
        } else if (winner.getLevel() > loser.getLevel()) {
            return new Prize((int) (40 / (winner.getLevel() - loser.getLevel() + 1)), 0);
        } else {
            return new Prize(30, 0);
        }
    }

    public Prize losePrize(User winner, User loser) {
        if (winner.getLevel() < loser.getLevel()) {
            return new Prize(0, 0);
        } else if (winner.getLevel() > loser.getLevel()) {
            return new Prize(10 * (winner.getLevel() - loser.getLevel()), 0);
        } else {
            return new Prize(5 , 0);
        }
    }
}

