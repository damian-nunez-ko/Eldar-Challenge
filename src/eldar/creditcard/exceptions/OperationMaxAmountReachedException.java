package eldar.creditcard.exceptions;

public class OperationMaxAmountReachedException extends Throwable {
    public OperationMaxAmountReachedException(String errorMessage) {
        super(errorMessage);
    }
}
