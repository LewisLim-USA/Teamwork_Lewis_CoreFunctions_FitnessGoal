package com.example.teamworklewis.controller.form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgressVisualizationController {

    //To switch to UserProfile
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
    private PieChart nutritionPieChart;

    // 定义变量
    private int caloriesBurned = 0; // 燃烧量
    private int dailyCalories = 0; // 摄入量

    // 更新卡路里燃烧量
    public void updateCaloriesBurned(int burned) {
        caloriesBurned += burned; // 更新燃烧量
        updatePieChart(); // 刷新饼图
    }

    // 更新饼图
    public void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Calories Burned", caloriesBurned),
                new PieChart.Data("Calories Consumed", dailyCalories)
        );
        nutritionPieChart.setData(pieChartData);
    }

    // 示例：初始化饼图数据
    public void initializeChart() {
        nutritionPieChart.setTitle("Daily Calorie Balance");
        updatePieChart(); // 初始化时设置数据
    }


}
