package org.example.lists;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.Book;
import org.example.Library;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoanedBooksList {

    private List<Book> loanedBooks;
    private final Gson gson;
    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public void setLoanedBooks(List<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public LoanedBooksList(){
        gson = new GsonBuilder().setPrettyPrinting().create();
        loanedBooks = loanedBooksFromJson();
        if (loanedBooks == null) {
            loanedBooks = new ArrayList<>();
        }
    }

    public List<Book> loanedBooksFromJson() {
        List<Book> books = null;
        File file = new File("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\loanedBooks.json");

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Book>>() {
                }.getType();
               books = gson.fromJson(reader, listType);
            } catch (IOException e) {
                System.err.println("Error loading information from file: " + e.getMessage());
            }
        }
        return books;
    }

    public void saveToJsonFile(String fileName) {
        try {

            File directory = new File("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data");

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
           try {
               loanedBooks.add(loanedBook);
               saveToJsonFile("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\loanedBooks.json");
               System.out.println("Book added to loaned list.");
           } catch (NullPointerException e){
               System.out.println(loanedBook);
               System.out.println("The book cannot be found.");
        }

    }
}
