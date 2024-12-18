package com.example.teamworklewis.controller.form;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorkoutPlansControllerForm extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamworklewis/View/WorkoutPlan-view.fxml"));
        Parent root = loader.load();

        // Set the scene
        primaryStage.setTitle("Workout Plan");
        primaryStage.setScene(new Scene(root, 738, 729));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
