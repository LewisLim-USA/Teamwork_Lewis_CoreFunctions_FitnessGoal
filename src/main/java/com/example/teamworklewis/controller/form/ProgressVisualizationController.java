package com.example.teamworklewis.controller.form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class ProgressVisualizationController {
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
