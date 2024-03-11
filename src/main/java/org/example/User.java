package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;

public class User {
    String name;
    String username;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public User() {

    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User - " +
                "Name: " + name + '\'' +
                ", Username: " + username + '\'' +
                ", Password: " + password + '\'' +
                '.';
    }
}
