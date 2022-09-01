package eldar.creditcard.exceptions;

public class OperationMaxAmountReachedException extends Exception {
    public OperationMaxAmountReachedException(String errorMessage) {
        super(errorMessage);
    }
}
