package com.app.authentication;

import com.Main;
import com.app.App;
import com.controller.AuthenticationController;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordLabel;

  @FXML
  private Button loginButton;

  @FXML
  private Label errorLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        usernameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          loginButton.setDisable(newValue.trim().isEmpty() || passwordLabel.getText().trim().isEmpty());
        }));
        passwordLabel.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          loginButton.setDisable(newValue.trim().isEmpty() || usernameField.getText().trim().isEmpty());
        }));
      }
    });
  }

  private static final int BASE_LOCKOUT_TIME = 5;

  private long lockoutEndTime = 0;
  private int failedAttempts = 0;

  private int remainingTime() {
    return (int) ((lockoutEndTime - System.currentTimeMillis()) / 1000);
  }

  @FXML
  protected void onLoginButtonClick() {
    String username = usernameField.getText();
    String password = passwordLabel.getText();
    AuthenticationController controller = new AuthenticationController();

    User user = controller.login(username, password);
    if (user == null) {
      failedAttempts++;
      loginButton.setDisable(true);
      lockoutEndTime = System.currentTimeMillis() + BASE_LOCKOUT_TIME * failedAttempts * 1000 + 1500;

      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        errorLabel.setText("Invalid username or password\nTry again in " + remainingTime() + " seconds");
      }));
      timeline.setCycleCount(remainingTime());
      timeline.play();
      timeline.setOnFinished(event -> {
        loginButton.setDisable(false);
        errorLabel.setText("");
      });
    } else {
      errorLabel.setText("Login successful!");
      Main.loggedInUser = user;
      Main.loggedInUserId = Main.crud.getUserId(user.getUsername());
    }

  }

  @FXML
  protected void onForgotPasswordClick() {
    try {
      App.setRoot("authentication/forgotPassword","authentication/css/forgotPassword");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void onSignupHyperlinkClick() {
    try {
      App.setRoot("authentication/signup","authentication/css/signup");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}