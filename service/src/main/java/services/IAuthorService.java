package services;

import models.Author;

import java.io.IOException;

public interface IAuthorService {
    void addAuthor(Author author) throws IOException;
}
