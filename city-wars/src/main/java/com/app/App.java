package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
    scene.getStylesheets().add(App.class.getResource("CSS/app.css").toExternalForm());
    stage.setTitle("City Wars");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
