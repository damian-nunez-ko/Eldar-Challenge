package eldar.creditcard.exceptions;

public class CreditCardAlreadyExistsException extends Exception {
    public CreditCardAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
