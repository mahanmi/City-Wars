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
    public void run(Scanner scanner, User player1, User player2) throws Exception {
        int round = 4, position;
        User firstPlayer;
        User secondPlayer;
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
        firstPlayerBoard.add(rand.nextInt(firstPlayerBoard.size() - 1), holeCard);
        ArrayList<Card> secondPlayerBoard = new ArrayList<>(21);
        secondPlayerBoard.add(rand.nextInt(secondPlayerBoard.size() - 1), holeCard);

        ArrayList<Card> firstPlayerHand = new ArrayList<>(5);
        firstPlayerHand = playerHand(firstPlayer);
        ArrayList<Card> secondPlayerHand = new ArrayList<>(5);
        secondPlayerHand = playerHand(secondPlayer);

        while (round > 0) {
            System.out.println("Round " + (5 - round));
            System.out.println(firstPlayer.getNickname() + "'s turn");
            System.out.println("Please select a card to play:");
            controller.showHand(firstPlayerHand);
            int cardIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Please select the position you want to play this card in:");
            position = scanner.nextInt();
            controller.placeCard(firstPlayer, firstPlayerBoard, secondPlayerBoard, firstPlayerHand.get(cardIndex - 1), position);
            firstPlayerHand.remove(cardIndex - 1);
            firstPlayerHand.set(cardIndex - 1, firstPlayer.cards.get(rand.nextInt(firstPlayer.cards.size())));

            System.out.println(secondPlayer.getNickname() + "'s turn");
            System.out.println("Please select a card to play:");
            controller.showHand(secondPlayerHand);
            cardIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Please select the position you want to play this card in:");
            position = scanner.nextInt();
            controller.placeCard(secondPlayer, secondPlayerBoard, firstPlayerBoard, secondPlayerHand.get(cardIndex - 1), position);
            secondPlayerHand.remove(cardIndex - 1);
            secondPlayerHand.set(cardIndex - 1, secondPlayer.cards.get(rand.nextInt(secondPlayer.cards.size())));

            round--;
        }

        User winner = firstPlayer;
        Prize prize = new Prize(20 * Math.abs(firstPlayer.getLevel() - secondPlayer.getLevel()), 2 * bet);
        Game game = new Game(1, firstPlayer, secondPlayer, prize, new Timestamp(System.currentTimeMillis()), Main.crud.getUserId(winner.getUsername()));
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