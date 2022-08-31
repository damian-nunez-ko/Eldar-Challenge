package eldar.creditcard.models;

import java.time.LocalDate;

public class Operation {

    private final CreditCard card;
    private final double amount;
    private final LocalDate purchaseDate;

    public Operation(CreditCard card, double amount, LocalDate purchaseDate) {
        this.card = card;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
    }

    public CreditCard getCard() {
        return card;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
}
