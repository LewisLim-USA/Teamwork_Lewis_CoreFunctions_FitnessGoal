package com.example.teamworklewis.controller.form;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HomepageController {

    @FXML
    private VBox sidebar;

    @FXML
    private Label headerLabel;

    @FXML
    private Button profileButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button customerSupportButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label userStatusLabel;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
        // Initialize the ComboBox with values
        typeComboBox.getItems().addAll("Run", "Step", "Gym", "Calories");

        // Set default values or perform any necessary setup
        userLabel.setText("User2");
        userStatusLabel.setText("Hope you are doing well");
    }

    @FXML
    private void handleProfileButton() {
        // Handle profile button action
        System.out.println("Profile button clicked");
    }

    @FXML
    private void handleSettingsButton() {
        // Handle settings button action
        System.out.println("Settings button clicked");
    }

    @FXML
    private void handleCustomerSupportButton() {
        // Handle customer support button action
        System.out.println("Customer Support button clicked");
    }

    @FXML
    private void handleSaveButton() {
        // Handle save button action
        String amount = amountField.getText();
        String type = typeComboBox.getValue();
        System.out.println("Saved: " + amount + " " + type);
    }
}