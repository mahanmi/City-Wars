package com.view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.model.User;
import com.model.Card;
import com.model.game.Game;
import com.model.game.Prize;
import com.controller.BetModeController;

public class BetMode {
    public void run(Scanner scanner, User player1, User player2, int bet){
        int round = 4, position;
        User firstPlayer;
        User secondPlayer;

        BetModeController controller = new BetModeController();

        bet = controller.welcome(scanner, player1, player2);
        firstPlayer = controller.firstPlayer(player1, player2);
        if (firstPlayer.equals(player1)) {
            secondPlayer = player2;
        } else {
            secondPlayer = player1;
        }

        ArrayList<Card> firstPlayerBoard = new ArrayList<>(20);
        ArrayList<Card> secondPlayerBoard = new ArrayList<>(20);

        ArrayList<Card> firstPlayerHand = new ArrayList<>(5);
        firstPlayerHand = playerHand(firstPlayer);
        ArrayList<Card> secondPlayerHand = new ArrayList<>(5);
        secondPlayerHand = playerHand(secondPlayer);

        Prize prize = new Prize(20 * Math.abs(firstPlayer.getLevel() - secondPlayer.getLevel()), 2 * bet);

        Game game = new Game(1, firstPlayer, secondPlayer, prize);

        while(round > 0){
            System.out.println("Round " + (5 - round));
            System.out.println(firstPlayer.getNickname() + "'s turn");
            System.out.println("Please select a card to play:");
            controller.showHand(firstPlayerHand);
            int cardIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Please select the position you want to play this card in:");
            position = scanner.nextInt();
            firstPlayerBoard.add(firstPlayerHand.get(cardIndex-1));
            firstPlayer.cards.remove(cardIndex);

            System.out.println(secondPlayer.getNickname() + "'s turn");
            System.out.println("Please select a card to play:");
            controller.showHand(secondPlayerHand);
            cardIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Please select the position you want to play this card in:");
            position = scanner.nextInt();
            secondPlayerBoard.add(secondPlayerHand.get(cardIndex-1));
            secondPlayer.cards.remove(cardIndex);

            round--;
        }
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