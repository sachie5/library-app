package org.example.lists;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.user.Admin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminList {

    private List<Admin> admins;
    private Gson gson;
    private Scanner scanner = new Scanner(System.in);
    public List<Admin> getAdmins() {
        return admins;
    }

    public AdminList(){
        gson = new GsonBuilder().setPrettyPrinting().create();
        admins = adminFromJson();
        if(admins == null){
            admins = new ArrayList<>();
            Admin newAdmin = new Admin("Sacha M", "admin1", "Adminpass1");
            admins.add(newAdmin);
        }
    }

    public void saveToJsonFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                String adminJson = gson.toJson(admins);
                writer.write(adminJson);
            }
        } catch (IOException e) {
            System.out.println("Error saving admin to file: " + e.getMessage());
        }
    }

    public void createAdmin() {
        Admin newAdmin = new Admin();
        String userInput;
        System.out.println("Enter the name of the member you would like to use: ");
        userInput = scanner.nextLine();
        newAdmin.setName(userInput);

        System.out.println("Enter the username you would like to use: ");
        userInput = scanner.nextLine();
        newAdmin.setUsername(userInput);

        System.out.println("Enter the password you would like to use: ");
        userInput = scanner.nextLine();
        newAdmin.setPassword(userInput);

        admins.add(newAdmin);
        String userJson = gson.toJson(newAdmin);
        saveToJsonFile("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\admin.json");
        System.out.println("Your user profile has been created. Welcome, " + newAdmin.getUsername());

    }

    protected List<Admin> adminFromJson() {
        File file = new File("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\admin.json");

        if(file.exists()){
            try(FileReader reader = new FileReader(file)){
                Type listType = new TypeToken<List<Admin>>() { }.getType();
                admins = gson.fromJson(reader, listType);
            } catch (IOException e){
                System.err.println("Error loading information from file: " + e.getMessage());
            }
        }
        return admins;
    }

}
