package views.controller;

import models.Author;
import services.AuthorService;
import services.IAuthorService;

import java.io.IOException;

public class AuthorController {
    private IAuthorService authorService = new AuthorService();

    public void addAuthor(String name, String surname, String placeOfBirght) throws IOException {
        Author author = new Author("mama", "tata","Krak");

        authorService.addAuthor(author);
    }
}
