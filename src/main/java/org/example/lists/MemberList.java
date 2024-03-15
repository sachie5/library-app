package org.example.lists;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.Book;
import org.example.user.Member;
import org.h2.engine.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberList {
    private List<Member> members;
    private final Gson gson;
    private final Scanner scanner = new Scanner(System.in);

    public List<Member> getMembers() {
        return members;
    }

    public MemberList() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        members = usersFromJson("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\members.json");
    }

    public void createUser() {
        Member newUser = new Member();
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

        members.add(newUser);
        String userJson = gson.toJson(newUser);
        saveToJsonFile("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\members.json");
        System.out.println("Your user profile has been created. Welcome, " + newUser.getUsername());

    }

    public void saveToJsonFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                String usersJson = gson.toJson(members);
                writer.write(usersJson);
            }
        } catch (IOException e) {
            System.out.println("Error saving member to file: " + e.getMessage());
        }
    }

    public List<Member> usersFromJson(String fileName) {
        List<Member> users = null;
        File file = new File(fileName);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Member>>() {
                }.getType();
                users = gson.fromJson(reader, listType);
            } catch (IOException e) {
                System.err.println("Error loading information from file: " + e.getMessage());
            }
        }
        return users;
    }

    public void saveLoanBookToJson(Book loanedBook, String username) {

        File file = new File("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\members.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Sorry. this file can't be created:" + e.getMessage());
            }
        }

        List<Member> users = usersFromJson("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\members.json");
        Member currentUser = null;
        if (users != null && !users.isEmpty()) {
            for (Member member : users) {
                if (member.getUsername().equals(username)) {
                    currentUser = member;
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