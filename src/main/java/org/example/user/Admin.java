package org.example.user;

public class Admin {

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

    public Admin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin -" +
                "Name: " + name +
                ", Username: " + username +
                ", Password: " + password +
                ".";
    }
}
