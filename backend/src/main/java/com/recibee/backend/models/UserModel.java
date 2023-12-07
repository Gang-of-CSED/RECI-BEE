package com.recibee.backend.models;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class UserModel implements Serializable{
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

    @Override
    public String toString() {
        return "UserModel [username=" + username + ", password=" + password + ", name=" + name + "]";
    }
    
}
