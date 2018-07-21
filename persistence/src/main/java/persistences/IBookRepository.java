package persistences;

import models.Book;

import java.io.IOException;
import java.util.ArrayList;

public interface IBookRepository {
    void add(Book book) throws IOException;
    void remove(Long id);
    void update(Book book);
    Book get(Long id);
    ArrayList<Book> getAll();
}
