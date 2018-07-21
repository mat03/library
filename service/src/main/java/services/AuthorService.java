package services;

import models.Author;
import persistences.AuthorRepository;
import persistences.IAuthorRepository;

import java.io.IOException;

public class AuthorService implements IAuthorService {
    private static final IAuthorRepository authorRepository = new AuthorRepository();

    public void addAuthor(Author author) throws IOException {
        authorRepository.add(author);
    }

}
