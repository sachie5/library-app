package org.example;

import org.example.data.Commands;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;

public class Library {
    JsonLibrary jsonLibrary = new JsonLibrary();
    Commands commands = new Commands();
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Book> books = jsonLibrary.getListData();
    // by id of book
    private Map<Integer, Book> idBook = new HashMap<>();
    // by name of author
    private Map<String, List<Book>> authorBook = new HashMap<>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public Map<Integer, Book> getIdBook() {
        return idBook;
    }

    public void setIdBook(Map<Integer, Book> idBook) {
        this.idBook = idBook;
    }

    public Map<String, List<Book>> getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(Map<String, List<Book>> authorBook) {
        this.authorBook = authorBook;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void openLibrary() throws JSONException, IOException {
        System.out.println("Welcome to the Library! Please select one of the following options.");
        jsonLibrary.createListOfBooks();
        System.out.println(Arrays.toString(commands.getEntryCommands()));
        int userInput = scanner.nextInt();
        switch(userInput){
            case 1:
                System.out.println(Arrays.toString(commands.getVisitorCommands()));
            case 2:
                System.out.println(Arrays.toString(commands.getAdminCommands()));
        }
    }
}
