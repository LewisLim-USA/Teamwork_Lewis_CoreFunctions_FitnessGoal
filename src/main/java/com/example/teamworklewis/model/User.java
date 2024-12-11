package com.example.teamworklewis.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String email;
    private int age;
    private String gender;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public User(int id, String username, String email, int age, String gender) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.id = id;
    }

    public User() {
        this.username = "XXX";
        this.email = "email";
        this.age = 000;
        this.gender = "F";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
