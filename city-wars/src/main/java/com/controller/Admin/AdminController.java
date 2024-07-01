package com.controller.Admin;

import java.util.ArrayList;
import java.util.Scanner;
import com.Main;
import com.model.*;

public class AdminController {
    final String password = "admin";

    public void login(String password, Scanner scanner){
        boolean validPassword = false;
        while(!validPassword){
            if (password.equals(this.password)){
                System.out.println("Logged in as admin.");
                validPassword = true;
                adminMenu(scanner);
            }
            else{
                System.out.println("Incorrect password.");
            }
        }
    }
    public void adminMenu(Scanner scanner){
        while(!Main.input.equals("back")){
            System.out.println("What would you like to do?");
            System.out.println("1. Add a card");
            System.out.println("2. Edit a card");
            System.out.println("3. Delete a card");
            System.out.println("4. View all players");
            
            Main.input = scanner.nextLine();
            switch (Main.input){
                case "1":
                    addCard(scanner);
                    break;
                case "2":
                    editCard(scanner);
                    break;
                case "3":
                    deleteCard(scanner);
                    break;
                case "4":
                    Main.crud.showAllUsers();
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    public void addCard(Scanner scanner){
        System.out.println("Enter the name of the card:");
        String name = scanner.nextLine();
        System.out.println("Enter the power of the card:");
        int power = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the duration of the card:");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the damage of the card:");
        int damage = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the upgrade level of the card:");
        int upgradeLevel = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the upgrade cost of the card:");
        int upgradeCost = Integer.parseInt(scanner.nextLine());

        Card card = new Card(name, power, duration, damage, upgradeLevel, upgradeCost);

        Main.crud.addCard(card);
        System.out.println("Card added successfully.");
    }

    public void editCard(Scanner scanner){
        ArrayList<Card> cards ; //mahan por kon
        cards = Main.crud.getAllCards();
        for (Card i : cards){
            System.out.println(i.getId() + ". " + i.getName());
        }
        System.out.println("Enter the id of the card you want to edit:");
        Main.input = scanner.nextLine();
        if(Main.input.equals("back")){
            return;
        }
        int id = Integer.parseInt(Main.input);
        Card card = Main.crud.getCardById(id);
        Main.crud.showCard(card);
        
        System.out.println("Enter card's new name:");
        String name = scanner.nextLine();
        System.out.println("Enter card's new power:");
        int power = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter card's new duration:");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter card's new damage:");
        int damage = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter card's new upgrade level:");
        int upgradeLevel = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter card's new upgrade cost:");
        int upgradeCost = Integer.parseInt(scanner.nextLine());

        System.out.println("Are you sure you want to edit this card? (y/n)");
        Main.input = scanner.nextLine();
        if(Main.input.equals("y")){
            Card newCard = new Card(name, power, duration, damage, upgradeLevel, upgradeCost);
            Main.crud.updateCard(newCard, id);
            System.out.println("Card edited successfully.");
        }
        else{
            return;
        }
    }

    public void deleteCard(Scanner scanner){
        ArrayList<Card> cards ; //mahan por kon
        cards = Main.crud.getAllCards();
        for (Card i : cards){
            System.out.println(i.getId() + ". " + i.getName());
        }
        System.out.println("Enter the id of the card you want to delete:");
        Main.input = scanner.nextLine();
        if(Main.input.equals("back")){
            return;
        }
        int id = Integer.parseInt(scanner.nextLine());
        Card card = Main.crud.getCardById(id);
        Main.crud.showCard(card);
        
        System.out.println("Are you sure you want to delete this card? (y/n)");
        Main.input = scanner.nextLine();
        if(Main.input.equals("y")){
            Main.crud.deleteCard(id);
            System.out.println("Card deleted successfully.");
        }
        else{
            return;
        }
    }
}