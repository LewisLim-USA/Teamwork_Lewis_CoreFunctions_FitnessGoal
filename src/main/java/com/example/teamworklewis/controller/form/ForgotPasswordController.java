package com.example.teamworklewis.controller.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ForgotPasswordController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button btnGoToPage2; // fx:id="btnGoToPage2"

    @FXML
    private void handleGoToPage2(ActionEvent event) throws Exception {
        // Load the second FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
    }
}