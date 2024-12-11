package com.example.teamworklewis.controller.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserSettingController {

    @FXML
    private Button GoToForgotPassword;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToUserSetting(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserSetting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToForgotPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/ForgotPasswordPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
