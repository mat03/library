package exceptions;

public class AuthorException extends RuntimeException {
    public AuthorException(String message) {
        super("Author Exception" + message);
    }
}
