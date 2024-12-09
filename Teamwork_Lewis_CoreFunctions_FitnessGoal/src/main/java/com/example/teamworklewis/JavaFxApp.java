package com.example.teamworklewis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFxApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Userform.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // Method to load a new scene
    public static void loadScene(String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApp.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
