package com.app.shop;

import com.Main;
import com.app.App;
import com.model.Card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

  @FXML
  private Label powerLabel1;

  @FXML
  private Label damageLabel1;

  @FXML
  private Label durationLabel1;

  @FXML
  private Label priceLabel1;

  @FXML
  ImageView cardImage1;

  @FXML
  private Label powerLabel2;

  @FXML
  private Label damageLabel2;

  @FXML
  private Label durationLabel2;

  @FXML
  ImageView cardImage2;

  private Label[] powerLabels = { powerLabel1 };
  private Label[] damageLabels = { damageLabel1 };
  private Label[] durationLabels = { durationLabel1 };
  private Label[] priceLabels = { priceLabel1 };
  private ImageView[] cardImages = { cardImage1 };

  private ArrayList<Card> cards = Main.crud.getAllCards();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 18; i++) {
          powerLabels[i].setText(String.valueOf(cards.get(i).getPower()));
          damageLabels[i].setText(String.valueOf(cards.get(i).getDamage()));
          durationLabels[i].setText(String.valueOf(cards.get(i).getDuration()));
          priceLabels[i].setText(String.valueOf(cards.get(i).getUpgradeCost()));
          cardImages[i].setImage(cards.get(i).getImage());
        }
      }
    });
  }

  @FXML
  protected void onImageClick() {
    System.out.println("Image clicked");
  }

}