package com.example.teamworklewis.controller.form;
import com.example.teamworklewis.model.Meal;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


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

    @FXML
    private Button backButton;



    @Override
    public void start(Stage primaryStage) throws Exception {

        //FMXL File
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/teamworklewis/View/nutrition.fxml"));
        Parent root = loader.load();  // This will load the FXML file

        System.out.println(getClass().getResource("/nutrition.fxml"));


        NutritionControllerForm controller = loader.getController();
        controller.initialize();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Bodega Fitness Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();

        //gymicon
        Image icon = new Image(getClass().getResource("/com/example/teamworklewis/View/gym icon.png").toExternalForm());
        primaryStage.getIcons().add(icon);

    }


    @FXML
    private void handleAddMeal() {
        String mealName = mealNameField.getText();
        LocalDate selectedDate = datePicker.getValue();
        if (!mealName.isEmpty()) {
            try {
                //Data from API
                Meal meal = fetchNutritionalData(mealName);
                if (meal != null) {
                    meal.setDate(selectedDate);
                    addMealToLog(selectedDate, meal);
                    saveMealsToFile();  // Save meals to file
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

        //Check the food got or not
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            // If the response code is not 200, return null (no meal found)
            showAlert("Error", "Failed to fetch nutritional data. Please check the meal name.");
            return null;
        }


        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Try parsing the JSON response
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



    private void updatePieChart(LocalDate selectedDate) {
        PieChart.Data calories = new PieChart.Data("Calories", dailyCalories);
        PieChart.Data protein = new PieChart.Data("Protein", dailyProtein);
        PieChart.Data fat = new PieChart.Data("Fat", dailyFat);
        PieChart.Data carbs = new PieChart.Data("Carbs", dailyCarbs);


        nutritionPieChart.getData().clear();
        nutritionPieChart.getData().addAll(calories, protein, fat, carbs);


        applyPieChartColors(nutritionPieChart);
    }

    @FXML
    private void handleDeleteMeal() {
        // Get selected meal from TableView
        Meal selectedMeal = mealTable.getSelectionModel().getSelectedItem();

        if (selectedMeal != null) {
            LocalDate selectedDate = datePicker.getValue();
            // Remove the selected meal from the mealLog
            List<Meal> mealsForDay = mealLog.get(selectedDate);
            if (mealsForDay != null) {
                mealsForDay.remove(selectedMeal);
                // If there are no more meals for the day, remove the date from the mealLog
                if (mealsForDay.isEmpty()) {
                    mealLog.remove(selectedDate);
                }
            }

            // Save updated meal log to file
            saveMealsToFile();

            // Update the table and nutritional summaries
            updateMealTable(selectedDate);
            updateNutritionalSummaries(selectedDate);
            updatePieChart(selectedDate);
        } else {
            showAlert("Selection Error", "Please select a meal to delete.");
        }
    }




    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("warning");
        alert.setHeaderText(null);
        alert.setContentText(message);


        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

// Correctly load the image from the resources folder
        Image errorIcon = new Image(getClass().getResource("/com/example/teamworklewis/View/error icon.png").toExternalForm());

        stage.getIcons().add(errorIcon);
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
                    writer.newLine();  //
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

        // Set the image for the back button
        Image backIcon = new Image(getClass().getResource("/com/example/teamworklewis/View/109618.png").toExternalForm());
        ImageView imageView = new ImageView(backIcon);


        imageView.setFitWidth(40);
        imageView.setFitHeight(35);


        backButton.setGraphic(imageView);
    }


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void SwitchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}