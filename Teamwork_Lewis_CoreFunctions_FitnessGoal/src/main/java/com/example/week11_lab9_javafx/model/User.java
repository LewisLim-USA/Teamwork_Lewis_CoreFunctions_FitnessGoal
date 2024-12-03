package com.example.week11_lab9_javafx.model;

import java.io.Serializable;

public class User implements Serializable { // to store objects
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String email;
    private int age;
    private String gender;

    public User(int id, String username, String email, int age, String gender) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;


    }

    @Override
    public String toString() {
        return "User{" +
                "age='" + age + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
