package eldar.creditcard.exceptions;

public class BadArgumentsException extends Exception {
    public BadArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
