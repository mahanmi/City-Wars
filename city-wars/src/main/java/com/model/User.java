package com.model;

import java.util.ArrayList;

import com.Main;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private int securityQuestionID;
    private String securityQuestionAnswer;
    private int level = 1;
    private int xp = 0;
    private int balance;

    public ArrayList<Card> cards = new ArrayList<>();

    public User(String username, String password, String email, String nickname, String cardIDs, int securityQuestionID,
            String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityQuestionID = securityQuestionID;
        this.securityQuestionAnswer = securityQuestionAnswer;
        addCards(cardIDs);
        this.balance = Main.INITIAL_BALANCE;
    }

    public User(String username, String password, String email, String nickname, String cardIDs, int securityQuestionID,
            String securityQuestionAnswer, int balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityQuestionID = securityQuestionID;
        this.securityQuestionAnswer = securityQuestionAnswer;
        addCards(cardIDs);
        this.balance = balance;
    }

    public User(String username, int level, int xp, String password, String email, String nickname, String cardIDs,
            int securityQuestionID,
            String securityQuestionAnswer, int balance) {
        this.username = username;
        this.level = level;
        this.xp = xp;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityQuestionID = securityQuestionID;
        this.securityQuestionAnswer = securityQuestionAnswer;
        addCards(cardIDs);
        this.balance = balance;
    }

    private void addCards(String cardIDs) {
        if (cardIDs == null || cardIDs.equals("")) {
            return;
        }
        String[] cardIDsArray = cardIDs.split(",");
        for (String cardID : cardIDsArray) {
            String[] cardIDArray = cardID.split("\\.");
            int id = Integer.parseInt(cardIDArray[0]);
            int level = Integer.parseInt(cardIDArray[1]);
            cards.add(new Card(Main.crud.getCard(id), level));
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getHP() {
        return level * 100 + 150;
    }

    public int getBalance() {
        return balance;
    }

    public String getCardIDs() {
        String cardIDs = "";
        for (Card card : cards) {
            cardIDs += card.getId() + "." + card.getLevel() + ",";
        }
        if (cardIDs.length() > 0) {
            cardIDs = cardIDs.substring(0, cardIDs.length() - 1);
        }
        return cardIDs;
    }

    public int getSecurityQuestionID() {
        return securityQuestionID;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setUsername(String username, int id) {
        this.username = username;
        Main.crud.updateUser(this, id);
    }

    public void setPassword(String password) {
        this.password = password;
        Main.crud.updateUser(this);
    }

    public void setEmail(String email) {
        this.email = email;
        Main.crud.updateUser(this);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        Main.crud.updateUser(this);
    }

    public void setBalance(int balance) {
        this.balance = balance;
        Main.crud.updateUser(this);
    }

    public void showCards() {
        System.out.println("Your cards:");
        for (Card card : cards) {
            System.out.println(card.getName() + " (Level " + card.getLevel() + ") " + " Upgrade cost: "
                    + card.getUpgradeCost() * Math.pow(1.25, card.getLevel() - 1));
            System.out.println("\t Power: " + card.getPower());
            System.out.println("\t Duration: " + card.getDuration());
            System.out.println("\t Damage: " + card.getDamage());
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        Main.crud.updateUser(this);
    }

    public void addCards(ArrayList<Card> cards) {
        this.cards = cards;
        Main.crud.updateUser(this);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void showNotOwnedCards() {
        for (Card card : Main.crud.getAllCards()) {
            if (!cards.contains(card)) {
                System.out.println(card.getName() + " (Price: " + 5 * card.getUpgradeCost() + ")");
                System.out.println("\t Power: " + card.getPower());
                System.out.println("\t Duration: " + card.getDuration());
                System.out.println("\t Damage: " + card.getDamage());
            }
        }
        System.out.println("Your balance: " + this.balance + "$");
    }

    public void upgradeCard(Card card) {
        for (Card i : cards) {
            if (i.equals(card)) {
                i.setLevel(i.getLevel() + 1);
                Main.crud.updateUser(this);
                return;
            }
        }
    }

    public void addXp(int xp) {
        this.xp += xp;
        if (xp >= 100) {
            level++;
            this.xp -= 100;
        }
        Main.crud.updateUser(this);
    }
}