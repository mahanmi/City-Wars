package com;

import com.model.*;
import com.model.game.Game;
import com.model.game.Prize;

import java.sql.Timestamp;
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
        crud.addInitialCards();
    }

    private void createTables() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            // users table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT UNIQUE, level INT,xp INT,password TEXT,email TEXT UNIQUE,nickname TEXT,cardIDs TEXT,QuestionID INT,Answer TEXT,balance INT)";
            connection.createStatement().execute(createTableQuery);
            System.out.println("Users Table created successfully");
            // cards table
            createTableQuery = "CREATE TABLE IF NOT EXISTS cards (id INTEGER PRIMARY KEY, name TEXT UNIQUE,isSpell INT, power INT, duration INT, damage INT,upgradeLevel INT,upgradeCost INT,character INT)";
            connection.createStatement().execute(createTableQuery);
            System.out.println("Cards Table created successfully");
            // games table
            createTableQuery = "CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY, player1ID INT, player2ID INT, winner INT, date DATETIME , gameMode INT,prize TEXT)";
            connection.createStatement().execute(createTableQuery);
            System.out.println("Games Table created successfully");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void addUser(User user) {
        try {
            String insertQuery = "INSERT INTO users (username, level, xp, password, email, nickname, cardIDs, QuestionID, Answer, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getLevel());
            statement.setInt(3, user.getXp());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getNickname());
            statement.setString(7, user.getCardIDs());
            statement.setInt(8, user.getSecurityQuestionID());
            statement.setString(9, user.getSecurityQuestionAnswer());
            statement.setInt(10, user.getBalance());
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
            String updateQuery = "UPDATE users SET username = ?, level = ?, xp = ?, password = ?, email = ?, nickname = ?, cardIDs = ?, QuestionID=?, Answer=?, balance = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getLevel());
            statement.setInt(3, user.getXp());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getNickname());
            statement.setString(7, user.getCardIDs());
            statement.setInt(8, user.getSecurityQuestionID());
            statement.setString(9, user.getSecurityQuestionAnswer());
            statement.setInt(10, user.getBalance());
            statement.setInt(11, getUserId(user.getUsername()));
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void updateUser(User user, int id) {
        try {
            String updateQuery = "UPDATE users SET username = ?, level = ?, xp = ?, password = ?, email = ?, nickname = ?, cardIDs = ?, QuestionID=?, Answer=?, balance = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getLevel());
            statement.setInt(3, user.getXp());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getNickname());
            statement.setString(7, user.getCardIDs());
            statement.setInt(8, user.getSecurityQuestionID());
            statement.setString(9, user.getSecurityQuestionAnswer());
            statement.setInt(10, user.getBalance());
            statement.setInt(11, id);
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
                int level = resultSet.getInt("level");
                int xp = resultSet.getInt("xp");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String nickname = resultSet.getString("nickname");
                String cardIDs = resultSet.getString("cardIDs");
                int securityQuestionID = resultSet.getInt("QuestionID");
                String securityQuestionAnswer = resultSet.getString("Answer");
                int balance = resultSet.getInt("balance");
                User user = new User(username, level, xp, password, email, nickname, cardIDs, securityQuestionID,
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
                int level = resultSet.getInt("level");
                int xp = resultSet.getInt("xp");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String nickname = resultSet.getString("nickname");
                String cardIDs = resultSet.getString("cardIDs");
                int securityQuestionID = resultSet.getInt("QuestionID");
                String securityQuestionAnswer = resultSet.getString("Answer");
                int balance = resultSet.getInt("balance");
                User user = new User(username, level, xp, password, email, nickname, cardIDs, securityQuestionID,
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
            String insertQuery = "INSERT INTO cards (name, isSpell, power, duration, damage, upgradeLevel, upgradeCost, character) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, card.getName());
            statement.setInt(2, card.isSpell());
            statement.setInt(3, card.getPower());
            statement.setInt(4, card.getDuration());
            statement.setInt(5, card.getDamage());
            statement.setInt(6, card.getUpgradeLevel());
            statement.setInt(7, card.getUpgradeCost());
            statement.setInt(8, card.getCharacter());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding card: " + e.getMessage());
        }
    }

    public void updateCard(Card card) {
        try {
            String updateQuery = "UPDATE cards SET name = ?, isSpell = ?, power = ?, duration = ?, damage = ?, upgradeLevel = ?, upgradeCost = ? , character = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, card.getName());
            statement.setInt(2, card.isSpell());
            statement.setInt(3, card.getPower());
            statement.setInt(4, card.getDuration());
            statement.setInt(5, card.getDamage());
            statement.setInt(6, card.getUpgradeLevel());
            statement.setInt(7, card.getUpgradeCost());
            statement.setInt(8, card.getId());
            statement.setInt(9, card.getCharacter());
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
                if (resultSet.getInt("isSpell") == 1) {
                    // This is a spell
                    connection.close();
                    return null;
                } else {
                    String name = resultSet.getString("name");
                    int power = resultSet.getInt("power");
                    int duration = resultSet.getInt("duration");
                    int damage = resultSet.getInt("damage");
                    int upgradeLevel = resultSet.getInt("upgradeLevel");
                    int upgradeCost = resultSet.getInt("upgradeCost");
                    int character = resultSet.getInt("character");
                    Card card = new Card(id, name, power, duration, damage, upgradeLevel, upgradeCost, character);
                    connection.close();
                    return card;
                }
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting card by id: " + e.getMessage());
            return null;
        }
    }

    public Card getCard(String cardName) {
        try {
            String selectQuery = "SELECT * FROM cards WHERE name = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, cardName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("isSpell") == 1) {
                    // This is a spell
                    connection.close();
                    return null;
                } else {
                    int id = resultSet.getInt("id");
                    int power = resultSet.getInt("power");
                    int duration = resultSet.getInt("duration");
                    int damage = resultSet.getInt("damage");
                    int upgradeLevel = resultSet.getInt("upgradeLevel");
                    int upgradeCost = resultSet.getInt("upgradeCost");
                    int character = resultSet.getInt("character");
                    Card card = new Card(id, cardName, power, duration, damage, upgradeLevel, upgradeCost, character);
                    connection.close();
                    return card;
                }
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting card by name: " + e.getMessage());
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
                if (resultSet.getInt("isSpell") == 1) {
                    continue;
                } else {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int power = resultSet.getInt("power");
                    int duration = resultSet.getInt("duration");
                    int damage = resultSet.getInt("damage");
                    int upgradeLevel = resultSet.getInt("upgradeLevel");
                    int upgradeCost = resultSet.getInt("upgradeCost");
                    int character = resultSet.getInt("character");
                    Card card = new Card(id, name, power, duration, damage, upgradeLevel, upgradeCost, character);
                    cards.add(card);
                }
            }
            connection.close();
            return cards;
        } catch (SQLException e) {
            System.out.println("Error getting all cards: " + e.getMessage());
            return null;
        }
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
        Card card1 = new Card("Supporting Cover", 37, 2, 40, 2, 150, 1);
        Card card2 = new Card("Flash Pellets", 29, 4, 28, 1, 100, 1);
        Card card3 = new Card("Swift Justice", 36, 2, 52, 2, 200, 1);
        Card card4 = new Card("Rapid Recoil", 25, 4, 40, 1, 100, 1);
        Card card5 = new Card("Bullet Flurry", 31, 2, 32, 2, 150, 2);
        Card card6 = new Card("Run & Gun", 28, 1, 28, 1, 80, 2);
        Card card7 = new Card("Leaping Fury", 30, 3, 21, 2, 70, 2);
        Card card8 = new Card("Bullet Blast", 35, 5, 55, 3, 250, 2);
        Card card9 = new Card("Bullet Assault", 30, 5, 25, 1, 150, 3);
        Card card10 = new Card("Stealth Support", 35, 4, 20, 2, 100, 3);
        Card card11 = new Card("Damage Breaker", 40 , 2, 40, 4, 350, 3);
        Card card12 = new Card("IDN Burst", 30, 3, 21, 2, 100, 3);
        Card card13 = new Card("Multi-Fire", 30, 2, 26, 2, 150, 4);
        Card card14 = new Card("Shattered Augmentation", 25, 5, 40, 1, 150, 4);
        Card card15 = new Card("Biker Support", 30, 3, 21, 2, 150, 4);
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
        Card card16 = new Card("Shield", 0, 1, 0, 0, 300, 0);
        Card card17 = new Card("Heal", 0, 1, 20, 0, 300, 0);
        Card card18 = new Card("Hole Changer", 0, 1, 0, 0, 300, 0);
        Card card19 = new Card("Repair", 0, 1, 0, 0, 300, 0);
        Card card20 = new Card("Skip Rounds", 0, 1, 0, 0, 300, 0);
        Card card21 = new Card("Hand Hider", 0, 1, 0, 0, 300, 0);
        Card card22 = new Card("Card Swapper", 0, 1, 0, 0, 300, 0);
        addCard(card16);
        addCard(card17);
        addCard(card18);
        addCard(card19);
        addCard(card20);
        addCard(card21);
        addCard(card22);
    }

    public void addGame(Game game) {
        try {
            String insertQuery = "INSERT INTO games (player1ID, player2ID, winner, date, gameMode, prize) VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, getUserId(game.getPlayer1().getUsername()));
            statement.setInt(2, getUserId(game.getPlayer2().getUsername()));
            statement.setInt(3, game.getWinner());
            statement.setTimestamp(4, game.getDate());
            statement.setInt(5, game.getGameMode());
            statement.setString(6, game.getPrize().dbString());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding game: " + e.getMessage());
        }
    }

    public void deleteGame(int id) {
        try {
            String deleteQuery = "DELETE FROM games WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error deleting game: " + e.getMessage());
        }
    }

    public ArrayList<Game> getUserGamesList(User user) {
        try {
            String selectQuery = "SELECT * FROM games WHERE player1ID = ? OR player2ID = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, getUserId(user.getUsername()));
            statement.setInt(2, getUserId(user.getUsername()));
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Game> games = new ArrayList<Game>();
            while (resultSet.next()) {
                int player1ID = resultSet.getInt("player1ID");
                int player2ID = resultSet.getInt("player2ID");
                int winner = resultSet.getInt("winner");
                Timestamp date = resultSet.getTimestamp("date");
                int gameMode = resultSet.getInt("gameMode");
                Prize prize;

                String[] prizeParts = resultSet.getString("prize").split(",");
                prize = new Prize(Integer.parseInt(prizeParts[0]), Integer.parseInt(prizeParts[1]));
                // prizeParts[0]: xp, prizeParts[1]: balance

                User player1 = getUser(player1ID);
                User player2 = getUser(player2ID);
                Game game = new Game(gameMode, player1, player2, prize, date, winner);
                games.add(game);
            }
            connection.close();
            return games;
        } catch (SQLException e) {
            System.out.println("Error getting user games list: " + e.getMessage());
            return null;
        }
    }
}