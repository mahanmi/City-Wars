package com;

import java.util.Scanner;

import com.controller.MenuController;
import com.model.User;
import com.view.authentication.Captcha;

public class Main {

    public static final int INITIAL_BALANCE = 1000;
    public static int loggedInUserId = -1;
    public static CRUD crud = new CRUD();
    public static Captcha captcha = new Captcha();
    public static User loggedInUser = null;
    public static String input;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuController menu = new MenuController();
        menu.run(scanner);
    }

    

}
