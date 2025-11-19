package it.cineca.hr.desktop_application.button_example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class ButtonExample extends Application {
   private final ChoiceBox<Pair<String, String>> assetClass = new ChoiceBox<>();
   private final static Pair<String, String> EMPTY_PAIR = new Pair<>("","");
   @Override
   public void start(Stage primaryStage) throws Exception {
      Label label = new Label("Hello World!");
      assetClass.setPrefWidth(200);
      Button button = new Button("Click me");

      HBox hBox = new HBox(
              label, assetClass, button
      );
      hBox.setSpacing(10.0d);
      hBox.setAlignment(Pos.CENTER);
      hBox.setPadding(new Insets(40));

      button.setOnAction(e -> {
         System.out.println("Button clicked!");
      });
      button.setOnAction((evt) -> {
         System.out.println("Saving "+ assetClass.getValue());
      });
      Scene scene = new Scene(hBox);
      initChoice();
      primaryStage.setScene(scene);
      primaryStage.setTitle("Something");
      primaryStage.show();
   }

   private void initChoice() {
      List<Pair<String, String>> assetClasses = new ArrayList<>();
      assetClasses.add( new Pair<>("Equip", "20000"));
      assetClasses.add( new Pair<>("Furniture", "20000"));
      assetClasses.add( new Pair<>("Investment", "20000"));
      assetClass.setConverter(new StringConverter<Pair<String, String>>() {
         @Override
         public String toString(Pair<String, String> pair) {
            return pair.getKey();
         }

         @Override
         public Pair<String, String> fromString(String string) {
            return null;
         }
      });
      assetClass.getItems().add(EMPTY_PAIR);
      assetClass.getItems().addAll(assetClasses);
      assetClass.setValue(EMPTY_PAIR);
   }

   public static void main(String[] args) {
      launch(args);
   }
}

