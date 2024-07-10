package com.app.authentication;

import java.util.Random;
import java.util.ResourceBundle;

import com.Main;
import com.app.App;
import com.controller.AuthenticationController;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.net.URL;

public class SignupController implements Initializable {

  @FXML
  private TextField usernameField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField nicknameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private PasswordField confirmPasswordField;

  @FXML
  TextField captchaField;

  @FXML
  private Button signupButton;

  @FXML
  private Label captchaLabel;

  @FXML
  private Label errorLabel;

  @FXML
  private Button backButton;

  @FXML
  private CheckBox randomPasswordCheckBox;

  private AuthenticationController controller = new AuthenticationController();
  private int captchaValue;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        generateCaptcha();
        usernameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          if (newValue.length() > 20) {
            usernameField.setText(oldValue);
          }
          signupButton.setDisable(areFieldsEmpty());
        }));
        emailField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          if (newValue.length() > 50) {
            emailField.setText(oldValue);
          }
          signupButton.setDisable(areFieldsEmpty());
        }));
        nicknameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          if (newValue.length() > 20) {
            nicknameField.setText(oldValue);
          }
          signupButton.setDisable(areFieldsEmpty());
        }));
        passwordField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          if (newValue.length() > 20) {
            passwordField.setText(oldValue);
          }
          signupButton.setDisable(areFieldsEmpty());
        }));
        confirmPasswordField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          if (newValue.length() > 20) {
            confirmPasswordField.setText(oldValue);
          }
          signupButton.setDisable(areFieldsEmpty());
        }));
        captchaField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          if (newValue.length() > 7) {
            captchaField.setText(oldValue);
          }
          signupButton.setDisable(areFieldsEmpty());
        }));
      }
    });
  }

  @FXML
  protected void onSignupButtonClick() {
    String username = usernameField.getText();
    String email = emailField.getText();
    String nickname = nicknameField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();
    String captcha = captchaField.getText();

    if (username.isEmpty() || email.isEmpty() || nickname.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
        || captcha.isEmpty()) {
      errorLabel.setText("Please fill in all fields.");
      return;
    }

    if (!controller.isUsernameValid(username)) {
      errorLabel.setText("Invalid username.");
      return;
    }

    if (Main.crud.getUser(username) != null) {
      errorLabel.setText("Username already exists.");
      return;
    }

    if (!controller.isEmailValid(email)) {
      errorLabel.setText("Invalid email.");
      return;
    }

    if (!password.equals(confirmPassword)) {
      errorLabel.setText("Passwords do not match.");
      return;
    }

    if (!controller.validatePassword(password).equals("")) {
      errorLabel.setText(controller.validatePassword(password));
    }

    if (Integer.parseInt(captcha) != captchaValue) {
      errorLabel.setText("Invalid captcha.");
      generateCaptcha();
      return;
    }

    App.currentUser = new User(username, password, email, nickname, "", 0, "");

    try {
      App.setRoot("authentication/securityQuestion", "authentication/css/securityQuestion");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @FXML
  protected void onRandomPasswordCheckBoxClick() {
    if (randomPasswordCheckBox.isSelected()) {
      String password = controller.generateRandomPassword();
      passwordField.setText(password);
      passwordField.setDisable(true);
      confirmPasswordField.setText(password);
      confirmPasswordField.setDisable(true);
      // Show alert
      Stage stage = (Stage) signupButton.getScene().getWindow();

      AlertType alertType = AlertType.INFORMATION;
      String title = "Random Password";
      String headerText = "Random Password";
      String contentText = "Your random password is: " + password;
      Alert alert = new Alert(alertType);
      alert.setTitle(title);
      alert.setHeaderText(headerText);
      alert.setContentText(contentText);
      alert.initOwner(stage);
      alert.showAndWait();
    } else {
      passwordField.setDisable(false);
      confirmPasswordField.setDisable(false);
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

  private void generateCaptcha() {
    Random random = new Random();
    int firstDigit = 10 + random.nextInt(99 - 10 + 1);
    int secondDigit = 10 + random.nextInt(99 - 10 + 1);
    int operation = random.nextInt(2);
    if (operation == 0) {
      captchaValue = firstDigit + secondDigit;
      captchaLabel.setText(firstDigit + " + " + secondDigit + " = ?");
    } else {
      captchaLabel.setText(firstDigit + " - " + secondDigit + " = ?");
      captchaValue = firstDigit - secondDigit;
    }
  }

  private boolean areFieldsEmpty() {
    return usernameField.getText().isEmpty() || emailField.getText().isEmpty() || nicknameField.getText().isEmpty()
        || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()
        || captchaField.getText().length() != Math.floor(Math.log10(captchaValue) + 1);
  }

}