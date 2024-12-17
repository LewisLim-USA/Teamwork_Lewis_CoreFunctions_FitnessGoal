package com.example.teamworklewis.controller.form;

import com.example.teamworklewis.controller.service.UserController;
import com.example.teamworklewis.dto.UserDto;
import com.example.teamworklewis.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserControllerForm implements Initializable {

    List<User> listprofile = new ArrayList<>();
    List<User> readprofile = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToNutritionApp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/Nutrition.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToUserSetting(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/UserSetting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToForgotPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/ForgotPassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToProgressVisualization(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/teamworklewis/View/ProgressVisualization.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private ListView<String> listall;
    private ObservableList<String> msgData;
    @FXML
    private TextField txtsearchid;

    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtage;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtgender;




    @FXML
    protected void ListAllUsers(ActionEvent event) {
       deserialize_user_profile();
        msgData.clear();


        for (UserDto user : UserController.getInstance().getUser()) {
            msgData.add(user.toString());
        }

        // Ensure the ListView is updated with the new data
        listall.setItems(msgData);
        // Output the user information to the console


        /*
        UserController.getInstance().getAllUser();
        for (User user : UserController.getUserContainer()) {
            msgData.add(user.toString());
        }
*/

    }


    @FXML
    protected void SearchUser(ActionEvent event) {

        msgData.clear();
        UserDto s = UserController.getInstance().getUserById(Integer.parseInt(txtsearchid.getText()));
        if(s!= null)
        msgData.add(s.toString());
        else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("No userid found");
            a.showAndWait();
        }

    }

    @FXML
    protected void SaveUser(ActionEvent event) {
        String s = UserController.getInstance().addUser(new Object[] {txtusername.getText(), txtemail.getText(), Integer.parseInt(txtage.getText()), txtgender.getText(), Integer.parseInt(txtid.getText())});
        txtusername.clear();
        txtage.clear();
        txtgender.clear();
        txtemail.clear();
        txtid.clear();
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText(s);
        a.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        msgData = FXCollections.observableArrayList();
        listall.setItems(msgData);
    }

    public void serialize_user_profile() {

        User up1 = new User("sam", "sam@gmail.com", 24, "male",1 );
        User up2 = new User("robert", "robert@gmail.com", 34, "male",2 );
        User up3 = new User("nancy", "nancy@outlook.com", 25, "female",3 );


        listprofile.add(up1);
        listprofile.add(up2);
        listprofile.add(up3);




      //  }
        try ( // Create an output stream for file array.dat
              ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("array.dat", false));
        ) {
            // Write arrays to the object output stream
            for(int i = 0; i < 3; i++)
                output.writeObject(listprofile.get(i));


        } catch (IOException ex) {

        }


    }

    public void deserialize_user_profile() {
        //deserialize

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("array.dat"));

            User s;
            while (true) {
                try {
                    s = (User) ois.readObject();
                    readprofile.add(s);
                } catch (Exception e) {
                    break; }
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(readprofile.toString());

    }

}
