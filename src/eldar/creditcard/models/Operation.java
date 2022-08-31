package eldar.creditcard.models;

public class Operation {

    private final CreditCard card;
    private final double amount;

    public Operation(CreditCard card, double amount) {
        this.card = card;
        this.amount = amount;
    }

    public CreditCard getCard() {
        return card;
    }

    public double getAmount() {
        return amount;
    }
}
