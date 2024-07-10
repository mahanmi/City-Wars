package com.app;

import com.Main;
import com.controller.AuthenticationController;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AppController {

  public void run() {
    if (Main.loggedInUser == null) {
      try {
        Parent root = FXMLLoader.load(getClass().getResource("authentication/login.fxml"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}