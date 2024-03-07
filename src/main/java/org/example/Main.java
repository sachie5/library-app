package org.example;

import org.json.JSONException;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, JSONException {
        Library library = new Library();
        library.openLibrary();
    }
}