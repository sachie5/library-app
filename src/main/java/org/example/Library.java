package org.example;

import org.example.lists.AdminList;
import org.example.lists.LoanedBooksList;
import org.example.lists.MemberList;
import org.example.user.Admin;
import org.example.user.Member;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final JsonLibrary jsonLibrary = new JsonLibrary();
    private MemberList memberList = new MemberList();
    private AdminList adminList = new AdminList();
    private LoanedBooksList loanedBooksList = new LoanedBooksList();
    private final Commands commands = new Commands();
    private static final Scanner scanner = new Scanner(System.in);
    private List <Member> members = memberList.getMembers();
    private List<Book> loanedBooks = loanedBooksList.getLoanedBooks();
    private List <Admin> admins = adminList.getAdmins();
    private Book chosenBook;
    private String typeOfVisitor;
    private Member loggedInMember;
    private Admin loggedInAdmin;
    private ArrayList<Book> books = jsonLibrary.getListData();

    public void openLibrary() throws JSONException, IOException {
        System.out.println("Welcome to the Library! Please select one of the following options.");
        jsonLibrary.createListOfBooks();
        System.out.println(Arrays.toString(commands.getEntryCommands()));

        try{
            int userInput = scanner.nextInt();
            switch(userInput){
                case 1:
                    typeOfVisitor = "visitor";
                    visitor();
                    break;
                case 2:
                    typeOfVisitor = "admin";
                    adminLogin();
                    break;
                case 3:
                    typeOfVisitor = "member";
                    memberLogin();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    openLibrary();
            }
        } catch (InputMismatchException e){
            System.out.println("Incorrect input. Please try again.");
            scanner.nextLine();
            openLibrary();
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
                    memberList.createUser();
                    memberLogin();
                    break;
                case 4:
                    System.out.println("Thank you for visiting!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Your input is invalid. Please try again.");

            }
    }


    public void member(){
        System.out.println(Arrays.toString(commands.getUserCommands()));
        int userInput = scanner.nextInt();
        switch(userInput){
            case 1:
                System.out.println(books);
                member();
                break;
            case 2:
                findBooks();
                break;
            case 3:
                System.out.println("Thank you for visiting!");
                scanner.close();
                break;
            default:
                System.out.println("Your input is invalid. Please try again.");
        }
    }

    public void admin() {
        System.out.println(Arrays.toString(commands.getAdminCommands()));
        int adminInput = scanner.nextInt();
        switch(adminInput){
            case 1:
                System.out.println(books);
                admin();
                break;
            case 2:
                findBooks();
                break;
            case 3:
                runLoanReport();
                break;
            case 4:
                adminList.createAdmin();
                adminLogin();
                break;
            case 5:
                System.out.println("Thank you for visiting!");
                scanner.close();
                break;
            default:
                System.out.println("Your input is invalid. Please try again.");
        }
    }


    public void findBooks(){
        System.out.println("Please enter the id or title of the book you would like to borrow.");
        String userInput = scanner.next();
        chosenBook = books.stream().filter(book -> book.getTitle().equalsIgnoreCase(userInput) || book.getId() == Integer.parseInt(userInput)).collect(Collectors.toList()).get(0);
        switch(typeOfVisitor){
            case "visitor":
                readBook();
                break;
            case "admin":
                System.out.println(chosenBook);
                checkIfOnLoan();
                break;
            case "member":
                System.out.println(chosenBook);
                loanBooks();
                break;
        }

    }

    private void checkIfOnLoan() {
        for(Book book: loanedBooks){
            if(chosenBook.getTitle().equals(book.getTitle())){
                System.out.println("This book is currently on loan.");
            }
        }
        admin();
    }

    public void readBook(){
        System.out.println("You are currently reading " + chosenBook + ". Enter done when completed reading to return to shelf.");
        String userInput = scanner.next();
        if(userInput.equalsIgnoreCase("done")){
            System.out.println(chosenBook + "has been returned.");
            visitor();
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public void loanBooks(){
        System.out.println("Is this the book you would like to borrow? Y/N");
        String userInput = scanner.next();
        if(userInput.equalsIgnoreCase("y") && !loggedInMember.getLoanedBooks().contains(chosenBook) && !loanedBooks.contains(chosenBook)){
                System.out.println(chosenBook);
                System.out.println(loggedInMember.getUsername());
                memberList.saveLoanBookToJson(chosenBook, loggedInMember.getUsername());
                loanedBooksList.saveLoanBookToJson(chosenBook);
                member();
            } else if (userInput.equalsIgnoreCase("y") && (loggedInMember.getLoanedBooks().contains(chosenBook) || loanedBooks.contains(chosenBook))){
                System.out.println("Sorry. You already have this book or this books is out on loan.");
                findBooks();
            } else if (userInput.equalsIgnoreCase("n")){
            System.out.println("Would you like to read the book? Y/N");
            userInput = scanner.next();
            if(userInput.equalsIgnoreCase("y")){
                readBook();
            } else {
                member();
            }
        }
    }

    public void memberLogin() {
        scanner.nextLine();
        System.out.println("Enter your username: ");
        String loginUsername = scanner.nextLine();
        System.out.println("Enter your password: ");
        String loginPassword = scanner.nextLine();

        Member loginAttempt = new Member();
        loginAttempt.setUsername(loginUsername);
        loginAttempt.setPassword(loginPassword);

        try {
            if (members.contains(loginAttempt)) {
                int index = members.indexOf(loginAttempt);
                loggedInMember = members.get(index);
                System.out.println(loginAttempt);
                System.out.println("Login successful. Welcome, " + loggedInMember.getName());
                member();
            } else {
                System.out.println("Invalid username or password. Please try again.");
                memberLogin();
            }
        } catch (NullPointerException e) {
            System.out.println("There are no members registered.");
        }
    }



    private void adminLogin() {
        scanner.nextLine();
        System.out.println("Enter your username: ");
        String loginUsername = scanner.nextLine();
        System.out.println("Enter your password: ");
        String loginPassword = scanner.nextLine();

        Admin loginAttempt = new Admin();
        loginAttempt.setUsername(loginUsername);
        loginAttempt.setPassword(loginPassword);

        try {
            for(Admin admin: admins){
                if (loginAttempt.getUsername().equals(admin.getUsername()) && loginAttempt.getPassword().equals(admin.getPassword())) {
                    loggedInAdmin = admin;
                    System.out.println("Login successful. Welcome, " + loggedInAdmin.getName());
                    admin();
                }
            }
            System.out.println(loginAttempt);
            System.out.println("Invalid username or password. Please try again.");
            adminLogin();

        } catch (NullPointerException e) {
            System.out.println("There are no admins registered.");
        }
    }

    private void runLoanReport() {
        System.out.println(loanedBooks);
        admin();
    }
}
