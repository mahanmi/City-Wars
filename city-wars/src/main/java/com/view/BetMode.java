package com.view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.model.User;
import com.model.Card;
import com.model.game.Game;
import com.model.game.Prize;
import com.controller.BetModeController;
import com.Main;

public class BetMode {
    public static void main(String[] args) {
        User player1 = Main.crud.getUser(1);
        User player2 = Main.crud.getUser(2);
        Scanner scanner = new Scanner(System.in);
        BetMode betMode = new BetMode();
        betMode.run(scanner, player1, player2);
    }

    public void run(Scanner scanner, User player1, User player2) {
        int round = 4, hp1 = 200, hp2 = 200, cardIndex;
        User firstPlayer;
        User secondPlayer;
        User winner;
        User loser;
        Card holeCard = new Card("Hole", 0, 0, 0, 0, 0, 0);

        BetModeController controller = new BetModeController();
        Random rand = new Random();

        firstPlayer = controller.firstPlayer(player1, player2);
        if (firstPlayer.equals(player1)) {
            secondPlayer = player2;
        } else {
            secondPlayer = player1;
        }

        controller.chooseCharacter(scanner, firstPlayer);
        controller.chooseCharacter(scanner, secondPlayer);

        int bet = controller.welcome(scanner, firstPlayer, secondPlayer);

        ArrayList<Card> firstPlayerBoard = new ArrayList<>(21);
        for (int i = 0; i < 21; i++) {
            firstPlayerBoard.add(null);
        }
        firstPlayerBoard.add(rand.nextInt(21), holeCard);
        ArrayList<Card> secondPlayerBoard = new ArrayList<>(21);
        for (int i = 0; i < 21; i++) {
            secondPlayerBoard.add(null);
        }
        secondPlayerBoard.add(rand.nextInt(21), holeCard);

        ArrayList<Card> firstPlayerHand = new ArrayList<>(5);
        firstPlayerHand = playerHand(firstPlayer);
        ArrayList<Card> secondPlayerHand = new ArrayList<>(5);
        secondPlayerHand = playerHand(secondPlayer);

        while (round > 0) {
            System.out.println("Round " + (5 - round) + " of " + 4);
            System.out.println(firstPlayer.getNickname() + "'s turn");
            controller.showHand(firstPlayerHand);
            System.out.println("Please select a card to play:");
            while (true) {
                try {
                    cardIndex = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
                    continue;
                }
            }
            controller.placeCard(scanner, firstPlayer, firstPlayerBoard, firstPlayerHand.get(cardIndex - 1));
            firstPlayerHand.set(cardIndex - 1, firstPlayer.cards.get(rand.nextInt(5)));
            System.out.println(secondPlayer.getNickname() + "'s turn");
            controller.showHand(secondPlayerHand);
            System.out.println("Please select a card to play:");
            while (true) {
                try {
                    cardIndex = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number");
                    continue;
                }
            }
            controller.placeCard(scanner, secondPlayer, secondPlayerBoard, secondPlayerHand.get(cardIndex - 1));
            secondPlayerHand.set(cardIndex - 1, secondPlayer.cards.get(rand.nextInt(5)));

            round--;
        }

        winner = controller.timeline(firstPlayerBoard, secondPlayerBoard, firstPlayer, secondPlayer, hp1, hp2);
        if (winner == null) {
            while (round > 0) {
                System.out.println("Round " + (5 - round) + " of " + 4);
                System.out.println(firstPlayer.getNickname() + "'s turn");
                controller.showHand(firstPlayerHand);
                System.out.println("Please select a card to play:");
                Main.input = scanner.nextLine();
                cardIndex = Integer.parseInt(Main.input);
                controller.placeCard(scanner, firstPlayer, firstPlayerBoard, firstPlayerHand.get(cardIndex - 1));
                firstPlayerHand.set(cardIndex - 1, firstPlayer.cards.get(rand.nextInt(5)));
                System.out.println(secondPlayer.getNickname() + "'s turn");
                controller.showHand(secondPlayerHand);
                System.out.println("Please select a card to play:");
                Main.input = scanner.nextLine();
                while (Main.input.equals("")) {
                    System.out.println("card bezan koskesh");
                    Main.input = scanner.nextLine();
                }
                cardIndex = Integer.parseInt(Main.input);
                controller.placeCard(scanner, secondPlayer, secondPlayerBoard, secondPlayerHand.get(cardIndex - 1));
                secondPlayerHand.set(cardIndex - 1, secondPlayer.cards.get(rand.nextInt(5)));

                round--;
            }

            winner = controller.timeline(firstPlayerBoard, secondPlayerBoard, firstPlayer, secondPlayer, hp1, hp2);
        }

        if (winner.equals(firstPlayer)) {
            loser = secondPlayer;
        } else {
            loser = firstPlayer;
        }

        Prize winnerPrize = controller.winPrize(winner, loser, bet);
        winner.setBalance(winner.getBalance() + winnerPrize.balance);
        Prize loserPrize = controller.losePrize(winner, loser, bet);
        loser.setBalance(loser.getBalance() + loserPrize.balance);

        Game game = new Game(1, firstPlayer, secondPlayer, winnerPrize, new Timestamp(System.currentTimeMillis()),
                Main.crud.getUserId(winner.getUsername()));
        Main.crud.addGame(game);
    }

    private ArrayList<Card> playerHand(User player) {
        ArrayList<Card> allCards = player.cards;
        ArrayList<Card> playerHand = new ArrayList<Card>(5);
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = rand.nextInt(allCards.size());
            playerHand.add(allCards.get(randomIndex));
        }
        return playerHand;
    }
}