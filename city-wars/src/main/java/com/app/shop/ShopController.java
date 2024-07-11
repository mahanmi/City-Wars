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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

  @FXML
  private Label errorLabel;

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
  private ImageView cardImage1Up;

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
  private ImageView cardImage2Up;

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
  private ImageView cardImage3Up;

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
  private ImageView cardImage4Up;

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
  private ImageView cardImage5Up;

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
  private ImageView cardImage6Up;

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
  private ImageView cardImage7Up;

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
  private ImageView cardImage8Up;

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
  private ImageView cardImage9Up;

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
  private ImageView cardImage10Up;

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
  private ImageView cardImage11Up;

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
  private ImageView cardImage12Up;

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
  private ImageView cardImage13Up;

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
  private ImageView cardImage14Up;

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
  private ImageView cardImage15Up;

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
  private ImageView cardImage16Up;

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
  private ImageView cardImage17Up;

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
  private ImageView cardImage18Up;

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
  private ImageView cardImage1Buy;

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
  private ImageView cardImage2Buy;

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
  private ImageView cardImage3Buy;

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
  private ImageView cardImage4Buy;

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
  private ImageView cardImage5Buy;

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
  private ImageView cardImage6Buy;

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
  private ImageView cardImage7Buy;

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
  private ImageView cardImage8Buy;

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
  private ImageView cardImage9Buy;

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
  private ImageView cardImage10Buy;

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
  private ImageView cardImage11Buy;

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
  private ImageView cardImage12Buy;

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
  private ImageView cardImage13Buy;

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
  private ImageView cardImage14Buy;

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
  private ImageView cardImage15Buy;

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
  private ImageView cardImage16Buy;

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
  private ImageView cardImage17Buy;

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
  private ImageView cardImage18Buy;

  private ArrayList<Card> cardsUp = Main.loggedInUser.cards;

  private ArrayList<Card> cardsBuy = Main.loggedInUser.notOwnedCards();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    Platform.runLater(new Runnable() {
      @Override
      public void run() {

        Label[] powerLabelsUp = new Label[18];
        {
          powerLabelsUp[0] = powerLabel1Up;
          powerLabelsUp[1] = powerLabel2Up;
          powerLabelsUp[2] = powerLabel3Up;
          powerLabelsUp[3] = powerLabel4Up;
          powerLabelsUp[4] = powerLabel5Up;
          powerLabelsUp[5] = powerLabel6Up;
          powerLabelsUp[6] = powerLabel7Up;
          powerLabelsUp[7] = powerLabel8Up;
          powerLabelsUp[8] = powerLabel9Up;
          powerLabelsUp[9] = powerLabel10Up;
          powerLabelsUp[10] = powerLabel11Up;
          powerLabelsUp[11] = powerLabel12Up;
          powerLabelsUp[12] = powerLabel13Up;
          powerLabelsUp[13] = powerLabel14Up;
          powerLabelsUp[14] = powerLabel15Up;
          powerLabelsUp[15] = powerLabel16Up;
          powerLabelsUp[16] = powerLabel17Up;
          powerLabelsUp[17] = powerLabel18Up;
        }

        Label[] damageLabelsUp = new Label[18];
        {
          damageLabelsUp[0] = damageLabel1Up;
          damageLabelsUp[1] = damageLabel2Up;
          damageLabelsUp[2] = damageLabel3Up;
          damageLabelsUp[3] = damageLabel4Up;
          damageLabelsUp[4] = damageLabel5Up;
          damageLabelsUp[5] = damageLabel6Up;
          damageLabelsUp[6] = damageLabel7Up;
          damageLabelsUp[7] = damageLabel8Up;
          damageLabelsUp[8] = damageLabel9Up;
          damageLabelsUp[9] = damageLabel10Up;
          damageLabelsUp[10] = damageLabel11Up;
          damageLabelsUp[11] = damageLabel12Up;
          damageLabelsUp[12] = damageLabel13Up;
          damageLabelsUp[13] = damageLabel14Up;
          damageLabelsUp[14] = damageLabel15Up;
          damageLabelsUp[15] = damageLabel16Up;
          damageLabelsUp[16] = damageLabel17Up;
          damageLabelsUp[17] = damageLabel18Up;
        }

        Label[] priceLabelsUp = new Label[18];
        {
          priceLabelsUp[0] = priceLabel1Up;
          priceLabelsUp[1] = priceLabel2Up;
          priceLabelsUp[2] = priceLabel3Up;
          priceLabelsUp[3] = priceLabel4Up;
          priceLabelsUp[4] = priceLabel5Up;
          priceLabelsUp[5] = priceLabel6Up;
          priceLabelsUp[6] = priceLabel7Up;
          priceLabelsUp[7] = priceLabel8Up;
          priceLabelsUp[8] = priceLabel9Up;
          priceLabelsUp[9] = priceLabel10Up;
          priceLabelsUp[10] = priceLabel11Up;
          priceLabelsUp[11] = priceLabel12Up;
          priceLabelsUp[12] = priceLabel13Up;
          priceLabelsUp[13] = priceLabel14Up;
          priceLabelsUp[14] = priceLabel15Up;
          priceLabelsUp[15] = priceLabel16Up;
          priceLabelsUp[16] = priceLabel17Up;
          priceLabelsUp[17] = priceLabel18Up;
        }

        Label[] nameLabelsUp = new Label[18];
        {
          nameLabelsUp[0] = nameLabel1Up;
          nameLabelsUp[1] = nameLabel2Up;
          nameLabelsUp[2] = nameLabel3Up;
          nameLabelsUp[3] = nameLabel4Up;
          nameLabelsUp[4] = nameLabel5Up;
          nameLabelsUp[5] = nameLabel6Up;
          nameLabelsUp[6] = nameLabel7Up;
          nameLabelsUp[7] = nameLabel8Up;
          nameLabelsUp[8] = nameLabel9Up;
          nameLabelsUp[9] = nameLabel10Up;
          nameLabelsUp[10] = nameLabel11Up;
          nameLabelsUp[11] = nameLabel12Up;
          nameLabelsUp[12] = nameLabel13Up;
          nameLabelsUp[13] = nameLabel14Up;
          nameLabelsUp[14] = nameLabel15Up;
          nameLabelsUp[15] = nameLabel16Up;
          nameLabelsUp[16] = nameLabel17Up;
          nameLabelsUp[17] = nameLabel18Up;
        }

        Label[] durationLabelsUp = new Label[18];
        {
          durationLabelsUp[0] = durationLabel1Up;
          durationLabelsUp[1] = durationLabel2Up;
          durationLabelsUp[2] = durationLabel3Up;
          durationLabelsUp[3] = durationLabel4Up;
          durationLabelsUp[4] = durationLabel5Up;
          durationLabelsUp[5] = durationLabel6Up;
          durationLabelsUp[6] = durationLabel7Up;
          durationLabelsUp[7] = durationLabel8Up;
          durationLabelsUp[8] = durationLabel9Up;
          durationLabelsUp[9] = durationLabel10Up;
          durationLabelsUp[10] = durationLabel11Up;
          durationLabelsUp[11] = durationLabel12Up;
          durationLabelsUp[12] = durationLabel13Up;
          durationLabelsUp[13] = durationLabel14Up;
          durationLabelsUp[14] = durationLabel15Up;
          durationLabelsUp[15] = durationLabel16Up;
          durationLabelsUp[16] = durationLabel17Up;
          durationLabelsUp[17] = durationLabel18Up;
        }

        ImageView[] cardImagesUp = new ImageView[18];
        {
          cardImagesUp[0] = cardImage1Up;
          cardImagesUp[1] = cardImage2Up;
          cardImagesUp[2] = cardImage3Up;
          cardImagesUp[3] = cardImage4Up;
          cardImagesUp[4] = cardImage5Up;
          cardImagesUp[5] = cardImage6Up;
          cardImagesUp[6] = cardImage7Up;
          cardImagesUp[7] = cardImage8Up;
          cardImagesUp[8] = cardImage9Up;
          cardImagesUp[9] = cardImage10Up;
          cardImagesUp[10] = cardImage11Up;
          cardImagesUp[11] = cardImage12Up;
          cardImagesUp[12] = cardImage13Up;
          cardImagesUp[13] = cardImage14Up;
          cardImagesUp[14] = cardImage15Up;
          cardImagesUp[15] = cardImage16Up;
          cardImagesUp[16] = cardImage17Up;
          cardImagesUp[17] = cardImage18Up;
        }

        Label[] powerLabelsBuy = new Label[18];
        {
          powerLabelsBuy[0] = powerLabel1Buy;
          powerLabelsBuy[1] = powerLabel2Buy;
          powerLabelsBuy[2] = powerLabel3Buy;
          powerLabelsBuy[3] = powerLabel4Buy;
          powerLabelsBuy[4] = powerLabel5Buy;
          powerLabelsBuy[5] = powerLabel6Buy;
          powerLabelsBuy[6] = powerLabel7Buy;
          powerLabelsBuy[7] = powerLabel8Buy;
          powerLabelsBuy[8] = powerLabel9Buy;
          powerLabelsBuy[9] = powerLabel10Buy;
          powerLabelsBuy[10] = powerLabel11Buy;
          powerLabelsBuy[11] = powerLabel12Buy;
          powerLabelsBuy[12] = powerLabel13Buy;
          powerLabelsBuy[13] = powerLabel14Buy;
          powerLabelsBuy[14] = powerLabel15Buy;
          powerLabelsBuy[15] = powerLabel16Buy;
          powerLabelsBuy[16] = powerLabel17Buy;
          powerLabelsBuy[17] = powerLabel18Buy;
        }

        Label[] damageLabelsBuy = new Label[18];
        {
          damageLabelsBuy[0] = damageLabel1Buy;
          damageLabelsBuy[1] = damageLabel2Buy;
          damageLabelsBuy[2] = damageLabel3Buy;
          damageLabelsBuy[3] = damageLabel4Buy;
          damageLabelsBuy[4] = damageLabel5Buy;
          damageLabelsBuy[5] = damageLabel6Buy;
          damageLabelsBuy[6] = damageLabel7Buy;
          damageLabelsBuy[7] = damageLabel8Buy;
          damageLabelsBuy[8] = damageLabel9Buy;
          damageLabelsBuy[9] = damageLabel10Buy;
          damageLabelsBuy[10] = damageLabel11Buy;
          damageLabelsBuy[11] = damageLabel12Buy;
          damageLabelsBuy[12] = damageLabel13Buy;
          damageLabelsBuy[13] = damageLabel14Buy;
          damageLabelsBuy[14] = damageLabel15Buy;
          damageLabelsBuy[15] = damageLabel16Buy;
          damageLabelsBuy[16] = damageLabel17Buy;
          damageLabelsBuy[17] = damageLabel18Buy;
        }

        Label[] durationLabelsBuy = new Label[18];
        {
          durationLabelsBuy[0] = durationLabel1Buy;
          durationLabelsBuy[1] = durationLabel2Buy;
          durationLabelsBuy[2] = durationLabel3Buy;
          durationLabelsBuy[3] = durationLabel4Buy;
          durationLabelsBuy[4] = durationLabel5Buy;
          durationLabelsBuy[5] = durationLabel6Buy;
          durationLabelsBuy[6] = durationLabel7Buy;
          durationLabelsBuy[7] = durationLabel8Buy;
          durationLabelsBuy[8] = durationLabel9Buy;
          durationLabelsBuy[9] = durationLabel10Buy;
          durationLabelsBuy[10] = durationLabel11Buy;
          durationLabelsBuy[11] = durationLabel12Buy;
          durationLabelsBuy[12] = durationLabel13Buy;
          durationLabelsBuy[13] = durationLabel14Buy;
          durationLabelsBuy[14] = durationLabel15Buy;
          durationLabelsBuy[15] = durationLabel16Buy;
          durationLabelsBuy[16] = durationLabel17Buy;
          durationLabelsBuy[17] = durationLabel18Buy;
        }

        Label[] priceLabelsBuy = new Label[18];
        {
          priceLabelsBuy[0] = priceLabel1Buy;
          priceLabelsBuy[1] = priceLabel2Buy;
          priceLabelsBuy[2] = priceLabel3Buy;
          priceLabelsBuy[3] = priceLabel4Buy;
          priceLabelsBuy[4] = priceLabel5Buy;
          priceLabelsBuy[5] = priceLabel6Buy;
          priceLabelsBuy[6] = priceLabel7Buy;
          priceLabelsBuy[7] = priceLabel8Buy;
          priceLabelsBuy[8] = priceLabel9Buy;
          priceLabelsBuy[9] = priceLabel10Buy;
          priceLabelsBuy[10] = priceLabel11Buy;
          priceLabelsBuy[11] = priceLabel12Buy;
          priceLabelsBuy[12] = priceLabel13Buy;
          priceLabelsBuy[13] = priceLabel14Buy;
          priceLabelsBuy[14] = priceLabel15Buy;
          priceLabelsBuy[15] = priceLabel16Buy;
          priceLabelsBuy[16] = priceLabel17Buy;
          priceLabelsBuy[17] = priceLabel18Buy;
        }

        Label[] nameLabelsBuy = new Label[18];
        {
          nameLabelsBuy[0] = nameLabel1Buy;
          nameLabelsBuy[1] = nameLabel2Buy;
          nameLabelsBuy[2] = nameLabel3Buy;
          nameLabelsBuy[3] = nameLabel4Buy;
          nameLabelsBuy[4] = nameLabel5Buy;
          nameLabelsBuy[5] = nameLabel6Buy;
          nameLabelsBuy[6] = nameLabel7Buy;
          nameLabelsBuy[7] = nameLabel8Buy;
          nameLabelsBuy[8] = nameLabel9Buy;
          nameLabelsBuy[9] = nameLabel10Buy;
          nameLabelsBuy[10] = nameLabel11Buy;
          nameLabelsBuy[11] = nameLabel12Buy;
          nameLabelsBuy[12] = nameLabel13Buy;
          nameLabelsBuy[13] = nameLabel14Buy;
          nameLabelsBuy[14] = nameLabel15Buy;
          nameLabelsBuy[15] = nameLabel16Buy;
          nameLabelsBuy[16] = nameLabel17Buy;
          nameLabelsBuy[17] = nameLabel18Buy;
        }

        ImageView[] cardImagesBuy = new ImageView[18];
        {
          cardImagesBuy[0] = cardImage1Buy;
          cardImagesBuy[1] = cardImage2Buy;
          cardImagesBuy[2] = cardImage3Buy;
          cardImagesBuy[3] = cardImage4Buy;
          cardImagesBuy[4] = cardImage5Buy;
          cardImagesBuy[5] = cardImage6Buy;
          cardImagesBuy[6] = cardImage7Buy;
          cardImagesBuy[7] = cardImage8Buy;
          cardImagesBuy[8] = cardImage9Buy;
          cardImagesBuy[9] = cardImage10Buy;
          cardImagesBuy[10] = cardImage11Buy;
          cardImagesBuy[11] = cardImage12Buy;
          cardImagesBuy[12] = cardImage13Buy;
          cardImagesBuy[13] = cardImage14Buy;
          cardImagesBuy[14] = cardImage15Buy;
          cardImagesBuy[15] = cardImage16Buy;
          cardImagesBuy[16] = cardImage17Buy;
          cardImagesBuy[17] = cardImage18Buy;
        }

        cardsUp = Main.loggedInUser.cards;

        cardsBuy = Main.loggedInUser.notOwnedCards();

        for (int index = 0; index < 18; index++) {
          if (index < cardsUp.size()) {
            powerLabelsUp[index].setText(String.valueOf(cardsUp.get(index).getPower()));
            damageLabelsUp[index].setText(String.valueOf(cardsUp.get(index).getDamage()));
            durationLabelsUp[index].setText(String.valueOf(cardsUp.get(index).getDuration()));
            priceLabelsUp[index].setText(String.valueOf(cardsUp.get(index).getUpgradeCost()));
            nameLabelsUp[index].setText(cardsUp.get(index).getName());
            cardImagesUp[index].setImage(cardsUp.get(index).getImage());
            cardImagesUp[index].visibleProperty().setValue(true);
            cardImagesUp[index].disableProperty().setValue(false);
          } else {
            powerLabelsUp[index].setText("");
            damageLabelsUp[index].setText("");
            durationLabelsUp[index].setText("");
            priceLabelsUp[index].setText("");
            nameLabelsUp[index].setText("");
            cardImagesUp[index].visibleProperty().setValue(false);
            cardImagesUp[index].disableProperty().setValue(true);
          }
        }

        for (int index = 0; index < 18; index++) {
          if (index < cardsBuy.size()) {
            powerLabelsBuy[index].setText(String.valueOf(cardsBuy.get(index).getPower()));
            damageLabelsBuy[index].setText(String.valueOf(cardsBuy.get(index).getDamage()));
            durationLabelsBuy[index].setText(String.valueOf(cardsBuy.get(index).getDuration()));
            priceLabelsBuy[index].setText(String.valueOf(3 * cardsBuy.get(index).getUpgradeCost()));
            nameLabelsBuy[index].setText(cardsBuy.get(index).getName());
            cardImagesBuy[index].setImage(cardsBuy.get(index).getImage());
            cardImagesBuy[index].visibleProperty().setValue(true);
            cardImagesBuy[index].disableProperty().setValue(false);
          } else {
            powerLabelsBuy[index].setText("");
            damageLabelsBuy[index].setText("");
            durationLabelsBuy[index].setText("");
            priceLabelsBuy[index].setText("");
            nameLabelsBuy[index].setText("");
            cardImagesBuy[index].visibleProperty().setValue(false);
            cardImagesBuy[index].disableProperty().setValue(true);
          }
        }

      }
    });
  }

  @FXML
  protected void onImageClick() {
    System.out.println("Image clicked");
  }

  @FXML
  protected void onUpgrade1Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(0)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade2Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(1)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade3Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(2)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade4Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(3)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade5Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(4)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade6Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(5)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade7Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(6)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade8Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(7)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade9Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(8)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade10Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(9)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade11Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(10)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade12Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(11)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade13Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(12)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade14Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(13)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade15Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(14)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade16Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(15)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade17Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(16)));
    initialize(null, null);
  }

  @FXML
  protected void onUpgrade18Click() {
    errorLabel.setText(Main.loggedInUser.upgradeCard(cardsUp.get(17)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy1Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(0)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy2Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(1)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy3Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(2)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy4Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(3)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy5Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(4)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy6Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(5)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy7Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(6)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy8Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(7)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy9Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(8)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy10Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(9)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy11Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(10)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy12Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(11)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy13Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(12)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy14Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(13)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy15Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(14)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy16Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(15)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy17Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(16)));
    initialize(null, null);
  }

  @FXML
  protected void onBuy18Click() {
    errorLabel.setText(Main.loggedInUser.buyCard(cardsBuy.get(17)));
    initialize(null, null);
  }
}