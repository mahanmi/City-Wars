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
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, username TEXT UNIQUE, password TEXT,email TEXT UNIQUE,nickname TEXT,cardIDs TEXT,Q1 TEXT,Q2 TEXT,Q3 TEXT,balance INT)";
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
            String insertQuery = "INSERT INTO users (username, password, email, nickname, cardIDs, Q1, Q2, Q3, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNickname());
            statement.setString(5, user.getCardIDs());
            statement.setString(6, user.getSecurityQuestions()[0]);
            statement.setString(7, user.getSecurityQuestions()[1]);
            statement.setString(8, user.getSecurityQuestions()[2]);
            statement.setInt(9, user.getBalance());
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
            String updateQuery = "UPDATE users SET password = ?, email = ?, nickname = ?, cardIDs = ?, Q1 = ?, Q2 = ?, Q3 = ?, balance = ? WHERE username = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getNickname());
            statement.setString(4, user.getCardIDs());
            statement.setString(5, user.getSecurityQuestions()[0]);
            statement.setString(6, user.getSecurityQuestions()[1]);
            statement.setString(7, user.getSecurityQuestions()[2]);
            statement.setInt(8, user.getBalance());
            statement.setString(9, user.getUsername());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void updateUser(User user, int id) {
        try {
            String updateQuery = "UPDATE users SET username = ?, password = ?, email = ?, nickname = ?, cardIDs = ?, Q1 = ?, Q2 = ?, Q3 = ?, balance = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNickname());
            statement.setString(5, user.getCardIDs());
            statement.setString(6, user.getSecurityQuestions()[0]);
            statement.setString(7, user.getSecurityQuestions()[1]);
            statement.setString(8, user.getSecurityQuestions()[2]);
            statement.setInt(9, user.getBalance());
            statement.setInt(10, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public User getUserById(int id) {
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
                String Q1 = resultSet.getString("Q1");
                String Q2 = resultSet.getString("Q2");
                String Q3 = resultSet.getString("Q3");
                int balance = resultSet.getInt("balance");
                User user = new User(username, password, email, nickname, cardIDs, Q1, Q2, Q3, balance);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error getting user by id: " + e.getMessage());
            return null;
        }
    }

    public int getUserIdByUsername(String username) {
        try {
            String selectQuery = "SELECT id FROM users WHERE username = ?";
            Connection connection = DriverManager.getConnection("jdbc:sqlite:city-wars/src/main/resources/data.db");
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
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

    public Card getCardById(int id) {
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
                return card;
            } else {
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
            return cards;
        } catch (SQLException e) {
            System.out.println("Error getting all cards: " + e.getMessage());
            return null;
        }
    }

    public void showCard(Card card){
        System.out.println("Name: " + card.getName());
        System.out.println("\t Power: " + card.getPower());
        System.out.println("\t Duration: " + card.getDuration());
        System.out.println("\t Damage: " + card.getDamage());
        System.out.println("\t Upgrade Level: " + card.getUpgradeLevel());
        System.out.println("\t Upgrade Cost: " + card.getUpgradeCost());
    }

    public void showAllUsers(){
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
                String Q1 = resultSet.getString("Q1");
                String Q2 = resultSet.getString("Q2");
                String Q3 = resultSet.getString("Q3");
                int balance = resultSet.getInt("balance");
                User user = new User(username, password, email, nickname, cardIDs, Q1, Q2, Q3, balance);
                System.out.println("ID: " + id);
                System.out.println("\t Username: " + user.getUsername());
                System.out.println("\t Password: " + user.getPassword());
                System.out.println("\t Email: " + user.getEmail());
                System.out.println("\t Nickname: " + user.getNickname());
                System.out.println("\t Card IDs: " + user.getCardIDs());
                System.out.println("\t Security Questions: " + user.getSecurityQuestions()[0] + ", " + user.getSecurityQuestions()[1] + ", " + user.getSecurityQuestions()[2]);
                System.out.println("\t Balance: " + user.getBalance());
            }
        } catch (SQLException e) {
            System.out.println("Error showing all users: " + e.getMessage());
        }
    }
}
