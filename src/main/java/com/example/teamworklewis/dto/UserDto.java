package com.example.teamworklewis.dto;

public class UserDto {

    private String username;
    private String email;
    private int id;

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public UserDto() {
    }

    public UserDto(int id,String username, String email ) {
        this.username = username;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
