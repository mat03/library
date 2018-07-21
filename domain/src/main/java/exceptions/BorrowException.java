package exceptions;

public class BorrowException extends RuntimeException{
    public BorrowException(String message) {
        super("Borrow Exception " + message);
    }
}
