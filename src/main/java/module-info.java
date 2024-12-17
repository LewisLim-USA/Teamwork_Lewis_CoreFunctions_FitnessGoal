module com.example.teamworklewis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.json;


    opens com.example.teamworklewis to javafx.fxml;
    exports com.example.teamworklewis;

    opens com.example.teamworklewis.controller.form to javafx.fxml;
    exports com.example.teamworklewis.controller.form;
    exports com.example.teamworklewis.model;
    opens com.example.teamworklewis.model to javafx.fxml;

}