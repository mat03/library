package views.controller;

import models.Author;
import services.AuthorService;
import services.IAuthorService;
import views.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorController {
    private IAuthorService authorService = new AuthorService();

    public AuthorController() {
    }

    public void addAuthor(String name, String surname, String placeOfBirght) {
        Author author = new Author(name, surname, placeOfBirght);

        try {
            authorService.addAuthor(author);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Author> allAuthors() {
        List<Author> authors = new ArrayList<Author>();

        try {
            authors = authorService.allAuthors();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
