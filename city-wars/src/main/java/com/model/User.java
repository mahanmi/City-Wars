package com.model;

import java.util.ArrayList;

import com.Main;
import com.CRUD;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    public ArrayList<Card> cards = new ArrayList<>();
    public String[] securityQuestions = new String[3];
    private int balance;
    public boolean firstTime = true;

    public User(String username, String password, String email, String nickname, String cardIDs, String Q1, String Q2,
            String Q3) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityQuestions[0] = Q1;
        this.securityQuestions[1] = Q2;
        this.securityQuestions[2] = Q3;
        addCards(cardIDs);
        this.balance = Main.INITIAL_BALANCE;
    }

    public User(String username, String password, String email, String nickname, String cardIDs, String Q1, String Q2,
            String Q3, int balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityQuestions[0] = Q1;
        this.securityQuestions[1] = Q2;
        this.securityQuestions[2] = Q3;
        addCards(cardIDs);
        this.balance = balance;
    }

    private void addCards(String cardIDs) {
        String[] cardIDsArray = cardIDs.split(",");
        for (String cardID : cardIDsArray) {
            String[] cardIDArray = cardID.split("\\.");
            int id = Integer.parseInt(cardIDArray[0]);
            int level = Integer.parseInt(cardIDArray[1]);
            cards.add(new Card(Main.crud.getCardById(id), level));
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

    public int getBalance() {
        return balance;
    }

    public String getCardIDs() {
        String cardIDs = "";
        for (Card card : cards) {
            cardIDs += card.getId() + "." + card.getLevel() + ",";
        }
        return cardIDs;
    }

    public String[] getSecurityQuestions() {
        return securityQuestions;
    }

    public void setUsername(String username, int id) {
        this.username = username;
        Main.crud.updateUser(this, id);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void showCards() {
        System.out.println("Your cards:");
        for (Card card : cards) {
            System.out.println(card.getName() + " (Level " + card.getLevel() + ")");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        Main.crud.updateUser(this);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
}