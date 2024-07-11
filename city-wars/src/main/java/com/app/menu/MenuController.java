package com.app.menu;

import com.Main;
import com.app.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class MenuController implements Initializable {

  @FXML
  private ProgressBar xpProgressBar;

  @FXML
  private Label userStatusLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        if (App.mediaPlayer == null) {
          Media sound = new Media(getClass().getResource("music/GOAT.mp3").toExternalForm());
          App.mediaPlayer = new MediaPlayer(sound);
          App.mediaPlayer.setVolume(0.75);
          App.mediaPlayer.play();
        }
        xpProgressBar.setProgress((double) Main.loggedInUser.getXp() / 100);
        userStatusLabel
            .setText("Level " + Main.loggedInUser.getLevel() + "\t" + "Balance: " + Main.loggedInUser.getBalance());
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
      App.setRoot("shop/shop");
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
      App.mediaPlayer.stop();
      App.setRoot("authentication/login");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}