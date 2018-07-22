package services;

import models.Book;
import persistences.BookRepository;
import persistences.IBookRepository;

import java.io.IOException;
import java.util.List;

public class BookService implements IBookService {
    private static final IBookRepository bookRepository = new BookRepository();

    public void addBook(Book book) throws IOException {
        bookRepository.add(book);
    }

    public void removeBook(Long id) throws IOException {
        bookRepository.remove(id);
    }

    public List<Book> allBooks() throws IOException {
        return bookRepository.getAll();
    }
}
