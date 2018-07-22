package services;

import models.Book;
import persistences.BookRepository;
import persistences.IBookRepository;

import java.io.IOException;

public class BookService implements IBookService {
    private static final IBookRepository bookRepository = new BookRepository();

    public void addBook(Book book) throws IOException {
        bookRepository.add(book);
    }
}
