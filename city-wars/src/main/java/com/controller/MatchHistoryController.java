package com.controller;

import java.util.ArrayList;

import com.Main;
import com.model.User;
import com.model.game.Game;
import com.model.game.Sort;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

public class MatchHistoryController {

  private final int PAGE_SIZE = 5;

  public String userMatchHistory(User user, int id, int page, Sort sort, boolean ascending) {
    AsciiTable table = new AsciiTable();
    table.addRule();
    table.addRow("Match Date", "Opponent", "Result", "Prize");
    ArrayList<Game> userGames = sort.getSortedGames(Main.crud.getUserGamesList(user));
    if (!ascending) {
      ArrayList<Game> reversed = new ArrayList<>();
      for (int i = userGames.size() - 1; i >= 0; i--) {
        reversed.add(userGames.get(i));
      }
      userGames = reversed;
    }
    Game game;
    for (int i = 0; i < PAGE_SIZE && i < userGames.size(); i++) {
      game = userGames.get(i + (page - 1) * PAGE_SIZE);
      table.addRule();
      String opponent = game.getPlayer1().getUsername().equals(user.getUsername()) ? game.getPlayer2().getUsername()
          : game.getPlayer1().getUsername();
      String result = game.getWinner() == id ? "Won" : "Lost";
      table.addRow(game.getMatchDate(), opponent, result, game.getPrize().toString());
    }
    table.addRule();
    table.setTextAlignment(TextAlignment.CENTER);
    return table.render() + "\n" + pageFooter(page, (int) Math.ceil((double) userGames.size() / PAGE_SIZE));
  }

  public String pageFooter(int currentPage, int pagesCount) {
    AsciiTable pages = new AsciiTable();
    pages.addRule();
    String footer = "";
    for (int i = 1; i <= pagesCount; i++) {
      if (i == currentPage) {
        footer += " [" + i + "] ";
      } else {
        footer += " " + i + " ";
      }
    }
    pages.addRow(footer);
    pages.addRule();
    pages.setTextAlignment(TextAlignment.CENTER);
    return pages.render();
  }

}
