package views.entry;

import enums.BookGenre;
import models.Author;
import models.Book;
import models.Borrow;
import models.Borrower;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import views.controller.AuthorController;
import views.controller.BookController;
import views.controller.BorrowController;
import views.controller.BorrowerController;
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
    private static final BorrowerController borrowerController = new BorrowerController();
    private static final BorrowController borrowControler = new BorrowController();

    public AplicationMenager() {
    }

    public void runApplication() {
        State state = State.INIT;

        Author author = new Author();

        do {
            switch (state) {
                case INIT: {
                    state = initState();
                    break;
                }
                case TRY_ADD_NEW_BOOK: {
                    state = tryAddNewBookState(author);
                    break;
                }
                case ADD_BOOK: {
                    state = addBookState();
                    break;
                }
                case ADD_AUTHOR: {
                    state = addAuthorState();
                    break;
                }
                case REMOVE_BOOK: {
                    state = removeBookState();
                    break;
                }
                case BORROW_BOOK: {
                    state = borrowBookState();
                    break;
                }
                case ADD_BORROWER: {
                    state = AddBorrowerState();
                    break;
                }
                case EXIT:
                    exitAplication();
            }

        }while (true);
    }


    public State initState() {

        do {
            System.out.println(Message.WELCOME_MESSAGE);
            int readKey;
            try {
                readKey = sc.nextInt();
            }catch (Exception e) {
                System.out.println(Message.WRONG_KEY);
                return State.INIT;
            }finally {
                sc.nextLine();
            }
            switch (readKey) {
                case 1: {
                    return State.TRY_ADD_NEW_BOOK;
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
                    return State.INIT;
                }
            }
        }while (true);
    }

    public void exitAplication() {
        System.exit(0);
    }

    public State tryAddNewBookState(Author author) {
        do {
            List<Author> authors = authorController.allAuthors();
            System.out.println(Message.ALL_AUTHORS);
            for(int i = 0; i < authors.size(); ++i)
            {
                System.out.println(authors.get(i).getId() + " - " + authors.get(i).getName());
            }

            System.out.println(Message.ADD_NEW_AUTHOR);

            int choseAuthor = sc.nextInt();
            sc.nextLine();

            if(choseAuthor == 0) {
                return State.ADD_AUTHOR;
            }
            else {
                if(authors.size() <= choseAuthor) {
                    author = authors.get(choseAuthor - 1);
                    return State.ADD_BOOK;
                }
                else {
                    System.out.println(Message.ADD_NEW_AUTHOR_WORNING);
                    return State.ADD_AUTHOR;
                }

            }

        } while (true);
    }

    public State addAuthorState() {
        String name, surname, birthPlace;

        System.out.println(Message.NEW_AUTHOR_NAME);
        name = sc.next();

        System.out.println(Message.NEW_AUTHOR_SURNAME);
        surname = sc.next();

        System.out.println(Message.NEW_AUTHOR_BIRTHPLACE);
        birthPlace = sc.next();

        authorController.addAuthor(name, surname, birthPlace);

        return State.TRY_ADD_NEW_BOOK;
    }

    public State addBookState() {
        String title, description, publishDateStr;
        Long isbn, numberOfPages;
        BookGenre genre;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

        System.out.println(Message.NEW_BOOK_TITLE);
        title = sc.nextLine();

        System.out.println(Message.NEW_BOOK_PUBLISH_DATE);

        try {
            publishDateStr = sc.nextLine();
            date = sdf.parse(publishDateStr);
        } catch (ParseException e) {
            System.out.println(Message.NEW_BOOK_PUBLISH_DATE_ERROR);
            return State.ADD_BOOK;
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
        return State.INIT;
    }

    public State removeBookState() {
        List<Book> books = bookController.allBooks();
        int bookSize = books.size();

        if(bookSize == 0) {
            System.out.println(Message.REMOVE_BOOK_EMPTY_LIBRARY);
            return State.INIT;
        }

        System.out.println(Message.REMOVE_BOOK);

        for (int i = 0; i < bookSize; ++i) {
            System.out.println(books.get(i).getId() + " -> " + books.get(i).getTitle() + "\n");
        }

        Long removeBookIndex = sc.nextLong();
        sc.nextLine();
        try {
            bookController.removeBook(removeBookIndex);
        }catch (Exception e){
            System.out.println(Message.REMOVE_BOOK_ID_NOT_EXIST);
        }


        return  State.REMOVE_BOOK;
    }

    public State borrowBookState() {
        System.out.println(Message.BORROW_BOOK);
        List<Borrower> borrowers = borrowerController.allBorrowers();
        int borrowerSize = borrowers.size();

        if(borrowerSize == 0) {
            System.out.println(Message.BORROW_BOOK_NO_BORROWER);
        } else {
            for (int i = 0; i < borrowerSize; ++i) {
                System.out.println(borrowers.get(i).getId() + " -> " + borrowers.get(i).getName() + ", " + borrowers.get(i).getSurname());
            }
        }

        Long selectOption = sc.nextLong();
        sc.nextLine();

        if(selectOption == 0) {
            return State.ADD_BORROWER;
        }

        System.out.println(Message.BORROW_BOOK_BORROW);
        List<Book> books = bookController.allBooks();
        int bookSize = books.size();

        for (int i = 0; i < bookSize; ++i) {
            System.out.println(books.get(i).getId() + " -> " + books.get(i).getTitle() + "\n");
        }

        Long bookId = sc.nextLong();
        sc.nextLine();
        bookController.borrowBook(bookId);


        return State.INIT;
    }

    public State AddBorrowerState() {
        String name, surname, address, email;
        Long phone;

        System.out.println(Message.ADD_BORROWER_NEW);

        System.out.println(Message.ADD_BORROWER_NAME);
        name = sc.nextLine();

        System.out.println(Message.ADD_BORROWER_SURNAME);
        surname = sc.nextLine();

        System.out.println(Message.ADD_BORROWER_ADDRESS);
        address = sc.nextLine();

        System.out.println(Message.ADD_BORROWER_Phone);
        phone = sc.nextLong();
        sc.nextLine();

        System.out.println(Message.ADD_BORROWER_EMAIL);
        email = sc.nextLine();

        Borrower borrower = new Borrower(name,surname,address,phone,email);
        borrowerController.addBorrower(borrower);

        return State.BORROW_BOOK;
    }

}
