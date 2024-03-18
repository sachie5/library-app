package org.example.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Book;
import org.h2.engine.User;

import java.util.List;
import java.util.Objects;

public class Member  {

    String name;

    String username;

    String password;

    List<Book> loanedBooks;

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

    public List<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public void setLoanedBooks(List<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public Member() {

    }

    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member - " +
                "Name: " + name +
                ", Username: " + username +
                ", Password: " + password +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(username, member.username) && Objects.equals(password, member.password);
    }

}
