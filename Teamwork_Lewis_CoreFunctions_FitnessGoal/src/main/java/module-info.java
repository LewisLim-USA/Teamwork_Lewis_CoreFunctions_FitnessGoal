module com.example.week11_lab9_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.week11_lab9_javafx to javafx.fxml;
    exports com.example.week11_lab9_javafx;

    opens com.example.week11_lab9_javafx.controller.form to javafx.fxml;
    exports com.example.week11_lab9_javafx.controller.form;

}