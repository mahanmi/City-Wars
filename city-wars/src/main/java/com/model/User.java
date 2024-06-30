package com.model;

import java.util.ArrayList;

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
    static int loggedInUserId = -1;

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
        this.balance = 1000; // initial balance
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
            CRUD crud = new CRUD();
            cards.add(new Card(crud.getCardById(id), level));
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

    public void setUsername(String username) {
        this.username = username;
    }
}