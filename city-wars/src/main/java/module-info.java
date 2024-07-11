module city.wars {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;
  requires java.sql;
  requires asciitable;
  requires commons.lang3;
  requires skb.interfaces;

  opens com.app to javafx.fxml;
  opens com.app.authentication to javafx.fxml;
  opens com.app.menu to javafx.fxml;
  opens com.app.shop to javafx.fxml;
  opens com.app.settings to javafx.fxml;

  exports com.app;
}
