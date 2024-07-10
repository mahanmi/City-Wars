package com.view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
import com.model.User;
import com.model.Card;
import com.model.game.Game;
import com.model.game.Prize;
import com.controller.BetModeController;
import com.Main;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

public class BetMode {

    public void run(Scanner scanner, User player1, User player2) {
        int round = 4, cardIndex;
        int hp1 = 150;
        int hp2 = 150;
        int result[];
        int placeCard[];
        User firstPlayer;
        User secondPlayer;
        User winner = null;
        User loser;
        boolean hide = false;
        Card holeCard = new Card("Hole", 0, 1, 0, 0, 0, 0);

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

        while (winner == null) {
            while (round > 0) {
                System.out.println("Round " + (5 - round) + " of " + 4);
                System.out.println(firstPlayer.getNickname() + "'s turn");
                if (hide) {
                    Collections.shuffle(firstPlayerHand);
                } else {
                    controller.showHand(firstPlayerHand);
                }

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
                hide = false;
                placeCard = controller.placeCard(scanner, firstPlayer, secondPlayer, firstPlayerBoard,
                        secondPlayerBoard,
                        firstPlayerHand, secondPlayerHand, firstPlayerHand.get(cardIndex - 1), round, hide);
                round = placeCard[0];
                if (placeCard[1] == 1) {
                    hide = true;
                } else if (placeCard[1] == 0) {
                    hide = false;
                }
                firstPlayerHand.set(cardIndex - 1, firstPlayer.cards.get(rand.nextInt(5)));

                System.out.println(secondPlayer.getNickname() + "'s turn");
                if (hide) {
                    Collections.shuffle(secondPlayerHand);
                } else {
                    controller.showHand(secondPlayerHand);
                }

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
                hide = false;
                placeCard = controller.placeCard(scanner, secondPlayer, firstPlayer, secondPlayerBoard,
                        firstPlayerBoard,
                        secondPlayerHand, firstPlayerHand, secondPlayerHand.get(cardIndex - 1), round, hide);
                round = placeCard[0];
                if (placeCard[1] == 1) {
                    hide = false;
                } else if (placeCard[1] == 0) {
                    hide = true;
                }
                secondPlayerHand.set(cardIndex - 1, secondPlayer.cards.get(rand.nextInt(5)));

                round--;
            }

            result = controller.timeline(firstPlayerBoard, secondPlayerBoard, firstPlayer, secondPlayer, hp1, hp2);
            hp1 = result[1];
            hp2 = result[2];
            if (result[0] == 1) {
                winner = firstPlayer;
                loser = secondPlayer;
            } else if (result[0] == 2) {
                winner = secondPlayer;
                loser = firstPlayer;
            }

            round = 4;
            firstPlayerBoard = new ArrayList<>(21);
            for (int i = 0; i < 21; i++) {
                firstPlayerBoard.add(null);
            }
            firstPlayerBoard.add(rand.nextInt(21), holeCard);
            secondPlayerBoard = new ArrayList<>(21);
            for (int i = 0; i < 21; i++) {
                secondPlayerBoard.add(null);
            }

            secondPlayerBoard.add(rand.nextInt(21), holeCard);
            firstPlayerHand = new ArrayList<>(5);
            firstPlayerHand = playerHand(firstPlayer);
            secondPlayerHand = new ArrayList<>(5);
            secondPlayerHand = playerHand(secondPlayer);

        }

        if (winner.equals(firstPlayer)) {
            loser = secondPlayer;
        } else {
            loser = firstPlayer;
        }

        Prize winnerPrize = controller.winPrize(winner, loser, bet);
        winner.setBalance(winner.getBalance() + winnerPrize.balance);
        winner.addXp(winnerPrize.xp);
        Prize loserPrize = controller.losePrize(winner, loser, bet);
        loser.setBalance(loser.getBalance() + loserPrize.balance);
        loser.addXp(loserPrize.xp);

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

    public void showBoard(ArrayList<Card> player1Board, ArrayList<Card> player2Board, User player1, User player2) {

        AsciiTable at = new AsciiTable();
        at.getRenderer().setCWC(new CWC_LongestLine());
        at.addRule();

        String[] index = new String[21];
        for (int i = 0; i < 21; i++) {
            index[i] = String.valueOf("\u001B[34m" + (i + 1) + "\u001B[0m");
        }
        at.addRow((Object[]) index);
        at.addRule();

        String[] player1Row = new String[21];
        for (int col = 0; col < 21; col++) {
            Card card = player1Board.get(col);
            if (card != null) {
                player1Row[col] = card.boardCard(player1);
            } else {
                player1Row[col] = " ";
            }
        }
        at.addRow((Object[]) player1Row);
        at.addRule();

        String[] player2Row = new String[21];
        for (int col = 0; col < 21; col++) {
            Card card = player2Board.get(col);
            if (card != null) {
                player2Row[col] = card.boardCard(player2);
            } else {
                player2Row[col] = " ";
            }
        }
        at.addRow((Object[]) player2Row);
        at.addRule();

        String renderedTable = at.render();
        System.out.println(renderedTable);
    }
}