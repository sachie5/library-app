package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserList {
    private List<User> users;
    private final Gson gson;
    private final Scanner scanner = new Scanner(System.in);

    public List<User> getUsers() {
        return users;
    }

    public UserList() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        users = usersFromJson("users.json");
    }

    public void createUser() {
        User newUser = new User();
        String userInput;
        System.out.println("Enter the name of the member you would like to use: ");
        userInput = scanner.nextLine();
        newUser.setName(userInput);

        System.out.println("Enter the username you would like to use: ");
        userInput = scanner.nextLine();
        newUser.setUsername(userInput);

        System.out.println("Enter the password you would like to use: ");
        userInput = scanner.nextLine();
        newUser.setPassword(userInput);
        newUser.setLoanedBooks(new ArrayList<>());

        users.add(newUser);
        String userJson = gson.toJson(newUser);
        saveToJsonFile("users.json");
        System.out.println("Your user profile has been created. Welcome, " + newUser.username);

    }

    public void saveToJsonFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                String usersJson = gson.toJson(users);
                writer.write(usersJson);
            }
        } catch (IOException e) {
            System.out.println("Error saving member to file: " + e.getMessage());
        }
    }

    public List<User> usersFromJson(String fileName) {
        List<User> users = null;
        File file = new File(fileName);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<User>>() {
                }.getType();
                users = gson.fromJson(reader, listType);
            } catch (IOException e) {
                System.err.println("Error loading information from file: " + e.getMessage());
            }
        }
        return users;
    }

    public void saveLoanBookToJson(Book loanedBook, String filename, String username) {

        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Sorry. this file can't be created:" + e.getMessage());
            }
        }

        List<User> users = usersFromJson(filename);
        User currentUser = null;
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                if (user.username.equals(username)) {
                    currentUser = user;
                }
            }
        }

        if (currentUser != null) {
            List<Book> loanedBooks = currentUser.getLoanedBooks();
            loanedBooks.add(loanedBook);

            try (FileWriter writer = new FileWriter(file)) {
                String usersJson = gson.toJson(users);
                writer.write(usersJson);
            } catch (IOException e) {
                System.err.println("Error loading information from file: " + e.getMessage());
            }
            System.out.println("You have now borrowed " + loanedBook + ".");
        } else {
            System.out.println("User with username: " + username + " not found.");
        }
    }

}




