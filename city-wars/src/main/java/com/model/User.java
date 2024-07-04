package com.model;

import java.util.ArrayList;

import com.Main;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private ArrayList<Card> cards = new ArrayList<>();
    private int securityQuestionID;
    private String securityQuestionAnswer;
    private int balance;

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
            System.out.println(card.getName() + " (Level " + card.getLevel() + ")");
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
}