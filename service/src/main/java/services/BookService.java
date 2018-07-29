package services;

import importexport.ExportXML;
import importexport.ImportXML;
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

    public void borrowBook(Long id, boolean borrow) throws IOException {
        Book book = bookRepository.get(id);
        book.setBorrowed(borrow);
        bookRepository.update(book);
    }

    public Book getBook(Long id) throws IOException {
        return bookRepository.get(id);
    }

    public List<Book> importBooks() throws IOException {
        ImportXML importXML = new ImportXML();

        return importXML.getBookList();
    }

    public void exportBooks(List<Book> books) throws IOException {
        ExportXML exportXML = new ExportXML();

        exportXML.setBookList(books);
    }

}
