package com.example.teamworklewis.controller.form;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WorkoutCalender extends Application {
    @Override

    public void start(Stage stage) {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        DatePickerSkin date = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node content = date.getPopupContent();
        pane.add(content, 0, 0);
        Scene scene = new Scene(pane, 250, 250);
        stage.setTitle("JavaFX Calendar");
        stage.setScene(scene);

        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
