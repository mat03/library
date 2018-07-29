package services;

import models.Book;

import java.io.IOException;
import java.util.List;

public interface IBookService {
    void addBook(Book book) throws IOException;
    void removeBook(Long id) throws IOException;
    List<Book> allBooks() throws  IOException;
    void borrowBook(Long id, boolean borrow) throws IOException;
    Book getBook(Long id) throws IOException;

    List<Book> getImportBooks()throws IOException;

}
