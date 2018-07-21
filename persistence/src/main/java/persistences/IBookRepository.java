package persistences;

import models.Book;

import java.util.ArrayList;

public interface IBookRepository {
    void add(Book book);
    void remove(Long id);
    void update(Book book);
    Book get(Long id);
    ArrayList<Book> getAll();
}
