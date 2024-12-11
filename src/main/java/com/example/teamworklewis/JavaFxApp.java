package com.example.teamworklewis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFxApp extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        try {
            // Ensure the path to the FXML file is correct
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserSetting.fxml"));
            stage.setTitle("Fitness Tracking Application");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}