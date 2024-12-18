package com.example.teamworklewis.controller.form;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class WorkoutPlanController {
    @FXML
    private Label workoutPlanLabel; // Reference to the label in FXML
    @FXML
    private DatePicker datePicker; // Reference to the DatePicker in FXML
    @FXML
    private MenuButton workoutTypeMenu; // Reference to the MenuButton in FXML

    @FXML
    public void initialize() {
        // Initialization logic can go here
        workoutPlanLabel.setText("Select your workout plan");

        // Example of handling menu item selection
        for (MenuItem item : workoutTypeMenu.getItems()) {
            item.setOnAction(event -> {
                String selectedWorkout = item.getText();
                workoutPlanLabel.setText("Selected Workout: " + selectedWorkout);
            });
        }
    }

}