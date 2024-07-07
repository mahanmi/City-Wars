package com.model.game;

public class Prize implements Comparable<Prize> {
  public int xp;
  public int balance;

  public Prize(int xp, int balance) {
    this.xp = xp;
    this.balance = balance;
  }

  public String toString() {
    return "XP: " + xp + " Balance: " + balance;
  }

  public String dbString() {
    return xp + "," + balance;
  }

  @Override
  public int compareTo(Prize prize) {
    if (xp > prize.xp) {
      return 1;
    } else if (xp < prize.xp) {
      return -1;
    } else {
      if (balance > prize.balance) {
        return 1;
      } else if (balance < prize.balance) {
        return -1;
      } else {
        return 0;
      }
    }
  }

}
