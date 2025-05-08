package org.example.sheriev.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {
    private Integer bookId;
    private Integer personId;
    @NotEmpty(message = "Please enter the name of the book")
    private String name;
    @NotEmpty(message = "Please enter the author name")
    private String author;
    @Max(value = 2025, message = "Please, enter the right year")
    private int year;

    public String getFullName() {
        return name + ", " + author + ", " + year;
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(int bookId, int personId, String name, String author, int year) {
        this.bookId = bookId;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    public Book(){};
}
