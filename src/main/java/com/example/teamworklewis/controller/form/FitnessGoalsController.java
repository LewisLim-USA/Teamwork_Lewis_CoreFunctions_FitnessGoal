package com.example.teamworklewis.controller.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class FitnessGoalsController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private ComboBox<String> genderBox;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private ComboBox<String> goalBox;
    @FXML
    private TextArea resultArea;
    @FXML
    private Button generatePlanButton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToUserHomepage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserHomepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {

        genderBox.getItems().addAll("Male", "Female");


        goalBox.getItems().addAll("Lose weight", "Keep fit", "Keep healthy");


        generatePlanButton.setOnAction(event -> generatePlan());
    }

    private void generatePlan() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            String gender = genderBox.getValue();
            String goal = goalBox.getValue();

            if (gender == null || goal == null) {
                resultArea.setText("Please select both gender and goal.");
                return;
            }

            String plan = createPlan(name, age, weight, height, gender, goal);
            resultArea.setText(plan);
        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter valid numbers for age, weight, and height.");
        }
    }

    private String createPlan(String name, int age, double weight, double height, String gender, String goal) {
        double bmi = weight / Math.pow(height / 100, 2);
        String bmiCategory = (bmi < 18.5) ? "thin" :
                (bmi <= 24.9) ? "ok" :
                        (bmi <= 29.9) ? "overweight" : "obese";

        StringBuilder plan = new StringBuilder();
        plan.append("Hello ").append(name).append(", according to your goal '").append(goal).append("', here is a plan:\n");
        switch (goal) {
            case "Lose weight":
                plan.append("- Perform 4-5 aerobic exercises per week (e.g., running, cycling)\n")
                        .append("- Each session lasts 30-60 minutes\n")
                        .append("- Control daily calorie intake and avoid high-sugar, high-fat foods\n");
                break;
            case "Keep fit":
                plan.append("- Perform 3-4 strength training sessions per week (e.g., squats, bench press)\n")
                        .append("- Each session lasts 45-60 minutes\n")
                        .append("- Increase protein intake and maintain a calorie surplus\n");
                break;
            case "Keep healthy":
                plan.append("- Perform 3-4 moderate-intensity workouts per week (e.g., brisk walking, yoga)\n")
                        .append("- Maintain a balanced diet and ensure sufficient sleep\n");
                break;
        }
        plan.append("Your BMI is ").append(String.format("%.2f", bmi)).append(" (").append(bmiCategory).append(").");
        return plan.toString();
    }
}