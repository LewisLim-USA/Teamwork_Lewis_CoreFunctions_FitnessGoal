package com.example.teamworklewis.controller.form;

import com.example.teamworklewis.model.Meal;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class NutritionControllerForm extends Application {

    @FXML
    private TextField mealNameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Meal> mealTable;
    @FXML
    private TableColumn<Meal, String> mealColumn;
    @FXML
    private TableColumn<Meal, Integer> caloriesColumn, proteinColumn, fatColumn, carbsColumn;
    @FXML
    private Label totalCaloriesLabel, totalProteinLabel, totalFatLabel, totalCarbsLabel;
    @FXML
    private PieChart nutritionPieChart;
    @FXML
    private Button backButton;

    private final ObservableList<Meal> mealList = FXCollections.observableArrayList();
    private final Map<LocalDate, List<Meal>> mealLog = new HashMap<>();
    private static final String FILE_PATH = "meal_log.txt";

    private int dailyCalories = 0;
    private int dailyProtein = 0;
    private int dailyFat = 0;
    private int dailyCarbs = 0;

    private static final String API_URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";
    private static final String APP_ID = "523dbe76";
    private static final String API_KEY = "20992bcfbe7d9491d9e0d94f72eba79b";






    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamworklewis/View/nutrition.fxml"));
        Parent root = loader.load();
        NutritionControllerForm controller = loader.getController();
        controller.initialize();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Bodega Fitness Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleAddMeal() {
        String mealName = mealNameField.getText();
        LocalDate selectedDate = datePicker.getValue();
        if (!mealName.isEmpty()) {
            try {
                Meal meal = fetchNutritionalData(mealName);
                if (meal != null) {
                    meal.setDate(selectedDate);
                    addMealToLog(selectedDate, meal);
                    saveMealsToFile();
                    updateMealTable(selectedDate);
                    updateNutritionalSummaries(selectedDate);
                    updatePieChart(selectedDate);
                } else {
                    showAlert("Error", "Could not fetch nutritional data for the entered food.");
                }
            } catch (IOException e) {
                showAlert("Error", "Failed to connect to the API.");
                e.printStackTrace();
            }
        } else {
            showAlert("Input Error", "Please enter a meal name.");
        }
    }

    private Meal fetchNutritionalData(String mealName) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("x-app-id", APP_ID);
        connection.setRequestProperty("x-app-key", API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInputString = "{\"query\": \"" + mealName + "\"}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            return null;
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return parseMealData(response.toString());
        }
    }

    private Meal parseMealData(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        if (json.has("foods") && json.getJSONArray("foods").length() > 0) {
            JSONObject food = json.getJSONArray("foods").getJSONObject(0);
            String name = food.getString("food_name");
            int calories = food.optInt("nf_calories", 0);
            int protein = food.optInt("nf_protein", 0);
            int fat = food.optInt("nf_total_fat", 0);
            int carbs = food.optInt("nf_total_carbohydrate", 0);
            return new Meal(name, calories, protein, fat, carbs);
        }
        return null;
    }

    private void addMealToLog(LocalDate date, Meal meal) {
        mealLog.putIfAbsent(date, new ArrayList<>());
        mealLog.get(date).add(meal);
    }

    private void updateMealTable(LocalDate selectedDate) {
        List<Meal> mealsForDay = mealLog.getOrDefault(selectedDate, new ArrayList<>());
        mealList.setAll(mealsForDay);
        mealTable.setItems(mealList);
    }

    private void updateNutritionalSummaries(LocalDate selectedDate) {
        dailyCalories = 0;
        dailyProtein = 0;
        dailyFat = 0;
        dailyCarbs = 0;

        List<Meal> mealsForDay = mealLog.getOrDefault(selectedDate, new ArrayList<>());
        for (Meal meal : mealsForDay) {
            dailyCalories += meal.getCalories();
            dailyProtein += meal.getProtein();
            dailyFat += meal.getFat();
            dailyCarbs += meal.getCarbs();
        }

        totalCaloriesLabel.setText("Total Calories: " + dailyCalories + "cal");
        totalProteinLabel.setText("Total Protein: " + dailyProtein + "g");
        totalFatLabel.setText("Total Fat: " + dailyFat + "g");
        totalCarbsLabel.setText("Total Carbs: " + dailyCarbs + "g");
    }

    private void updatePieChart(LocalDate selectedDate) {
        PieChart.Data calories = new PieChart.Data("Calories", dailyCalories);
        PieChart.Data protein = new PieChart.Data("Protein", dailyProtein);
        PieChart.Data fat = new PieChart.Data("Fat", dailyFat);
        PieChart.Data carbs = new PieChart.Data("Carbs", dailyCarbs);


        nutritionPieChart.getData().clear();
        nutritionPieChart.getData().addAll(calories, protein, fat, carbs);


        applyPieChartColors(nutritionPieChart);
    }


    private void applyPieChartColors(PieChart pieChart) {
        //Piechart colour
        String[] sliceColors = {"#53a6ea", "#a133c3", "#ff3333", "#da9927"};


        for (int i = 0; i < pieChart.getData().size(); i++) {
            PieChart.Data data = pieChart.getData().get(i);
            String color = sliceColors[i % sliceColors.length];


            data.getNode().setStyle("-fx-pie-color: " + color + ";");


            Node legendItemSymbol = pieChart.lookup(".chart-legend-item:nth-child(" + (i + 1) + ") .chart-legend-symbol");
            if (legendItemSymbol != null) {
                legendItemSymbol.setStyle("-fx-background-color: " + color + ";");
            }
        }
    }



    // Helper method to convert color to hex string for CSS styling
    private String toHexString(javafx.scene.paint.Color color) {
        return String.format("#%02x%02x%02x", (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255));
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveMealsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<LocalDate, List<Meal>> entry : mealLog.entrySet()) {
                LocalDate date = entry.getKey();
                for (Meal meal : entry.getValue()) {
                    writer.write(date.toString() + "," + meal.getMealName() + "," +
                            meal.getCalories() + "," + meal.getProtein() + "," +
                            meal.getFat() + "," + meal.getCarbs());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMealsFromFile() {
        mealLog.clear();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] mealData = line.split(",");
                    if (mealData.length == 6) {
                        LocalDate date = LocalDate.parse(mealData[0]);
                        String mealName = mealData[1];
                        int calories = Integer.parseInt(mealData[2]);
                        int protein = Integer.parseInt(mealData[3]);
                        int fat = Integer.parseInt(mealData[4]);
                        int carbs = Integer.parseInt(mealData[5]);

                        Meal meal = new Meal(mealName, calories, protein, fat, carbs);
                        meal.setDate(date);
                        addMealToLog(date, meal);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void initialize() {
        mealColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMealName()));
        caloriesColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCalories()).asObject());
        proteinColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProtein()).asObject());
        fatColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFat()).asObject());
        carbsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCarbs()).asObject());


        datePicker.setValue(LocalDate.now());


        loadMealsFromFile();
        updateMealTable(datePicker.getValue());
        updateNutritionalSummaries(datePicker.getValue());
        updatePieChart(datePicker.getValue());


        datePicker.setOnAction(event -> {
            LocalDate selectedDate = datePicker.getValue();
            updateMealTable(selectedDate);
            updateNutritionalSummaries(selectedDate);
            updatePieChart(selectedDate);
        });
    }

        @FXML
    public void handleBackButton() {
        // Logic for the back button
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleDeleteMeal() {
        Meal selectedMeal = mealTable.getSelectionModel().getSelectedItem();

        if (selectedMeal != null) {
            LocalDate selectedDate = datePicker.getValue();
            List<Meal> mealsForDay = mealLog.get(selectedDate);
            if (mealsForDay != null) {
                mealsForDay.remove(selectedMeal);
                if (mealsForDay.isEmpty()) {
                    mealLog.remove(selectedDate);
                }
            }


            saveMealsToFile();
            updateMealTable(selectedDate);
            updateNutritionalSummaries(selectedDate);
            updatePieChart(selectedDate);
        } else {
            showAlert("Selection Error", "Please select a meal to delete.");
        }
    }


}
