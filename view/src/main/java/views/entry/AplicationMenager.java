package views.entry;

import models.Author;
import views.controller.AuthorController;
import views.controller.BookController;
import views.enums.State;
import views.message.Message;

import java.util.List;
import java.util.Scanner;

public class AplicationMenager {
    private static final Scanner sc = KeyboardReader.getInstance();

    public State initState() {
        do {
            System.out.println(Message.WELCOME_MESSAGE);
            int readKey = sc.nextInt();
            sc.nextLine();

            switch (readKey) {
                case 1: {
                    return State.ADD_BOOK;
                }
                case 2: {
                    return State.REMOVE_BOOK;
                }
                case 3: {
                    return State.BORROW_BOOK;
                }
                case 4: {
                    return State.BACK_BOOK;
                }
                case 5: {
                    return State.EXIT;
                }
                default: {
                    System.out.println(Message.WRONG_KEY);
                }
            }

        }while (true);
    }

    public void exitAplication() {
        System.exit(0);
    }

    public State addBookAuthorState() {
        AuthorController ac = new AuthorController();

        do {
            List<Author> authors = ac.allAuthors();
            System.out.println(Message.ALL_AUTHORS);
            for(int i = 0; i < authors.size(); ++i)
            {
                System.out.println(i + " - " + authors.get(i).getName());
            }

            System.out.println(Message.ADD_NEW_AUTHOR);

            int choseAuthor = sc.nextInt();
            sc.nextLine();
            Author author = authors.get(choseAuthor);
            if(choseAuthor == 0) {

                return State.ADD_AUTHOR;
            }
            else {
                return State.ADD_BOOK;
            }

        } while (true);
    }

    public State addNewAuthorState() {
        String name, surname, birthPlace;
        AuthorController authorController = new AuthorController();

        System.out.println(Message.NEW_AUTHOR_NAME);
        name = sc.next();

        System.out.println(Message.NEW_AUTHOR_SURNAME);
        surname = sc.next();

        System.out.println(Message.NEW_AUTHOR_BIRTHPLACE);
        birthPlace = sc.next();

        authorController.addAuthor(name, surname, birthPlace);

        return State.EXIT;
    }

    public State addNewBookState() {

        BookController bookController = new BookController();
        bookController.addBook();
        return State.EXIT;
    }

}
