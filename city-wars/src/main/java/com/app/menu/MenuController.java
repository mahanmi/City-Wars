package com.app.menu;

import com.Main;
import com.app.App;

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

public class MenuController implements Initializable {

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {

      }
    });
  }

  @FXML
  protected void onPlayButtonClick() {
    try {
      App.setRoot("game/game");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void onGameHistoryButtonClick() {
    try {
      App.setRoot("gameHistory/gameHistory");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void onStoreAndInventoryButtonClick() {
    try {
      App.setRoot("storeAndInventory/storeAndInventory");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void onSettingsButtonClick() {
    try {
      App.setRoot("settings/settings");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  protected void onLogoutButtonClick() {
    try {
      Main.loggedInUser = null;
      Main.loggedInUserId = -1;
      App.setRoot("authentication/login");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}