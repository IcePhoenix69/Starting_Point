package it.cineca.hr.desktop_application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ButtonExample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("Click me");
        button.setOnAction(e -> {
            System.out.println("Button clicked!");
        });
        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Button");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

