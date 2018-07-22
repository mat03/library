package services;

import exceptions.AuthorException;
import models.Author;
import persistences.AuthorRepository;
import persistences.IAuthorRepository;

import java.io.IOException;
import java.util.List;

public class AuthorService implements IAuthorService {
    private static final IAuthorRepository authorRepository = new AuthorRepository();

    public void addAuthor(Author author) throws IOException {
        authorRepository.add(author);
    }

    public void allAuthors() throws IOException {
        authorRepository.getAll();
    }

    public Author getAuthors(String name, String surname) throws IOException {
        List<Author> authors = authorRepository.getAll();

        for (Author a: authors) {
            if(a.getName().equals(name) && a.getSurname().equals(surname)) {
                return a;
            }
        }
        throw  new AuthorException("");
    }

}
