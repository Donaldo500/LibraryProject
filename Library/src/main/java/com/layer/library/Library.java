package com.layer.library;
import java.util.ArrayList;
import java.util.List;
import com.layer.authors.Authors;
import com.layer.books.Books;
import com.layer.users.Users;
import com.layer.notfound.NotFound;

public class Library {
    private List<Books> books = new ArrayList<>();
    private List<Authors> authors = new ArrayList<>();
    private List<Users> users = new ArrayList<>();

    public void addBook(Books book) {
        boolean exists = books.stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle()));
        if (exists) {
            throw new NotFound("El libro " + book.getTitle() + " ya existe en la biblioteca.");
        }
        books.add(book);
        System.out.println("Se agrego el libro: " + book.getTitle() + " a la biblioteca.");
    }

    public void addAuthor(Authors author) {
        boolean exists = authors.stream()
                .anyMatch(a -> a.getFirstName().equalsIgnoreCase(author.getFirstName()) && a.getLastName().equalsIgnoreCase(author.getLastName()));
        
        if (exists) {
            throw new NotFound("El autor " + author.getFirstName() + " " + author.getLastName() + " ya existe en la biblioteca.");
        }

        authors.add(author);
        System.out.println("Se agrego el autor: " + author.getFirstName() + " " + author.getLastName());
    }

    public void addUser(Users user) {
        boolean exists = users.stream()
                .anyMatch(u -> u.getUserName().equalsIgnoreCase(user.getUserName()) || u.getEmail().equalsIgnoreCase(user.getEmail()));
        
        if (exists) {
            throw new NotFound("Algun usuario ya usa el username: " + user.getUserName() + " o el email: " + user.getEmail());
        }
        
        users.add(user);
        System.out.println("Se agrego el usuario: " + user.getUserName() + ".");
    }  

    public Books findBook(String title){
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new NotFound("No se encontro el libro: " + title));
    }

    public Authors findAuthor(String name){
        return authors.stream()
                .filter(author -> 
                    (author.getFirstName() + " " + author.getLastName())
                    .toLowerCase()
                    .contains(name.toLowerCase())
                )
                .findFirst()
                .orElseThrow(() -> new NotFound("No se encontro el autor: " + name));
    }

    public Users findUser(String username){
        return users.stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new NotFound(username));
    }

    public void deleteBook(String title) {
        Books book = findBook(title);
        books.remove(book);
        System.out.println(title + " se ha eliminado.");
    }

    public void deleteAuthor(String name) {
        Authors author = findAuthor(name);
        authors.remove(author);
        System.out.println(name + " se ha eliminado.");
    }

    public void deleteUser(String username) {
        Users user = findUser(username);
        users.remove(user);
        System.out.println(username + " se ha eliminado.");
    }

    public void editBook(String title, String newTitle, String author, int publicationYear, String isbn){
        Books book = findBook(title);
        book.setTitle(newTitle);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbn);
        System.out.println(title + " se ha editado.");
    }

    public void editAuthor(String firstName, String lastName,String newFName, String newLName, String biography){
        Authors author = findAuthor(firstName + " " + lastName);
        author.setFirstName(newFName);
        author.setLastName(newLName);
        author.setBiography(biography);
        System.out.println("El autor: " + firstName + " " + lastName + " se ha editado.");
    }

    public void editUser(String userName, String newUserName, String email, String password){
        Users user = findUser(userName);
        user.setUserName(newUserName);
        user.setEmail(email);
        user.setPassword(password);
        System.out.println("El usuario: " + userName + " se ha editado.");
    }
}