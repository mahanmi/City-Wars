package com;

import com.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CRUD {
    public static void main(String[] args) {
        CRUD crud = new CRUD();
        crud.createTables();
    }

    private void createTables() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT UNIQUE, password TEXT,email TEXT UNIQUE,nickname TEXT,cardIDs TEXT,QuestionID INT,Answer TEXT,balance INT)";
            connection.createStatement().execute(createTableQuery);
            System.out.println("Users Table created successfully");
            createTableQuery = "CREATE TABLE IF NOT EXISTS cards (id INTEGER PRIMARY KEY, name TEXT UNIQUE, power INT, duration INT, damage INT,upgradeLevel INT,upgradeCost INT)";
            connection.createStatement().execute(createTableQuery);
            System.out.println("Cards Table created successfully");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void addUser(User user) {
        try {
            String insertQuery = "INSERT INTO users (username, password, email, nickname, cardIDs, QuestionID, Answer, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNickname());
            statement.setString(5, user.getCardIDs());
            statement.setInt(6, user.getSecurityQuestionID());
            statement.setString(7, user.getSecurityQuestionAnswer());
            statement.setInt(8, user.getBalance());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            String deleteQuery = "DELETE FROM users WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            String updateQuery = "UPDATE users SET password = ?, email = ?, nickname = ?, cardIDs = ?, QuestionID=?, Answer=?, balance = ? WHERE username = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getNickname());
            statement.setString(4, user.getCardIDs());
            statement.setInt(5, user.getSecurityQuestionID());
            statement.setString(6, user.getSecurityQuestionAnswer());
            statement.setInt(7, user.getBalance());
            statement.setString(8, user.getUsername());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void updateUser(User user, int id) {
        try {
            String updateQuery = "UPDATE users SET username = ?, password = ?, email = ?, nickname = ?, cardIDs = ?, QuestionID=?, Answer=?, balance = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNickname());
            statement.setString(5, user.getCardIDs());
            statement.setInt(6, user.getSecurityQuestionID());
            statement.setString(7, user.getSecurityQuestionAnswer());
            statement.setInt(8, user.getBalance());
            statement.setInt(9, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public User getUser(int id) {
        try {
            String selectQuery = "SELECT * FROM users WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String nickname = resultSet.getString("nickname");
                String cardIDs = resultSet.getString("cardIDs");
                int securityQuestionID = resultSet.getInt("QuestionID");
                String securityQuestionAnswer = resultSet.getString("Answer");
                int balance = resultSet.getInt("balance");
                User user = new User(username, password, email, nickname, cardIDs, securityQuestionID,
                        securityQuestionAnswer, balance);
                connection.close();
                return user;
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting user by id: " + e.getMessage());
            return null;
        }
    }

    public User getUser(String username) {
        try {
            String selectQuery = "SELECT * FROM users WHERE username = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String nickname = resultSet.getString("nickname");
                String cardIDs = resultSet.getString("cardIDs");
                int securityQuestionID = resultSet.getInt("QuestionID");
                String securityQuestionAnswer = resultSet.getString("Answer");
                int balance = resultSet.getInt("balance");
                User user = new User(username, password, email, nickname, cardIDs, securityQuestionID,
                        securityQuestionAnswer, balance);
                connection.close();
                return user;
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting user by id: " + e.getMessage());
            return null;
        }
    }

    public int getUserId(String username) {
        try {
            String selectQuery = "SELECT id FROM users WHERE username = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int result = resultSet.getInt("id");
                connection.close();
                return result;
            } else {
                connection.close();
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error getting user id by username: " + e.getMessage());
            return -1;
        }
    }

    public void addCard(Card card) {
        try {
            String insertQuery = "INSERT INTO cards (name, power, duration, damage, upgradeLevel, upgradeCost) VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, card.getName());
            statement.setInt(2, card.getPower());
            statement.setInt(3, card.getDuration());
            statement.setInt(4, card.getDamage());
            statement.setInt(5, card.getUpgradeLevel());
            statement.setInt(6, card.getUpgradeCost());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding card: " + e.getMessage());
        }
    }

    public void updateCard(Card card, int id) {
        try {
            String updateQuery = "UPDATE cards SET name = ?, power = ?, duration = ?, damage = ?, upgradeLevel = ?, upgradeCost = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, card.getName());
            statement.setInt(2, card.getPower());
            statement.setInt(3, card.getDuration());
            statement.setInt(4, card.getDamage());
            statement.setInt(5, card.getUpgradeLevel());
            statement.setInt(6, card.getUpgradeCost());
            statement.setInt(7, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updating card: " + e.getMessage());
        }
    }

    public Card getCard(int id) {
        try {
            String selectQuery = "SELECT * FROM cards WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int power = resultSet.getInt("power");
                int duration = resultSet.getInt("duration");
                int damage = resultSet.getInt("damage");
                int upgradeLevel = resultSet.getInt("upgradeLevel");
                int upgradeCost = resultSet.getInt("upgradeCost");
                Card card = new Card(id, name, power, duration, damage, upgradeLevel, upgradeCost);
                connection.close();
                return card;
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting card by id: " + e.getMessage());
            return null;
        }
    }

    public void deleteCard(int id) {
        try {
            String deleteQuery = "DELETE FROM cards WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleting card: " + e.getMessage());
        }
    }

    public ArrayList<Card> getAllCards() {
        try {
            String selectQuery = "SELECT * FROM cards";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Card> cards = new ArrayList<Card>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int power = resultSet.getInt("power");
                int duration = resultSet.getInt("duration");
                int damage = resultSet.getInt("damage");
                int upgradeLevel = resultSet.getInt("upgradeLevel");
                int upgradeCost = resultSet.getInt("upgradeCost");
                Card card = new Card(id, name, power, duration, damage, upgradeLevel, upgradeCost);
                cards.add(card);
            }
            connection.close();
            return cards;
        } catch (SQLException e) {
            System.out.println("Error getting all cards: " + e.getMessage());
            return null;
        }
    }

    public void showCard(Card card) {
        System.out.println("Name: " + card.getName());
        System.out.println("\t Power: " + card.getPower());
        System.out.println("\t Duration: " + card.getDuration());
        System.out.println("\t Damage: " + card.getDamage());
        System.out.println("\t Upgrade Level: " + card.getUpgradeLevel());
        System.out.println("\t Upgrade Cost: " + card.getUpgradeCost());
    }

    public void showAllUsers() {
        try {
            String selectQuery = "SELECT * FROM users";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String nickname = resultSet.getString("nickname");
                String cardIDs = resultSet.getString("cardIDs");
                int securityQuestionID = resultSet.getInt("QuestionID");
                String securityQuestionAnswer = resultSet.getString("Answer");
                int balance = resultSet.getInt("balance");
                User user = new User(username, password, email, nickname, cardIDs, securityQuestionID,
                        securityQuestionAnswer, balance);
                System.out.println("ID: " + id);
                System.out.println("\t Username: " + user.getUsername());
                System.out.println("\t Password: " + user.getPassword());
                System.out.println("\t Email: " + user.getEmail());
                System.out.println("\t Nickname: " + user.getNickname());
                System.out.println("\t Card IDs: " + user.getCardIDs());
                System.out
                        .println("\t Security Question" + securityQuestionID + ": " + user.getSecurityQuestionAnswer());
                System.out.println("\t Balance: " + user.getBalance());
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error showing all users: " + e.getMessage());
        }
    }

    public void addInitialCards() {
        // DAMAGE/HEAL
        Card card1 = new Card("Supporting Cover", 37, 2, 40, 2, 150);
        Card card2 = new Card("Flash Pellets", 29, 4, 28, 1, 100);
        Card card3 = new Card("Swift Justice", 36, 2, 52, 2, 200);
        Card card4 = new Card("Rapid Recoil", 25, 4, 40, 1, 100);
        Card card5 = new Card("Bullet Flurry", 31, 2, 32, 2, 150);
        Card card6 = new Card("Run & Gun", 28, 1, 28, 1, 80);
        Card card7 = new Card("Leaping Fury", 30, 3, 21, 2, 70);
        Card card8 = new Card("Bullet Blast", 35, 5, 55, 3, 250);
        Card card9 = new Card("Bullet Assault", 30, 5, 25, 1, 150);
        Card card10 = new Card("Stealth Support", 35, 4, 20, 2, 100);
        Card card11 = new Card("Damage Breaker", 0 /* Infinity */ , 2, 40, 4, 350);
        Card card12 = new Card("IDN Burst", 30, 3, 21, 2, 100);
        Card card13 = new Card("Multi-Fire", 30, 2, 26, 2, 150);
        Card card14 = new Card("Shattered Augmentation", 25, 5, 40, 1, 150);
        Card card15 = new Card("Biker Support", 30, 3, 21, 2, 150);
        addCard(card1);
        addCard(card2);
        addCard(card3);
        addCard(card4);
        addCard(card5);
        addCard(card6);
        addCard(card7);
        addCard(card8);
        addCard(card9);
        addCard(card10);
        addCard(card11);
        addCard(card12);
        addCard(card13);
        addCard(card14);
        addCard(card15);

        // SPELLS
        Card card16 = new Card("Rad Darts", 33, 3, 33, 4, 300); // This spell makes the opposing space poisonous, and if
                                                                // the opponent places a card in that space, they will
                                                                // take damage at the end of the game
        Card card17 = new Card("Thermo Crisi", 33, 2, 36, 3, 200); // This spell randomly sets one of the opponent's
                                                                   // spaces on fire, causing the damage of the card
                                                                   // placed in that space to be halved
        Card card18 = new Card("Ice Land", 32, 2, 34, 5, 350); // This spell freezes two opponent's spaces, preventing
                                                               // them from placing a card in that space
        Card card19 = new Card("Acidic Cleanser", 29, 1, 33, 3, 180); // This spell makes the opposing space acidic,
                                                                      // causing the opponent to take damage when they
                                                                      // reach this space at the end of the game
        Card card20 = new Card("Thermo Extinguisher", 33, 1, 47, 4, 350); // This spell neutralizes the effect of Ice
                                                                          // Land on all our spaces
        addCard(card16);
        addCard(card17);
        addCard(card18);
        addCard(card19);
        addCard(card20);
    }
}