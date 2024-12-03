package com.example.week11_lab9_javafx.controller.form;

import com.example.week11_lab9_javafx.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



    private List<User> listprofile = new ArrayList<>(); // grow and shrink
    private List<User> readprofile = new ArrayList<>(); // user objects

    @FXML
    private ListView<String> lstUsers;
    private ObservableList<String> msgData;

    @FXML
    protected void ListAllUsers(ActionEvent event) { // List User box in Gluon
        msgData.clear();

        for (User user : readprofile) {
            msgData.add(user.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //serialize_user_profile();
        deserialize_user_profile();
        msgData = FXCollections.observableArrayList();
        lstUsers.setItems(msgData); //binding automatically, not called by programs
    }

    public void serialize_user_profile() {
        try (
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("array.dat", false))
        ) {

            User up1 = new User(1, "sam", "sam@gmail.com", 24, "Male");
            User up2 = new User(2, "sam", "sam@gmail.com", 34, "Female");
            User up3 = new User(3, "sam", "sam@gmail.com", 42, "Male");

            listprofile.add(up1);
            listprofile.add(up2);
            listprofile.add(up3);

            for (int i = 0; i < listprofile.size(); i++) {
                output.writeObject(listprofile.get(i));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deserialize_user_profile() { // to read from printwriter file
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("array.dat"));

            User s;
            while (true) {
                try {
                    s = (User) input.readObject();
                    readprofile.add(s);
                } catch (Exception e) {
                    break;
                }

                // object file read, instead of a marker to end
            }
            input.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(readprofile.toString());
    }
}
