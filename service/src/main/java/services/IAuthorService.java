package services;

import models.Author;

import java.io.IOException;
import java.util.List;

public interface IAuthorService {
    void addAuthor(Author author) throws IOException;
    public List<Author> allAuthors() throws IOException;

}
