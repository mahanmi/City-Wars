module city.wars {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires asciitable;
  requires commons.lang3;
  requires skb.interfaces;

  opens com.app to javafx.fxml;
  opens com.app.authentication to javafx.fxml;
  opens com.app.menu to javafx.fxml;

  exports com.app;
}
