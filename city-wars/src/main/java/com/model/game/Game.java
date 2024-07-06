package com.model.game;

import java.util.ArrayList;
import java.sql.Date;

import com.model.*;

public class Game {
  private int gameMode;// 0: 1v1, 1: bet
  private User player1;
  private User player2;
  private Date date;
  private int winner;
  private Prize prize;
  private ArrayList<Card> player1Board;
  private ArrayList<Card> player2Board;

  public Game(int gameMode, User player1, User player2, Prize prize) {
    this.gameMode = gameMode;
    this.player1 = player1;
    this.player2 = player2;
    this.date = new Date(System.currentTimeMillis());
    this.winner = -1;
    this.prize = prize;
    this.player1Board = new ArrayList<>(20);
    this.player2Board = new ArrayList<>(20);
  }

  public Game(int gameMode, User player1, User player2, Prize prize, Date date, int winner) {
    this.gameMode = gameMode;
    this.player1 = player1;
    this.player2 = player2;
    this.date = date;
    this.winner = winner;
    this.prize = prize;
  }

  public int getGameMode() {
    return gameMode;
  }

  public User getPlayer1() {
    return player1;
  }

  public User getPlayer2() {
    return player2;
  }

  public Date getDate() {
    return date;
  }

  public int getWinner() {
    return winner;
  }

  public Prize getPrize() {
    return prize;
  }

  public void setWinner(int winner) {
    this.winner = winner;
  }

  public void placeCard(User player, Card card, int position) throws Exception {
    if (player == player1) {
      for (int i = position; i < card.getDuration(); i++) {
        if (player1Board.get(i) != null) {
          throw new Exception("There is already a card in that position");
        }
        player1Board.set(i, card);
      }
    } else {
      for (int i = position; i < card.getDuration(); i++) {
        if (player2Board.get(i) != null) {
          throw new Exception("There is already a card in that position");
        }
        player2Board.set(i, card);
      }
    }
  }

}
