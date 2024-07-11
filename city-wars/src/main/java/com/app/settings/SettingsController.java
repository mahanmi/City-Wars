package com.app.settings;

import com.app.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.application.Platform;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SettingsController implements Initializable {

  @FXML
  private Slider volumeSlider;

  @FXML
  private ChoiceBox<String> musicChoiceBox;

  @FXML
  private Button backButton;

  private double percentage = 0.75;

  private final String[] musicPaths = { "music/Mbappe.mp3", "music/QWERT.mp3", "music/Mochale.mp3",
      "music/Skechers.mp3", "music/SLEEP MODE.mp3", "music/Nefrat.mp3", "music/KSD.mp3" };

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
          percentage = 100.0 * newValue.doubleValue() / volumeSlider.getMax();
          App.mediaPlayer.setVolume(percentage / 100);
        });
        musicChoiceBox.getItems().addAll("Mbappe", "QWERT - TNTX", "Mochale - Talkhoon", "Skechers - DripReport",
            "SLEEP MODE - Shahi", "Nefrat - Hiphopologist", "KSD - NimOG");
        musicChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          App.mediaPlayer.stop();
          Media sound = new Media(getClass()
              .getResource(musicPaths[musicChoiceBox.getSelectionModel().getSelectedIndex()]).toExternalForm());
          App.mediaPlayer = new MediaPlayer(sound);
          App.mediaPlayer.setVolume(volumeSlider.getValue() / 100);
          App.mediaPlayer.play();
        });

      }
    });
  }

  @FXML
  protected void onBackButtonClick() {
    try {
      App.setRoot("menu/menu");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}