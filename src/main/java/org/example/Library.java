package org.example;

import org.example.data.Commands;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    JsonLibrary jsonLibrary = new JsonLibrary();
    Commands commands = new Commands();
    Scanner scanner = new Scanner(System.in);
    String chosenBook;

    String typeOfVisitor;
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
                typeOfVisitor = "visitor";
                visitor();
                break;
            case 2:
                typeOfVisitor = "admin";
                admin();
                break;
            case 3:
                typeOfVisitor = "user";
                user();
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void visitor(){
        System.out.println(Arrays.toString(commands.getVisitorCommands()));
        int userInput = scanner.nextInt();
        switch(userInput){
            case 1:
                System.out.println(books);
                visitor();
                break;
            case 2:
                findBooks();
                break;
            case 3:
                System.out.println(Arrays.toString(commands.getEntryCommands()));
                break;
            default:
                System.out.println("Your input is invalid. Please try again.");
        }
    }
    public void user(){
        System.out.println(Arrays.toString(commands.getUserCommands()));
        int userInput = scanner.nextInt();
        switch(userInput){
            case 1:
                System.out.println(books);
                break;
            case 2:
                findBooks();
                break;
            case 3:
                System.out.println(Arrays.toString(commands.getEntryCommands()));
                break;
            default:
                System.out.println("Your input is invalid. Please try again.");
        }
    }

    public void admin(){
        System.out.println(Arrays.toString(commands.getAdminCommands()));
    }

    public void findBooks(){
        System.out.println("Please enter the title of the book you would like to borrow.");
        String userInput = scanner.next();
        chosenBook = books.stream().filter(book -> book.getTitle().toLowerCase().equals(userInput.toLowerCase())).collect(Collectors.toList()).toString();
        switch(typeOfVisitor){
            case "visitor":
                readBook();
                break;
            case "admin":
                break;
            case "user":
                loanBooks();
                break;
        }

    }


    public void readBook(){
        System.out.println("You are currently reading " + chosenBook + ". Enter done when completed reading to return to shelf.");
        String userInput = scanner.next();
        if(userInput.toLowerCase().equals("done")){
            System.out.println(chosenBook + "has been returned.");
            visitor();
        } else {
            System.out.println();
        }
    }
    public void loanBooks(){
        System.out.println("Is this the book you would like to borrow? Y/N");
        String userInput = scanner.next();
        if(userInput.toLowerCase().equals("y")){
            System.out.println("You have now loaned " + chosenBook + ".");
        } else if(userInput.toLowerCase().equals("n")){
            System.out.println();
        }
    }
}
