module com.example.teamworklewis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.teamworklewis to javafx.fxml;
    exports com.example.teamworklewis;

    opens com.example.teamworklewis.controller.form to javafx.fxml;
    exports com.example.teamworklewis.controller.form;

}