package eldar.creditcard.exceptions;

public class CreditCardCantOperateException extends Exception {
    public CreditCardCantOperateException(String errorMessage) {
        super(errorMessage);
    }
}
