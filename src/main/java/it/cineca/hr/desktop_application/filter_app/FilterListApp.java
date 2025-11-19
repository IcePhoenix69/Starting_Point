package it.cineca.hr.desktop_application.filter_app;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterListApp extends Application {

   @Override
   public void start(Stage primaryStage) throws Exception {
      Player[] players = {new Player("BOS", "David Ortiz"),
         new Player("BOS", "Jackie Bradley Jr."),
         new Player("BOS", "Xander Bogarts"),
         new Player("BOS", "Mookie Betts"),
         new Player("HOU", "Jose Altuve"),
         new Player("HOU", "Will Harris"),
         new Player("WSH", "Max Scherzer"),
         new Player("WSH", "Bryce Harper"),
         new Player("WSH", "Daniel Murphy"),
         new Player("WSH", "Wilson Ramos"),
         new Player("GIG","Matteo Ventura")
      };

      ReadOnlyObjectProperty<ObservableList<Player>> playersProperty =
         new SimpleObjectProperty<>(FXCollections.observableArrayList());

      ReadOnlyObjectProperty<FilteredList<Player>> viewablePlayersProperty =
         new SimpleObjectProperty<FilteredList<Player>>(
            new FilteredList<>(playersProperty.get()
            ));

      ObjectProperty<Predicate<? super Player>> filterProperty =
         viewablePlayersProperty.get().predicateProperty();

      VBox vbox = new VBox();
      vbox.setPadding( new Insets(10));
      vbox.setSpacing(4);

      HBox hbox = new HBox();
      hbox.setSpacing( 2 );

      ToggleGroup filterTG = new ToggleGroup();

      @SuppressWarnings("unchecked")
      EventHandler<ActionEvent> toggleHandler = (event) -> {
         ToggleButton tb = (ToggleButton)event.getSource();
         Predicate<Player> filter = (Predicate<Player>)tb.getUserData();
         filterProperty.set(filter);
      };

      ToggleButton tbShowAll = new ToggleButton("Show All");
      tbShowAll.setSelected(true);
      tbShowAll.setToggleGroup(filterTG);
      tbShowAll.setOnAction(toggleHandler);
      tbShowAll.setUserData((Predicate<Player>) (Player p) -> true);

      List<ToggleButton> tbs = Arrays.asList(players)
         .stream()
         .map((p) -> p.getTeam())
         .distinct()
         .map((team) -> {
            ToggleButton tb = new ToggleButton(team);
            tb.setToggleGroup(filterTG);
            tb.setOnAction(toggleHandler);
            tb.setUserData((Predicate<Player>) (Player p) -> team.equals(p.getTeam()));
            return tb;
         })
         .collect(Collectors.toList());

      hbox.getChildren().add(tbShowAll);
      hbox.getChildren().addAll(tbs);

      ListView<Player> lv = new ListView<>();
      lv.itemsProperty().bind(viewablePlayersProperty);

      vbox.getChildren().addAll(hbox, lv);

      Scene scene = new Scene(vbox);

      primaryStage.setScene(scene);
      primaryStage.setOnShown((evt) -> playersProperty.get().addAll(players));
      primaryStage.show();

   }

   public static void main(String[] args) {
      launch(args);
   }

   static class Player {
      private final String team;
      private final String playerName;

      public Player(String team, String playerName) {
         this.team = team;
         this.playerName = playerName;
      }

      public String getTeam() {
         return team;
      }

      public String getPlayerName() {
         return playerName;
      }

      @Override
      public String toString() {
         return playerName + " (" + team + ")";
      }

   }
}
