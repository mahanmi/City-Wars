package com.app.authentication;

import java.util.ResourceBundle;

import com.app.App;
import com.controller.AuthenticationController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;

import java.net.URL;

public class ResetPasswordController implements Initializable {

  @FXML
  private TextField newPasswordField;

  @FXML
  private TextField newPasswordConfirmationField;

  @FXML
  private Button enterButton;

  @FXML
  private Button backButton;

  @FXML
  private Label questionLabel;

  @FXML
  private Label errorLabel;

  private AuthenticationController controller = new AuthenticationController();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        newPasswordField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          enterButton.setDisable(newValue.isEmpty() || newPasswordConfirmationField.getText().isEmpty());
          if (!newValue.isEmpty() && !newPasswordConfirmationField.getText().isEmpty()) {
            String error = controller.validatePassword(newValue);
            if (!newValue.equals(newPasswordConfirmationField.getText())) {
              errorLabel.setText("Passwords do not match!");
              enterButton.setDisable(true);
            } else if (error.equals("")) {
              errorLabel.setText("");
              enterButton.setDisable(false);
            } else {
              errorLabel.setText(error);
              enterButton.setDisable(true);
            }
          }
        }));
        newPasswordConfirmationField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          enterButton.setDisable(newValue.isEmpty() || newPasswordField.getText().isEmpty());
          if (!newValue.isEmpty() && !newPasswordField.getText().isEmpty()) {
            String error = controller.validatePassword(newValue);
            if (!newValue.equals(newPasswordField.getText())) {
              errorLabel.setText("Passwords do not match!");
              enterButton.setDisable(true);
            } else if (error.equals("")) {
              errorLabel.setText("");
              enterButton.setDisable(false);
            } else {
              errorLabel.setText(error);
              enterButton.setDisable(true);
            }
          }
        }));
      }
    });
  }

  @FXML
  protected void onEnterButtonClick() {
    String password = newPasswordField.getText();
    String passwordConfirmation = newPasswordConfirmationField.getText();
    if (password.equals(passwordConfirmation)) {
      App.currentUser.setPassword(password);
      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
        errorLabel.setText("Password reset successfully!");
      }));
      timeline.setCycleCount(3);
      timeline.play();
      timeline.setOnFinished(event -> {
        try {
          App.setRoot("authentication/login");
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } else {
      errorLabel.setText("Passwords do not match!");
    }
  }

  @FXML
  protected void onBackButtonClick() {
    try {
      App.currentUser = null;
      App.setRoot("authentication/forgotPassword");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}