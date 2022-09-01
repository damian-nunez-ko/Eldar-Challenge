package eldar.creditcard.exceptions;

public class CreditCardCantOperateException extends Throwable {
    public CreditCardCantOperateException(String errorMessage) {
        super(errorMessage);
    }
}
