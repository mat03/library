package views.controller;

import enums.BookGenre;
import models.Book;
import services.BookService;
import services.IBookService;


import java.io.IOException;
import java.util.Date;

public class BookController {
    private IBookService bookService = new BookService();

    public void addBook(String title, Date publishDate, Long isbn,
                        BookGenre genre, Long numberOfPages, String description) {
        Book book = new Book(title, publishDate, isbn, genre, numberOfPages, description);

        try {
            bookService.addBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
