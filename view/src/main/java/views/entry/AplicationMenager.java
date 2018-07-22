package views.entry;

import enums.BookGenre;
import models.Author;
import views.controller.AuthorController;
import views.controller.BookController;
import views.enums.State;
import views.message.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static enums.BookGenre.Horror;

public class AplicationMenager {
    private static final Scanner sc = KeyboardReader.getInstance();

    private static final BookController bookController = new BookController();
    private static final AuthorController authorController = new AuthorController();

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
        do {
            List<Author> authors = authorController.allAuthors();
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
        String title, description, publishDateStr;
        Date publishDate;
        Long isbn, numberOfPages;
        BookGenre genre;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

        System.out.println(Message.NEW_BOOK_TITLE);
        title = sc.nextLine();

        System.out.println(Message.NEW_BOOK_PUBLISH_DATE);
        publishDateStr = sc.nextLine();

        try {
            date = sdf.parse(publishDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(Message.NEW_BOOK_ISBN);
        isbn = sc.nextLong();

        System.out.println(Message.NEW_BOOK_TYPE);
        //genre = new BookGenre("aaa");

        System.out.println(Message.NEW_BOOK_NUMBER_PAGE);
        numberOfPages = sc.nextLong();

        System.out.println(Message.NEW_BOOK_DESCRIPTION);
        description = sc.next();

        bookController.addBook(title, date, isbn, Horror, numberOfPages, description);
        return State.EXIT;
    }

    public State removeBookState() {
        Long id = 0L;
        bookController.removeBook(id);

        return  State.EXIT;
    }

}
