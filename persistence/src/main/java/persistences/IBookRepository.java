package persistences;

import models.Book;

import java.io.IOException;
import java.util.List;

public interface IBookRepository {
    void add(Book book) throws IOException;
    void remove(Long id) throws IOException;
    void update(Book book) throws IOException;
    Book get(Long id) throws IOException;
    List<Book> getAll() throws IOException;
    void borrow(Long id) throws IOException;
}
