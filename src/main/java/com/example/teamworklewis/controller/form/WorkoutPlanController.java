package com.example.teamworklewis.controller.form;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WorkoutPlanController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}