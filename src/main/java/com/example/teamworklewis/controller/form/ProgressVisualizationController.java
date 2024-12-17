package com.example.teamworklewis.controller.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.io.IOException;

public class ProgressVisualizationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button backToHome;

    @FXML
    private PieChart pieChart;
    public void initialize() {
        // 示例数据：卡路里摄入量和卡路里燃烧量
        int caloriesConsumed = 2200;
        int caloriesBurned = 1800;

        // 创建饼图数据
        PieChart.Data consumedData = new PieChart.Data("Calories Consumed", caloriesConsumed);
        PieChart.Data burnedData = new PieChart.Data("Calories Burned", caloriesBurned);

        // 添加数据到饼图
        //PieChart.getData().clear();
        //PieChart.getData().addAll(consumedData, burnedData);

    }



}




