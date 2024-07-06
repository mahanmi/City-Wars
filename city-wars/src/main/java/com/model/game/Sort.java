package com.model.game;

import java.util.ArrayList;

public enum Sort {

  TIME(),
  OPPONENT(),
  RESULT(),
  PRIZE();

  public ArrayList<Game> getSortedGames(ArrayList<Game> userGames) {
    if (this == TIME) {
      return sortByTime(userGames);
    } else if (this == OPPONENT) {
      return sortByOpponent(userGames);
    } else if (this == RESULT) {
      return sortByResult(userGames);
    } else if (this == PRIZE) {
      return sortByPrize(userGames);
    }
    return userGames;
  }

  private ArrayList<Game> sortByTime(ArrayList<Game> games) {
    games.sort((g1, g2) -> g1.getDate().compareTo(g2.getDate()));
    return games;
  }

  private ArrayList<Game> sortByOpponent(ArrayList<Game> games) {
    games.sort((g1, g2) -> {
      String opponent1 = g1.getPlayer1().getUsername().equals(g1.getPlayer2().getUsername())
          ? g1.getPlayer2().getUsername()
          : g1.getPlayer1().getUsername();
      String opponent2 = g2.getPlayer1().getUsername().equals(g2.getPlayer2().getUsername())
          ? g2.getPlayer2().getUsername()
          : g2.getPlayer1().getUsername();
      return opponent1.compareTo(opponent2);
    });
    return games;
  }

  private ArrayList<Game> sortByResult(ArrayList<Game> games) {
    games.sort((g1, g2) -> g1.getWinner() - g2.getWinner());
    return games;
  }

  private ArrayList<Game> sortByPrize(ArrayList<Game> games) {
    games.sort((g1, g2) -> g1.getPrize().compareTo(g2.getPrize()));
    return games;
  }

}
