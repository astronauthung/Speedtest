package com.example.test_mang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DatabaseConnect extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Connect.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Network Speed Tracker");
        primaryStage.setScene(new Scene(root, 1300, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
