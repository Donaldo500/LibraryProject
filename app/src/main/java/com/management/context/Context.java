package com.management.context;
import com.layer.library.Library;
import com.layer.authors.Authors;
import com.layer.books.Books;
import com.layer.users.Users;

public class Context {
    public static void main(String[] args) {
        Library library = new Library();

        //Agregamos un libro
        Books book = new Books("El sutil arte de que te importe un carajo", "Mark Manson", 2016, "978-0743273565");
        library.addBook(book);

        //Agregamos un autor
        Authors author = new Authors("Mark", "Manson", "Un gran autor que cambia tu perspectivas");
        library.addAuthor(author);

        //Agregamosd un usuario
        Users user = new Users("john_doe", "john@example.com", "password123");
        library.addUser(user);

        //Tratamos de agregar un libro ya existente para ver el manejo de errores
        //Books book1 = new Books("El sutil arte de que te importe un carajo", "Mark Manson", 2016, "978-0743273565");
        //library.addBook(book1);

        //Tratamos de agregar un autor ya existente para ver el manejo de errores
        //Authors author1 = new Authors("Mark", "Manson", "Un gran autor que cambia tu perspectivas");
        //library.addAuthor(author1);

        //Tratamos de agregar un usuario ya existente para ver el manejo de errores
        //Users user1 = new Users("john_doe", "john@example.com", "password123");
        //library.addUser(user1);
        
        //Buscamos un libro, autor y usuario
        Books foundBook = library.findBook("El sutil arte de que te importe un carajo");
        System.out.println("Libro encontrado: " + foundBook.getTitle() + " por " + foundBook.getAuthor());

        Authors foundAuthor = library.findAuthor("Mark Manson");
        System.out.println("Autor encontrado: " + foundAuthor.getFirstName() + " " + foundAuthor.getLastName());

        Users foundUser = library.findUser("john_doe");
        System.out.println("Usuario encontrado: " + foundUser.getUserName() + " con email: " + foundUser.getEmail());

        library.editBook("El sutil arte de que te importe un carajo", "Titulo falso", "Autor falso", 2020, "123");

        library.editAuthor("Mark", "Manson", "Autor", "Falso", "Un gran autor falso");

        library.editUser("john_doe", "john_new", "john_new@example.com", "newpassword123");

        library.deleteBook("Titulo falso");
        library.deleteAuthor("Autor falso");
        library.deleteUser("john_new");

        //Comprobamos que marque error al buscar Titulo Falso ya que lo eliminamos
        //System.out.println(library.findBook("Titulo falso"));
    }
}
