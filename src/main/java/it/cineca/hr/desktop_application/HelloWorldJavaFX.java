package it.cineca.hr.desktop_application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HelloWorldJavaFX extends Application {
   @Override
   public void start(Stage stage) {
      Label label = new Label("Hello World");
      Scene scene = new Scene(label, 200, 100);
      stage.setScene(scene);
      stage.setTitle("Hello JavaFX");
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
