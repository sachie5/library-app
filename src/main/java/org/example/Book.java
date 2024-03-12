package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private String subgenre;
    private String publisher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book(int id, String title, String author, String genre, String subgenre, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subgenre = subgenre;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "\n" + "Book - " +
                "id: " + id +
                ", title: " + title +
                ", author: " + author +
                ", genre: " + genre +
                ", subgenre: " + subgenre +
                ", publisher: " + publisher +
                '}';
    }

}
