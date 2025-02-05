package com.marvisx.frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private   Integer id;
    private   String title;
    private   String author;
    private  String isbn;
    private  String publishedDate;

    public Book(Integer id, String title, String author, String isbn, String publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
    }
    public Book(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    /*
    private  final IntegerProperty id;
    private final  StringProperty title;
    private  final StringProperty author;
    private  final StringProperty isbn;
    private  final StringProperty publishedDate;

    public Book(int id, String title, String author, String isbn, String publishedDate) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.isbn = new SimpleStringProperty(isbn);
        this.publishedDate = new SimpleStringProperty(publishedDate);
    }
    // Setters for Updating Book Info
    public void setId(int id) { this.id.set(id); }
    public void setTitle(String title) { this.title.set(title); }
    public void setAuthor(String author) { this.author.set(author); }
    public void setIsbn(String isbn) { this.isbn.set(isbn); }
    public void setPublishedDate(String publishedDate) { this.publishedDate.set(publishedDate); }
    // Getters for API communication
    public int getId() { return id.get(); }
    public String getTitle() { return title.get(); }
    public String getAuthor() { return author.get(); }
    public String getIsbn() { return isbn.get(); }
    public String getPublishedDate() { return publishedDate.get(); }

    // Getters as Properties (For JavaFX Bindings)
    public IntegerProperty idProperty() { return id; }
    public StringProperty titleProperty() { return title; }
    public StringProperty authorProperty() { return author; }
    public StringProperty isbnProperty() { return isbn; }
    public StringProperty publishedDateProperty() { return publishedDate; }


*/

}
