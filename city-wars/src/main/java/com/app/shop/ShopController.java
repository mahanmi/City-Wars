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
  private Label powerLabel1Up;

  @FXML
  private Label damageLabel1Up;

  @FXML
  private Label durationLabel1Up;

  @FXML
  private Label priceLabel1Up;

  @FXML
  private Label nameLabel1Up;

  @FXML
  ImageView cardImage1Up;

  @FXML
  private Label powerLabel2Up;

  @FXML
  private Label damageLabel2Up;

  @FXML
  private Label durationLabel2Up;

  @FXML
  private Label priceLabel2Up;

  @FXML
  private Label nameLabel2Up;

  @FXML
  ImageView cardImage2Up;

  @FXML
  private Label powerLabel3Up;

  @FXML
  private Label damageLabel3Up;

  @FXML
  private Label durationLabel3Up;

  @FXML
  private Label priceLabel3Up;

  @FXML
  private Label nameLabel3Up;

  @FXML
  ImageView cardImage3Up;

  @FXML
  private Label powerLabel4Up;

  @FXML
  private Label damageLabel4Up;

  @FXML
  private Label durationLabel4Up;

  @FXML
  private Label priceLabel4Up;

  @FXML
  private Label nameLabel4Up;

  @FXML
  ImageView cardImage4Up;

  @FXML
  private Label powerLabel5Up;

  @FXML
  private Label damageLabel5Up;

  @FXML
  private Label durationLabel5Up;

  @FXML
  private Label priceLabel5Up;

  @FXML
  private Label nameLabel5Up;

  @FXML
  ImageView cardImage5Up;

  @FXML
  private Label powerLabel6Up;

  @FXML
  private Label damageLabel6Up;

  @FXML
  private Label durationLabel6Up;

  @FXML
  private Label priceLabel6Up;

  @FXML
  private Label nameLabel6Up;

  @FXML
  ImageView cardImage6Up;

  @FXML
  private Label powerLabel7Up;

  @FXML
  private Label damageLabel7Up;

  @FXML
  private Label durationLabel7Up;

  @FXML
  private Label priceLabel7Up;

  @FXML
  private Label nameLabel7Up;

  @FXML
  ImageView cardImage7Up;

  @FXML
  private Label powerLabel8Up;

  @FXML
  private Label damageLabel8Up;

  @FXML
  private Label durationLabel8Up;

  @FXML
  private Label priceLabel8Up;

  @FXML
  private Label nameLabel8Up;

  @FXML
  ImageView cardImage8Up;

  @FXML
  private Label powerLabel9Up;

  @FXML
  private Label damageLabel9Up;

  @FXML
  private Label durationLabel9Up;

  @FXML
  private Label priceLabel9Up;

  @FXML
  private Label nameLabel9Up;

  @FXML
  ImageView cardImage9Up;

  @FXML
  private Label powerLabel10Up;

  @FXML
  private Label damageLabel10Up;

  @FXML
  private Label durationLabel10Up;

  @FXML
  private Label priceLabel10Up;

  @FXML
  private Label nameLabel10Up;

  @FXML
  ImageView cardImage10Up;

  @FXML
  private Label powerLabel11Up;

  @FXML
  private Label damageLabel11Up;

  @FXML
  private Label durationLabel11Up;

  @FXML
  private Label priceLabel11Up;

  @FXML
  private Label nameLabel11Up;

  @FXML
  ImageView cardImage11Up;

  @FXML
  private Label powerLabel12Up;

  @FXML
  private Label damageLabel12Up;

  @FXML
  private Label durationLabel12Up;

  @FXML
  private Label priceLabel12Up;

  @FXML
  private Label nameLabel12Up;

  @FXML
  ImageView cardImage12Up;

  @FXML
  private Label powerLabel13Up;

  @FXML
  private Label damageLabel13Up;

  @FXML
  private Label durationLabel13Up;

  @FXML
  private Label priceLabel13Up;

  @FXML
  private Label nameLabel13Up;

  @FXML
  ImageView cardImage13Up;

  @FXML
  private Label powerLabel14Up;

  @FXML
  private Label damageLabel14Up;

  @FXML
  private Label durationLabel14Up;

  @FXML
  private Label priceLabel14Up;

  @FXML
  private Label nameLabel14Up;

  @FXML
  ImageView cardImage14Up;

  @FXML
  private Label powerLabel15Up;

  @FXML
  private Label damageLabel15Up;

  @FXML
  private Label durationLabel15Up;

  @FXML
  private Label priceLabel15Up;

  @FXML
  private Label nameLabel15Up;

  @FXML
  ImageView cardImage15Up;

  @FXML
  private Label powerLabel16Up;

  @FXML
  private Label damageLabel16Up;

  @FXML
  private Label durationLabel16Up;

  @FXML
  private Label priceLabel16Up;

  @FXML
  private Label nameLabel16Up;

  @FXML
  ImageView cardImage16Up;

  @FXML
  private Label powerLabel17Up;

  @FXML
  private Label damageLabel17Up;

  @FXML
  private Label durationLabel17Up;

  @FXML
  private Label priceLabel17Up;

  @FXML
  private Label nameLabel17Up;

  @FXML
  ImageView cardImage17Up;

  @FXML
  private Label powerLabel18Up;

  @FXML
  private Label damageLabel18Up;

  @FXML
  private Label durationLabel18Up;

  @FXML
  private Label priceLabel18Up;

  @FXML
  private Label nameLabel18Up;

  @FXML
  ImageView cardImage18Up;

  @FXML
  private Label powerLabel1Buy;

  @FXML
  private Label damageLabel1Buy;

  @FXML
  private Label durationLabel1Buy;

  @FXML
  private Label priceLabel1Buy;

  @FXML
  private Label nameLabel1Buy;

  @FXML
  ImageView cardImage1Buy;

  @FXML
  private Label powerLabel2Buy;

  @FXML
  private Label damageLabel2Buy;

  @FXML
  private Label durationLabel2Buy;

  @FXML
  private Label priceLabel2Buy;

  @FXML
  private Label nameLabel2Buy;

  @FXML
  ImageView cardImage2Buy;

  @FXML
  private Label powerLabel3Buy;

  @FXML
  private Label damageLabel3Buy;

  @FXML
  private Label durationLabel3Buy;

  @FXML
  private Label priceLabel3Buy;

  @FXML
  private Label nameLabel3Buy;

  @FXML
  ImageView cardImage3Buy;

  @FXML
  private Label powerLabel4Buy;

  @FXML
  private Label damageLabel4Buy;

  @FXML
  private Label durationLabel4Buy;

  @FXML
  private Label priceLabel4Buy;

  @FXML
  private Label nameLabel4Buy;

  @FXML
  ImageView cardImage4Buy;

  @FXML
  private Label powerLabel5Buy;

  @FXML
  private Label damageLabel5Buy;

  @FXML
  private Label durationLabel5Buy;

  @FXML
  private Label priceLabel5Buy;

  @FXML
  private Label nameLabel5Buy;

  @FXML
  ImageView cardImage5Buy;

  @FXML
  private Label powerLabel6Buy;

  @FXML
  private Label damageLabel6Buy;

  @FXML
  private Label durationLabel6Buy;

  @FXML
  private Label priceLabel6Buy;

  @FXML
  private Label nameLabel6Buy;

  @FXML
  ImageView cardImage6Buy;

  @FXML
  private Label powerLabel7Buy;

  @FXML
  private Label damageLabel7Buy;

  @FXML
  private Label durationLabel7Buy;

  @FXML
  private Label priceLabel7Buy;

  @FXML
  private Label nameLabel7Buy;

  @FXML
  ImageView cardImage7Buy;

  @FXML
  private Label powerLabel8Buy;

  @FXML
  private Label damageLabel8Buy;

  @FXML
  private Label durationLabel8Buy;

  @FXML
  private Label priceLabel8Buy;

  @FXML
  private Label nameLabel8Buy;

  @FXML
  ImageView cardImage8Buy;

  @FXML
  private Label powerLabel9Buy;

  @FXML
  private Label damageLabel9Buy;

  @FXML
  private Label durationLabel9Buy;

  @FXML
  private Label priceLabel9Buy;

  @FXML
  private Label nameLabel9Buy;

  @FXML
  ImageView cardImage9Buy;

  @FXML
  private Label powerLabel10Buy;

  @FXML
  private Label damageLabel10Buy;

  @FXML
  private Label durationLabel10Buy;

  @FXML
  private Label priceLabel10Buy;

  @FXML
  private Label nameLabel10Buy;

  @FXML
  ImageView cardImage10Buy;

  @FXML
  private Label powerLabel11Buy;

  @FXML
  private Label damageLabel11Buy;

  @FXML
  private Label durationLabel11Buy;

  @FXML
  private Label priceLabel11Buy;

  @FXML
  private Label nameLabel11Buy;

  @FXML
  ImageView cardImage11Buy;

  @FXML
  private Label powerLabel12Buy;

  @FXML
  private Label damageLabel12Buy;

  @FXML
  private Label durationLabel12Buy;

  @FXML
  private Label priceLabel12Buy;

  @FXML
  private Label nameLabel12Buy;

  @FXML
  ImageView cardImage12Buy;

  @FXML
  private Label powerLabel13Buy;

  @FXML
  private Label damageLabel13Buy;

  @FXML
  private Label durationLabel13Buy;

  @FXML
  private Label priceLabel13Buy;

  @FXML
  private Label nameLabel13Buy;

  @FXML
  ImageView cardImage13Buy;

  @FXML
  private Label powerLabel14Buy;

  @FXML
  private Label damageLabel14Buy;

  @FXML
  private Label durationLabel14Buy;

  @FXML
  private Label priceLabel14Buy;

  @FXML
  private Label nameLabel14Buy;

  @FXML
  ImageView cardImage14Buy;

  @FXML
  private Label powerLabel15Buy;

  @FXML
  private Label damageLabel15Buy;

  @FXML
  private Label durationLabel15Buy;

  @FXML
  private Label priceLabel15Buy;

  @FXML
  private Label nameLabel15Buy;

  @FXML
  ImageView cardImage15Buy;

  @FXML
  private Label powerLabel16Buy;

  @FXML
  private Label damageLabel16Buy;

  @FXML
  private Label durationLabel16Buy;

  @FXML
  private Label priceLabel16Buy;

  @FXML
  private Label nameLabel16Buy;

  @FXML
  ImageView cardImage16Buy;

  @FXML
  private Label powerLabel17Buy;

  @FXML
  private Label damageLabel17Buy;

  @FXML
  private Label durationLabel17Buy;

  @FXML
  private Label priceLabel17Buy;

  @FXML
  private Label nameLabel17Buy;

  @FXML
  ImageView cardImage17Buy;

  @FXML
  private Label powerLabel18Buy;

  @FXML
  private Label damageLabel18Buy;

  @FXML
  private Label durationLabel18Buy;

  @FXML
  private Label priceLabel18Buy;

  @FXML
  private Label nameLabel18Buy;

  @FXML
  ImageView cardImage18Buy;

  private Label[] powerLabelsUp = { powerLabel1Up, powerLabel2Up, powerLabel3Up, powerLabel4Up, powerLabel5Up,
      powerLabel6Up,
      powerLabel7Up, powerLabel8Up, powerLabel9Up, powerLabel10Up, powerLabel11Up, powerLabel12Up, powerLabel13Up,
      powerLabel14Up, powerLabel15Up, powerLabel16Up, powerLabel17Up, powerLabel18Up };

  private Label[] damageLabelsUp = { damageLabel1Up, damageLabel2Up, damageLabel3Up, damageLabel4Up, damageLabel5Up,
      damageLabel6Up,
      damageLabel7Up, damageLabel8Up, damageLabel9Up, damageLabel10Up, damageLabel11Up, damageLabel12Up,
      damageLabel13Up,
      damageLabel14Up, damageLabel15Up, damageLabel16Up, damageLabel17Up, damageLabel18Up };

  private Label[] durationLabelsUp = { durationLabel1Up, durationLabel2Up, durationLabel3Up, durationLabel4Up,
      durationLabel5Up, durationLabel6Up,
      durationLabel7Up, durationLabel8Up, durationLabel9Up, durationLabel10Up, durationLabel11Up, durationLabel12Up,
      durationLabel13Up,
      durationLabel14Up, durationLabel15Up, durationLabel16Up, durationLabel17Up, durationLabel18Up };

  private Label[] priceLabelsUp = { priceLabel1Up, priceLabel2Up, priceLabel3Up, priceLabel4Up, priceLabel5Up,
      priceLabel6Up,
      priceLabel7Up, priceLabel8Up, priceLabel9Up, priceLabel10Up, priceLabel11Up, priceLabel12Up, priceLabel13Up,
      priceLabel14Up, priceLabel15Up, priceLabel16Up, priceLabel17Up, priceLabel18Up };

  private Label[] nameLabelsUp = { nameLabel1Up, nameLabel2Up, nameLabel3Up, nameLabel4Up, nameLabel5Up, nameLabel6Up,
      nameLabel7Up, nameLabel8Up, nameLabel9Up, nameLabel10Up, nameLabel11Up, nameLabel12Up, nameLabel13Up,
      nameLabel14Up, nameLabel15Up, nameLabel16Up, nameLabel17Up, nameLabel18Up };

  private ImageView[] cardImagesUp = { cardImage1Up, cardImage2Up, cardImage3Up, cardImage4Up, cardImage5Up,
      cardImage6Up,
      cardImage7Up, cardImage8Up, cardImage9Up, cardImage10Up, cardImage11Up, cardImage12Up, cardImage13Up,
      cardImage14Up, cardImage15Up, cardImage16Up, cardImage17Up, cardImage18Up };

  private Label[] powerLabelsBuy = { powerLabel1Buy, powerLabel2Buy, powerLabel3Buy, powerLabel4Buy, powerLabel5Buy,
      powerLabel6Buy, powerLabel7Buy, powerLabel8Buy, powerLabel9Buy, powerLabel10Buy, powerLabel11Buy, powerLabel12Buy,
      powerLabel13Buy, powerLabel14Buy, powerLabel15Buy, powerLabel16Buy, powerLabel17Buy, powerLabel18Buy };

  private Label[] damageLabelsBuy = { damageLabel1Buy, damageLabel2Buy, damageLabel3Buy, damageLabel4Buy,
      damageLabel5Buy, damageLabel6Buy, damageLabel7Buy, damageLabel8Buy, damageLabel9Buy, damageLabel10Buy,
      damageLabel11Buy, damageLabel12Buy, damageLabel13Buy, damageLabel14Buy, damageLabel15Buy, damageLabel16Buy,
      damageLabel17Buy, damageLabel18Buy };

  private Label[] durationLabelsBuy = { durationLabel1Buy, durationLabel2Buy, durationLabel3Buy, durationLabel4Buy,
      durationLabel5Buy, durationLabel6Buy, durationLabel7Buy, durationLabel8Buy, durationLabel9Buy, durationLabel10Buy,
      durationLabel11Buy, durationLabel12Buy, durationLabel13Buy, durationLabel14Buy, durationLabel15Buy,
      durationLabel16Buy, durationLabel17Buy, durationLabel18Buy };

  private Label[] priceLabelsBuy = { priceLabel1Buy, priceLabel2Buy, priceLabel3Buy, priceLabel4Buy, priceLabel5Buy,
      priceLabel6Buy, priceLabel7Buy, priceLabel8Buy, priceLabel9Buy, priceLabel10Buy, priceLabel11Buy, priceLabel12Buy,
      priceLabel13Buy, priceLabel14Buy, priceLabel15Buy, priceLabel16Buy, priceLabel17Buy, priceLabel18Buy };

  private Label[] nameLabelsBuy = { nameLabel1Buy, nameLabel2Buy, nameLabel3Buy, nameLabel4Buy, nameLabel5Buy,
      nameLabel6Buy, nameLabel7Buy, nameLabel8Buy, nameLabel9Buy, nameLabel10Buy, nameLabel11Buy, nameLabel12Buy,
      nameLabel13Buy, nameLabel14Buy, nameLabel15Buy, nameLabel16Buy, nameLabel17Buy, nameLabel18Buy };

  private ImageView[] cardImagesBuy = { cardImage1Buy, cardImage2Buy, cardImage3Buy, cardImage4Buy, cardImage5Buy,
      cardImage6Buy, cardImage7Buy, cardImage8Buy, cardImage9Buy, cardImage10Buy, cardImage11Buy, cardImage12Buy,
      cardImage13Buy, cardImage14Buy, cardImage15Buy, cardImage16Buy, cardImage17Buy, cardImage18Buy };


  private ArrayList<Card> cardsUp = Main.loggedInUser.cards;

  private ArrayList<Card> cardsBuy = Main.loggedInUser.notOwnedCards();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 18; i++) {
          if (i >= cardsUp.size()) {
            powerLabelsUp[i].setText("");
            damageLabelsUp[i].setText("");
            durationLabelsUp[i].setText("");
            priceLabelsUp[i].setText("");
            nameLabelsUp[i].setText("");
            cardImagesUp[i].setImage(null);
          }
          else {
            powerLabelsUp[i].setText(String.valueOf(cardsUp.get(i).getPower()));
            damageLabelsUp[i].setText(String.valueOf(cardsUp.get(i).getDamage()));
            durationLabelsUp[i].setText(String.valueOf(cardsUp.get(i).getDuration()));
            priceLabelsUp[i].setText(String.valueOf(cardsUp.get(i).getUpgradeCost()));
            nameLabelsUp[i].setText(cardsUp.get(i).getName());
            cardImagesUp[i].setImage(cardsUp.get(i).getImage());
          }
        }

        for (int i = 0; i < 18; i++) {
          if (i >= cardsBuy.size()) {
            powerLabelsBuy[i].setText("");
            damageLabelsBuy[i].setText("");
            durationLabelsBuy[i].setText("");
            priceLabelsBuy[i].setText("");
            nameLabelsBuy[i].setText("");
            cardImagesBuy[i].setImage(null);
          }
          else {
            powerLabelsBuy[i].setText(String.valueOf(cardsBuy.get(i).getPower()));
            damageLabelsBuy[i].setText(String.valueOf(cardsBuy.get(i).getDamage()));
            durationLabelsBuy[i].setText(String.valueOf(cardsBuy.get(i).getDuration()));
            priceLabelsBuy[i].setText(String.valueOf(cardsBuy.get(i).getUpgradeCost()));
            nameLabelsBuy[i].setText(cardsBuy.get(i).getName());
            cardImagesBuy[i].setImage(cardsBuy.get(i).getImage());
          }
        }
      }
    });
  }

  @FXML
  protected void onImageClick() {
    System.out.println("Image clicked");
  }

}