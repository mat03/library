package exceptions;

public class BorrowerException extends RuntimeException {
    public BorrowerException(String message) {
        super("Borrower Exception " + message);
    }
}
