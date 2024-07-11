package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

import com.model.User;

public class App extends Application {

  private static Scene scene;

  public static User currentUser;

  public static MediaPlayer mediaPlayer;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("authentication/login"), 1366, 768);
    scene.getStylesheets().add(App.class.getResource("authentication/CSS/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setTitle("City Wars");
    stage.show();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }

}
