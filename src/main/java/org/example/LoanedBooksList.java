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

public class LoanedBooksList {

    private List<Book> loanedBooks;
    private final Gson gson;
    private Library library;
    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public void setLoanedBooks(List<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public LoanedBooksList(){
        gson = new GsonBuilder().setPrettyPrinting().create();
        loanedBooks = loanedBooksFromJson("loanedBooks.json");
        if (loanedBooks == null) {
            loanedBooks = new ArrayList<>();
        }
    }

    public List<Book> loanedBooksFromJson(String fileName) {
        List<Book> loanedBooks = null;
        File file = new File(fileName);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Book>>() {
                }.getType();
                loanedBooks = gson.fromJson(reader, listType);
            } catch (IOException e) {
                System.err.println("Error loading information from file: " + e.getMessage());
            }
        }
        return loanedBooks;
    }

    public void saveToJsonFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                String booksJson = gson.toJson(loanedBooks);
                writer.write(booksJson);
            }
        } catch (IOException e) {
            System.out.println("Error saving member to file: " + e.getMessage());
        }
    }

    public void saveLoanBookToJson(Book loanedBook) {
        loanedBooksFromJson("loanedBooks.json");
           try {
               loanedBooks.add(loanedBook);
               saveToJsonFile("loanedBooks.json");
               System.out.println("Book added to loaned list.");
           } catch (NullPointerException e){
               System.out.println(loanedBook);
               System.out.println("The book cannot be found.");
        }

    }
}
