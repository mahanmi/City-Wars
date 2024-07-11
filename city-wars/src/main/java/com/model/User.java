package com.model;

import java.util.ArrayList;

import de.vandermeer.asciitable.AsciiTable;
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

    public String getSecurityQuestion() {
        switch (securityQuestionID) {
            case 1:
                return "What is your father's name ?";

            case 2:
                return "What is your favorite color ?";

            case 3:
                return "What was the name of your first pet?";

            default:
                return null;
        }
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

            at.addRow(name, String.valueOf(level), String.valueOf(upgradeCost), String.valueOf(power),
                    String.valueOf(duration), String.valueOf(damage));
            at.addRule();
        }

        String rend = at.render();
        System.out.println(rend);
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

    public ArrayList<Card> notOwnedCards() {
        ArrayList<Card> allCards = new ArrayList<>(Main.crud.getAllCards());
        ArrayList<Card> notOwnedCards = new ArrayList<>();

        for (Card card : allCards) {
            if (!cards.contains(card)) {
                notOwnedCards.add(card);
            }
        }

        return notOwnedCards;
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
                double price = 5 * card.getUpgradeCost();
                int power = card.getPower();
                int duration = card.getDuration();
                int damage = card.getDamage();

                at.addRow(name, String.format("%.2f", price), String.valueOf(power), String.valueOf(duration),
                        String.valueOf(damage));
                at.addRule();
            }
        }

        String rend = at.render();
        System.out.println(rend);
        System.out.println("Your balance: " + this.balance + "$");
    }

    public String upgradeCard(Card card) {
        for (Card i : cards) {
            if (i.equals(card)) {
                if (balance < card.getUpgradeCost()) {
                    return "You don't have enough money to upgrade this card.";
                }
                balance -= card.getUpgradeCost();
                i.setLevel(i.getLevel() + 1);
                Main.crud.updateUser(this);
                return "Card upgraded successfully.";
            }
        }
        return "You don't own this card.";
    }

    public String buyCard(Card card) {
        if (balance < 3 * card.getUpgradeCost()) {
            return "You don't have enough money to buy this card.";
        }
        balance -= 3 * card.getUpgradeCost();
        cards.add(card);
        Main.crud.updateUser(this);
        return "Card bought successfully.";
    }

    public void addXp(int xp) {
        this.xp += xp;
        if (xp >= (level + 1) * 100) {
            level++;
            this.xp -= (level + 1) * 100;
        }
        Main.crud.updateUser(this);
    }
}