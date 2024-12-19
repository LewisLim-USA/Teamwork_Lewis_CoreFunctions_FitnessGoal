package com.example.teamworklewis.controller.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class ProgressVisualizationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backToHome;

    @FXML
    private PieChart pieChart;

    @FXML
    private TextField caloriesBurned;

    @FXML
    private TextField calorieConsumption;

    @FXML
    private Button updateChartButton;


    private static final String DATA_FILE = "piechart_data.txt";



    public void SwitchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void initialize() {

        pieChart.getData().clear();

        updateChartButton.setOnAction(this::updatePieChart);

    }

    private void updatePieChart(ActionEvent event) {

        try {
            int consumed = Integer.parseInt(calorieConsumption.getText());
            int burned = Integer.parseInt(caloriesBurned.getText());

            pieChart.getData().clear();

            PieChart.Data consumedData = new PieChart.Data("Calories Consumed", consumed);
            PieChart.Data burnedData = new PieChart.Data("Calories Burned", burned);

            pieChart.getData().addAll(consumedData, burnedData);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for calories.");
        }
    }




}
