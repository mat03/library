package persistences;

import models.Author;

import java.io.IOException;
import java.util.List;

public interface IAuthorRepository {
    void add(Author author) throws IOException;
    void remove(Long id) throws IOException;
    Author get(Long id) throws IOException;
    void update(Author author) throws IOException;
    List<Author> getAll() throws IOException;
}
