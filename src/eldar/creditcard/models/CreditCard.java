package eldar.creditcard.models;

import java.time.LocalDate;
import java.util.Objects;

public class CreditCard {

    private final CardIssuer issuer;
    private final int id;
    private final Person holder;
    private final LocalDate expireDate;

    public CreditCard(CardIssuer issuer, int id, Person holder, LocalDate expireDate) {
        this.issuer = issuer;
        this.id = id;
        this.holder = holder;
        this.expireDate = expireDate;
    }

    public CardIssuer getIssuer() {
        return issuer;
    }

    public int getId() {
        return id;
    }

    public Person getHolder() {
        return holder;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CC: " + id + "\nHolder: " + holder + "\nIssuer: " + issuer.toString() + "\nExpiration date: " + expireDate.toString();
    }

}
