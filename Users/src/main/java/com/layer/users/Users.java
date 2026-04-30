package com.layer.users;
import java.util.List;
import java.util.ArrayList;
import com.layer.books.Books;


public class Users {
    private String userName;
    private String email;
    private String password;
    private List<Books> borrowedBooks = new ArrayList<>();

    public Users(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Books> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addBorrowedBook(Books book) {
        borrowedBooks.add(book);
    }
}
