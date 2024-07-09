package com.app;

import com.Main;
import com.controller.AuthenticationController;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AppController {

  @FXML
  private PasswordField passwordLabel;

  @FXML
  private TextField usernameField;

  @FXML
  private Button loginButton;

  @FXML
  private Label errorLabel;

  @FXML
  protected void onLoginButtonClick() {
    String username = usernameField.getText();
    String password = passwordLabel.getText();
    AuthenticationController controller = new AuthenticationController();
    User user = controller.login(username, password);
    if (user == null) {
      errorLabel.setText("Invalid username or password");
    } else {
      errorLabel.setText("Login successful!");
      Main.loggedInUser = user;
      Main.loggedInUserId = Main.crud.getUserId(user.getUsername());
    }
  }

}