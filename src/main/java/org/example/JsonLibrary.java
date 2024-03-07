package org.example;

import org.apache.commons.csv.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class JsonLibrary {

    private ArrayList<Book> listData = new ArrayList<Book>();

    public ArrayList<Book> getListData() {
        return listData;
    }

    public void setListData(ArrayList<Book> listData) {
        this.listData = listData;
    }

    public void convertCsvToJson () throws IOException {
        File csvFile = new File("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\books_data.csv");
        JSONArray arrayOfJSONObjects = new JSONArray();
        try (Reader reader = new FileReader(csvFile)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader().withQuote('"');

            CSVParser csvParser = new CSVParser(reader, csvFormat);
            Iterable<CSVRecord> records = csvParser.getRecords();

           for(CSVRecord record: records){
                try{
                    System.out.println("id:" + record.get("Number") + ",title:" + record.get("Title") + ",author" + record.get("Author")
                            + ",genre" + record.get("Genre") + ",subgenre" + record.get("SubGenre") + ",publisher" + record.get("Publisher"));

                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("id", record.get("Number"));
                    jsonObj.put("title",record.get("Title"));
                    jsonObj.put("author", record.get("Author"));
                    jsonObj.put("genre", record.get("Genre"));
                    jsonObj.put("subgenre", record.get("SubGenre"));
                    jsonObj.put("publisher", record.get("Publisher"));

                    arrayOfJSONObjects.put(jsonObj);
                } catch (JSONException e) {
                    System.err.println("Error processing JSON for record: " + record);
                    e.printStackTrace();
                }


                try(PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\bookData.json"))){
                    out.write((arrayOfJSONObjects.toString(4)));
                } catch (JSONException e) {
                    System.err.println("Error processing JSON for record: " + record);
                    e.printStackTrace();
                }

            }
        } catch (IOException e){
            e.printStackTrace();

        }
    }

    public void createListOfBooks() throws JSONException, IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get("C:\\Users\\winni\\Development\\nology\\projects\\java-library-app\\src\\main\\java\\org\\example\\data\\bookData.json")));
        JSONArray arrayOfJSONObjects = new JSONArray(jsonData);

        for (int i = 0; i < arrayOfJSONObjects.length(); i++) {
            JSONObject bookJson = arrayOfJSONObjects.getJSONObject(i);
            Book book = new Book (
                    bookJson.getInt("id"),
                    bookJson.getString("title"),
                    bookJson.getString("author"),
                    bookJson.getString("genre"),
                    bookJson.getString("subgenre"),
                    bookJson.getString("publisher")
            );
            listData.add(book);
        }
        }
    }


