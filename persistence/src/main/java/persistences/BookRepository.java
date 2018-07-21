package persistences;

import models.Author;
import models.Book;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private static final String JSON_FILEPATH = new String("./persistence/src/main/resources/database/books.json");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void add(Book book) throws IOException {
        List<Book> books = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Book>>(){});

        book.setId(new Long(books.size()));
        books.add(book);

        OBJECT_MAPPER.writeValue(new File(JSON_FILEPATH),books);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public Book get(Long id) {
        return null;
    }

    @Override
    public ArrayList<Book> getAll() {
        return null;
    }
}
