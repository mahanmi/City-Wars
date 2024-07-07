package com.model.game;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import com.model.*;

public class Game {
  private int gameMode;// 0: 1v1, 1: bet
  private User player1;
  private User player2;
  private Timestamp date;
  private int winner;
  private Prize prize;
  private ArrayList<Card> player1Board;
  private ArrayList<Card> player2Board;

  public Game(int gameMode, User player1, User player2,  int winner, ArrayList<Card> player1Board, ArrayList<Card> player2Board) {
    this.gameMode = gameMode;
    this.player1 = player1;
    this.player2 = player2;
    this.date = new Timestamp(System.currentTimeMillis());
    this.player1Board = player1Board;
    this.player2Board = player2Board;
    this.winner = winner;
  }

  public Game(int gameMode, User player1, User player2, Prize prize, Timestamp date, int winner) {
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

  public Timestamp getDate() {
    return date;
  }

  public int getWinner() {
    return winner;
  }

  public Prize getPrize() {
    return prize;
  }

  public String getMatchDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return date.toLocalDateTime().format(formatter);
  }

  public ArrayList<Card> getPlayer1Board() {
    return player1Board;
  }

  public ArrayList<Card> getPlayer2Board() {
    return player2Board;
  }

  public void setWinner(int winner) {
    this.winner = winner;
  }

}
