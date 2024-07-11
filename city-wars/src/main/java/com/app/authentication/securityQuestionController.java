package com.app.authentication;

import java.util.ArrayList;
import java.util.ResourceBundle;

import com.Main;
import com.app.App;
import com.model.Card;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

import java.net.URL;

public class securityQuestionController implements Initializable {

  @FXML
  private ChoiceBox<String> questionBox = new ChoiceBox<String>();

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

  final String[] questions = { "What is your father's name?", "What is your favorite color ?",
      "What was the name of your first pet?" };

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        questionBox.getItems().addAll(questions);
        answerField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
          enterButton.setDisable(newValue.isEmpty() || questionBox.getValue() == null);
        }));
        questionBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          enterButton.setDisable(answerField.getText().isEmpty() || newValue == null);
        });
      }
    });
  }

  @FXML
  protected void onEnterButtonClick() {
    String question = questionBox.getValue();
    String answer = answerField.getText();
    if (question == null || answer.isEmpty()) {
      errorLabel.setText("Please fill all the fields");
      return;
    }
    String username = App.currentUser.getUsername();
    String password = App.currentUser.getPassword();
    String email = App.currentUser.getEmail();
    String nickname = App.currentUser.getNickname();

    com.controller.MenuController controller = new com.controller.MenuController();

    ArrayList<Card> starterCards = controller.starterCards();

    if (question.equals(questions[0])) {
      User user = new User(username, password, email, nickname, "", 1, answer);
      App.currentUser = user;
    } else if (question.equals(questions[1])) {
      User user = new User(username, password, email, nickname, "", 2, answer);
      App.currentUser = user;
    } else if (question.equals(questions[2])) {
      User user = new User(username, password, email, nickname, "", 3, answer);
      App.currentUser = user;
    }

    App.currentUser.addCards(starterCards);

    Main.crud.addUser(App.currentUser);

    Main.loggedInUser = App.currentUser;
    Main.loggedInUserId = Main.crud.getUserId(App.currentUser.getUsername());

    AlertType alertType = AlertType.INFORMATION;
    String title = "Greetings!";
    String headerText = "Welcome to the game!";
    String contentText = "You have been given 1000 coins to start with.\n"
        + "You have been given the following cards:\n";
    for (Card card : starterCards) {
      contentText += card + "\n";
    }
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
    try {
      App.setRoot("menu/menu");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @FXML
  protected void onBackButtonClick() {
    try {
      App.currentUser = null;
      App.setRoot("authentication/login");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}