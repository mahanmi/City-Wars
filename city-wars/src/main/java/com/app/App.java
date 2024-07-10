package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.model.User;

public class App extends Application {

  private static Scene scene;

  public static User currentUser;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("authentication/login"), 1200, 674);
    scene.getStylesheets().add(App.class.getResource("authentication/CSS/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setTitle("City Wars");
    stage.show();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  public static void setRoot(String fxml, String css) throws IOException {
    scene.setRoot(loadFXML(fxml));
    scene.getStylesheets().add(App.class.getResource(css + ".css").toExternalForm());
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) {
    launch();
  }

}