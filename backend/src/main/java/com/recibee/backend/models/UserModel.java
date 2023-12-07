package com.recibee.backend.models;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class UserModel {
    private String username;
    private String password;
    private String name;

    private UserModel() {

    }

    public UserModel(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
