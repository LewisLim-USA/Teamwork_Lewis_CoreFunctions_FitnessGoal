package com.example.teamworklewis.controller.form;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.time.LocalDate;


public class WorkoutPlansControllerForm extends Application {

    @FXML
    private DatePicker DatePickerwk;

    @FXML
    private MenuButton WorkoutType;

    @FXML
    private MenuButton IntensityType;

    @FXML
    private TableView<WorkoutLog> workoutplantable;

    @FXML
    private TableColumn<WorkoutLog, LocalDate> DateColumn;

    @FXML
    private TableColumn<WorkoutLog, String> TypeWkColumn;

    @FXML
    private TableColumn<WorkoutLog, String> IntensityColumn;

    @FXML
    private TableColumn<WorkoutLog, Integer> CaloriesBurntColumn;

    private ObservableList<WorkoutLog> workoutLogs = FXCollections.observableArrayList();

    private String selectedWorkoutType = "";
    private String selectedIntensity = "";

    @FXML
    public void initialize() {
        // Initialize the columns
        DateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        TypeWkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkoutType()));
        IntensityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntensity()));
        CaloriesBurntColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCaloriesBurnt()).asObject());

        // Set the items in the TableView
        workoutplantable.setItems(workoutLogs);

        // Set up menu button actions
        setupMenuButtons();
    }

    private void setupMenuButtons() {
        // Set up Workout Type MenuButton
        for (MenuItem item : WorkoutType.getItems()) {
            item.setOnAction(event -> {
                selectedWorkoutType = item.getText();
                WorkoutType.setText(selectedWorkoutType); // Update the button text
            });
        }

        // Set up Intensity Type MenuButton
        for (MenuItem item : IntensityType.getItems()) {
            item.setOnAction(event -> {
                selectedIntensity = item.getText();
                IntensityType.setText(selectedIntensity); // Update the button text
            });
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        // Set the scene
        primaryStage.setTitle("Workout Plan");
        primaryStage.setScene(new Scene(root, 738, 729));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //The Add WorkoutLog2
    @FXML
    public void handleAddLog() {
        LocalDate selectedDate = DatePickerwk.getValue();
        String selectedWorkoutType = WorkoutType.getText();
        String selectedIntensity = IntensityType.getText();
        int caloriesBurnt = calculateCaloriesBurnt(selectedWorkoutType, selectedIntensity); // Implement this method based on your logic

        if (selectedDate != null && !selectedWorkoutType.equals("Choose Workout Type") && !selectedIntensity.equals("Choose Intensity")) {
            WorkoutLog newLog = new WorkoutLog(selectedDate, selectedWorkoutType, selectedIntensity, caloriesBurnt);
            workoutLogs.add(newLog);
        } else {
            // Show an error message if the input is invalid
            showAlert("Invalid Input", "Please select a date, workout type, and intensity.");
        }
    }

    private int calculateCaloriesBurnt(String workoutType, String intensity) {
        // Base calories burnt for each workout type at "Easy" intensity
        int baseCalories;

        switch (workoutType) {
            case "Swimming":
                baseCalories = 330;
                break;
            case "Push-ups":
                baseCalories = 210;
                break;
            case "Walking":
                baseCalories = 150;
                break;
            case "Step-up & Step-downs":
                baseCalories = 216;
                break;
            case "Jumping Jacks":
                baseCalories = 155;
                break;
            case "Crunches":
                baseCalories = 167;
                break;
            case "Burpee":
                baseCalories = 240;
                break;
            case "Squats":
                baseCalories = 147;
                break;
            default:
                baseCalories = 0; // Default to 0 if workout type is not recognized
                break;
        }

        // Adjust calories based on intensity
        int additionalCalories = 0;
        switch (intensity) {
            case "Moderately Easy":
                additionalCalories = 5;
                break;
            case "Medium":
                additionalCalories = 10;
                break;
            case "Moderately Medium":
                additionalCalories = 20;
                break;
            case "Hard":
                additionalCalories = 25;
                break;
            default:
                // No additional calories for "Easy" intensity
                break;
        }

        return baseCalories + additionalCalories;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleDeleteLog() {
        WorkoutLog selectedLog = workoutplantable.getSelectionModel().getSelectedItem();
        if (selectedLog != null) {
            workoutLogs.remove(selectedLog);
        } else {
            showAlert("No Selection", "Please select a log to delete.");
        }
    }




}
