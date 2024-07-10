package com.app.authentication;

import java.util.ResourceBundle;

import com.Main;
import com.app.App;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Platform;


import java.net.URL;

public class ForgotPasswordController implements Initializable {

  @FXML
  private TextField usernameField;

  @FXML
  private TextField answerField;

  @FXML
  private Button enterButton;

  @FXML
  private Button backButton;

  @FXML
  private Label questionLabel;

  @FXML
  private Label errorLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        usernameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          User user = Main.crud.getUser(newValue);
          if (user != null) {
            questionLabel.setText(user.getSecurityQuestion());
            answerField.setDisable(false);
            answerField.setOpacity(0.5);
          } else {
            questionLabel.setText("");
            answerField.setDisable(true);
            answerField.setOpacity(0);
            answerField.setText("");
          }
        }));
        answerField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          enterButton.setDisable(newValue.isEmpty());
        }));
      }
    });
  }

  @FXML
  protected void onEnterButtonClick() {
    String username = usernameField.getText();
    String answer = answerField.getText();
    User user = Main.crud.getUser(username);
    if (user == null || !user.getSecurityQuestionAnswer().equals(answer)) {
      errorLabel.setText("Incorrect Answer");
    } else {
      try {
        App.currentUser = user;
        App.setRoot("authentication/resetPassword", "authentication/css/resetPassword");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  protected void onBackButtonClick() {
    try {
      App.setRoot("authentication/login", "authentication/css/login");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}