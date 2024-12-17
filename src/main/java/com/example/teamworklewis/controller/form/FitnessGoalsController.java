package com.example.teamworklewis.controller.form;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FitnessGoalsController extends Application {

    @Override
    public void start(Stage primaryStage) {
        //图片
        ImageView imageView = new ImageView();
        Image fitnessImage = new Image("https://img.tukuppt.com/ad_preview/00/05/66/5c98dd70e65de.jpg!/fw/980");
        imageView.setImage(fitnessImage);
        imageView.setFitWidth(300);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0, 0, 4);" +
                "-fx-border-radius: 15px; -fx-background-radius: 15px;");

        VBox imageContainer = new VBox(imageView);
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPadding(new Insets(20));
        imageContainer.setStyle("-fx-background-color: #F0F0F0;");


        // 标题
        Label title = new Label("smart sports assistant");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#4CAF50"));


        TextField nameField = new TextField();
        nameField.setPromptText(" Enter your name");

        TextField ageField = new TextField();
        ageField.setPromptText(" Enter your age");

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll(" male", " Female");
        genderBox.setPromptText(" Select gender");

        TextField weightField = new TextField();
        weightField.setPromptText("Weight (kg)");

        TextField heightField = new TextField();
        heightField.setPromptText("Height(cm)");

        ComboBox<String> goalBox = new ComboBox<>();
        goalBox.getItems().addAll("lose weight", "keep fit", " keep healthy");
        goalBox.setPromptText("Select the target goal");

        VBox inputFields = new VBox(10, nameField, ageField, genderBox, weightField, heightField, goalBox);
        inputFields.setPadding(new Insets(20));


        Button generatePlanButton = new Button("give an opinion");
        generatePlanButton.setStyle(
                "-fx-background-color: #171616; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-padding: 10px;"
        );

        //显示结果
        TextArea resultArea = new TextArea();
        resultArea.setPromptText("Here will make a plan for you...");
        resultArea.setEditable(false);
        resultArea.setWrapText(true);

        VBox rightLayout = new VBox(15, title, inputFields, generatePlanButton, resultArea);
        rightLayout.setPadding(new Insets(20));
        rightLayout.setAlignment(Pos.TOP_CENTER);


        HBox mainLayout = new HBox(20, imageContainer, rightLayout);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #FFFFFF;");


        generatePlanButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String gender = genderBox.getValue();
                String goal = goalBox.getValue();

                String plan = generatePlan(name, age, weight, height, gender, goal);
                resultArea.setText(plan);
            } catch (NumberFormatException ex) {
                resultArea.setText("please enter a valid number！");
            }
        });


        Scene scene = new Scene(mainLayout, 800, 500);
        primaryStage.setTitle(" Smart Sport assistant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String generatePlan(String name, int age, double weight, double height, String gender, String goal) {
        double bmi = weight / Math.pow(height / 100, 2);
        String bmiCategory = (bmi < 18.5) ? "thin" :
                (bmi <= 24.9) ? "ok" :
                        (bmi <= 29.9) ? "overweight" :
                                "obese";


        StringBuilder plan = new StringBuilder();
        plan.append("according to your goal“").append(goal).append("”，give a plan  ：\n");
        switch (goal) {
            case "lose weight":
                plan.append("- Perform 4-5 aerobic exercises per week (e.g., running, cycling)\n")
                        .append("- Each session lasts 30-60 minutes\n")
                        .append("- Control daily calorie intake and avoid high-sugar, high-fat foods\n");
                break;
            case "keep fit":
                plan.append("- Perform 3-4 strength training sessions per week (e.g., squats, bench press)\n")
                        .append("- Each session lasts 45-60 minutes\n")
                        .append("- Increase protein intake and maintain a calorie surplus\n");
                break;
            case " keep healthy":
                plan.append("- Perform 3-4 moderate-intensity workouts per week (e.g., brisk walking, yoga)\n")
                        .append("- Maintain a balanced diet and ensure sufficient sleep\n");
                break;
        }
        return plan.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

