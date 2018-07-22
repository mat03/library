package services;

import models.Book;

import java.io.IOException;

public interface IBookService {
    void addBook(Book book) throws IOException;
}
