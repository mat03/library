package exceptions;

public class BookException extends RuntimeException{
    public BookException(String message) {
        super("BookException " + message);
    }
}
