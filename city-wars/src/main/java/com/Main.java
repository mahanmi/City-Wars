package com;

import java.util.Scanner;

import com.model.User;
import com.view.MainMenu;

public class Main {

    public static final int INITIAL_BALANCE = 1000;
    public static final int STARTER_CARDS = 5;
    public static int loggedInUserId = -1;
    public static CRUD crud = new CRUD();
    public static User loggedInUser = null;
    public static String input;
    public static boolean run = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu menu = new MainMenu();
        while (run) {
            menu.run(scanner);
        }
    }

}
