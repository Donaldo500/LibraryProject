package com.layer.authors;
import java.util.List;
import java.util.ArrayList;
import com.layer.books.Books;

public class Authors {
    private String firstName;
    private String lastName;
    private String biography;
    private List<Books> publishedBooks = new ArrayList<>();

    public Authors(String firstName, String lastName, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBiography() {
        return biography;
    }

    public List<Books> getPublishedBooks() {
        return publishedBooks;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void addPublishedBook(Books book) {
        publishedBooks.add(book);
    }
}