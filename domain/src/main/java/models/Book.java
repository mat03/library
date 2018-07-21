package models;

import enums.BookGenre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Book {
    private Long id;
    private String title;
    private Date publishDate;
    private Long isbn;
    private BookGenre genre;
    private Long numberOfPages;
    private String description;

    public Book(Long id, String title, Date publishDate, Long isbn, BookGenre genre, Long numberOfPages, String description) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
        this.description = description;
    }
}
