package com.model;

import java.util.ArrayList;
import java.util.List;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.TA_GridThemes;

import com.Main;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private int securityQuestionID;
    private String securityQuestionAnswer;
    private int level = 0;
    private int xp = 0;
    private int balance;
    private int character = 0;

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

    public int getCharacter() {
        return character;
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

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setBalance(int balance) {
        this.balance = balance;
        Main.crud.updateUser(this);
    }


    public void showCards() {
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Name", "Level", "Upgrade Cost", "Power", "Duration", "Damage");
        at.addRule();

        for (Card card : cards) {
            String name = card.getName();
            int level = card.getLevel();
            int upgradeCost = (int) (card.getUpgradeCost() * Math.pow(1.25, card.getLevel() - 1));
            int power = card.getPower();
            int duration = card.getDuration();
            int damage = card.getDamage();

            if (name.startsWith("\u001B[33m")) {
                at.addRow(name.replaceAll("\\u001B\\[[;\\d]*m", ""), "-", "-", "-", "-", "-");
                at.addRule();
            }
            else {
                at.addRow(name, level, String.format("%.2f", upgradeCost), power, duration, damage);
                at.addRule();
            }
        }

        String rend = at.render();
        System.out.println(rend);
        System.out.println("You have " + balance + " coins.");
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
        ArrayList<Card> allCards = new ArrayList<>(Main.crud.getAllCards());
        AsciiTable at = new AsciiTable();
        
        at.addRule();
        at.addRow("Name", "Price", "Power", "Duration", "Damage");
        at.addRule();

        for (Card card : allCards) {
            if (!cards.contains(card)) {
                String name = card.getName();
                double price = 3 * card.getUpgradeCost();
                int power = card.getPower();
                int duration = card.getDuration();
                int damage = card.getDamage();

                if (name.startsWith("\u001B[33m")) {
                    at.addRow(name.replaceAll("\\u001B\\[[;\\d]*m", ""), price, "-", "-", "-");
                    at.addRule();
                }
                else {
                    at.addRow(name, price, power, duration, damage);
                    at.addRule();
                }
            }
        }
        String renderedTable = at.render();
        System.out.println(renderedTable);
        System.out.println("You have " + balance + " coins.");
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
        if (xp >= (level + 1) * 100) {
            level++;
            this.xp -= (level + 1) * 100;
            System.out.println("Congratulations! " + this.nickname + " have reached level " + level + "!");
        }
        Main.crud.updateUser(this);
    }
}