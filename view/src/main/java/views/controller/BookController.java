package views.controller;

import enums.BookGenre;
import exceptions.BookException;
import models.Book;
import services.BookService;
import services.IBookService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void removeBook(Long id) {
        List<Book> books = new ArrayList<Book>();
        try {
            books = bookService.allBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Book book : books) {
            if(book.isBorrowed() == true) {
                throw new BookException("is borrowed can't remowe");
            }
        }

        try {
            bookService.removeBook(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> allBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
             books = bookService.allBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void borrowBook(Long id) {
        List<Book> books = new ArrayList<Book>();
        try {
            books = bookService.allBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Book book : books) {
            if((book.isBorrowed() == true) || (book.isRemoved() == true) ) {
                throw new BookException("is borrowed can't remowe");
            }
        }

        try {
            bookService.borrowBook(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
