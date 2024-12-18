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
    private PieChart pieChart; // 饼图对象

    @FXML
    private TextField caloriesBurned; // TextField：输入消耗卡路里
    @FXML
    private TextField calorieConsumption; // TextField：输入摄入卡路里

    @FXML
    private Button updateChartButton; // 按钮：更新饼图

    /**
     * 切换到用户个人资料界面
     */
    public void SwitchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 初始化方法
     */
    public void initialize() {
        // 初始化饼图为空
        pieChart.getData().clear();

        // 为按钮绑定事件
        updateChartButton.setOnAction(this::updatePieChart);
    }

    /**
     * 更新饼图数据
     */
    private void updatePieChart(ActionEvent event) {
        try {
            // 获取用户输入的卡路里数据
            int consumed = Integer.parseInt(calorieConsumption.getText());
            int burned = Integer.parseInt(caloriesBurned.getText());

            // 清空饼图原有数据
            pieChart.getData().clear();

            // 创建饼图数据
            PieChart.Data consumedData = new PieChart.Data("Calories Consumed", consumed);
            PieChart.Data burnedData = new PieChart.Data("Calories Burned", burned);

            // 添加数据到饼图
            pieChart.getData().addAll(consumedData, burnedData);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for calories.");
        }
    }
}
