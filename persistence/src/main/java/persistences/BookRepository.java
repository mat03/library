package persistences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.BookException;
import models.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository implements IBookRepository {
    private static final String JSON_FILEPATH = "./persistence/src/main/resources/database/books.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public BookRepository() {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void add(Book book) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Book> books = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Book>>(){});
        long id = books.size();

        book.setId(++id);
        books.add(book);

        OBJECT_MAPPER.writeValue(jsonFile, books);
    }

    @Override
    public void remove(Long id) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Book> books = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Book>>() {});

        for (Book book : books) {
            if(book.getId().equals(id)) {
                book.setRemoved(true);
                OBJECT_MAPPER.writeValue(jsonFile, books);
                return;
            }
        }
        throw new BookException("Can't remowe ID " + id);
    }

    @Override
    public void update(Book book) throws IOException {
        File jsonFile = new File(JSON_FILEPATH);
        List<Book> books = OBJECT_MAPPER.readValue(jsonFile, new TypeReference<List<Book>>() {});

        for (Book b : books) {
            if(b.getId().equals(book.getId())) {
                b.setTitle(book.getTitle());
                b.setPublishDate(book.getPublishDate());
                b.setIsbn(book.getIsbn());
                b.setGenre(book.getGenre());
                b.setNumberOfPages(book.getNumberOfPages());
                b.setDescription(book.getDescription());
                b.setRemoved(book.isRemoved());
                b.setBorrowed(book.isBorrowed());

                OBJECT_MAPPER.writeValue(jsonFile, books );
                return;
            }
        }
        throw new BookException("Update Fails ");
    }

    @Override
    public Book get(Long id) throws IOException {
        List<Book> books = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Book>>() {});

        for (Book book : books) {
            if(book.getId().equals(id)) {
                return book;
            }
        }
        throw new BookException("ID not exist " + id);
    }

    public List<Book> getAll() throws IOException {
        List<Book> books = OBJECT_MAPPER.readValue(new File(JSON_FILEPATH), new TypeReference<List<Book>>() {});

        return books.stream().filter(book -> !book.isBorrowed()).filter(book -> !book.isRemoved()).collect(Collectors.toList());
    }

}
