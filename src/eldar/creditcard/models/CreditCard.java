package eldar.creditcard.models;

import java.time.LocalDate;
import java.util.Objects;

public class CreditCard {

    private final CardIssuer issuer;
    private final Integer number;
    private final Person holder;
    private final LocalDate expireDate;

    public CreditCard(CardIssuer issuer, Integer number, Person holder, LocalDate expireDate) {
        this.issuer = issuer;
        this.number = number;
        this.holder = holder;
        this.expireDate = expireDate;
    }

    public CardIssuer getIssuer() {
        return issuer;
    }

    public Integer getNumber() {
        return number;
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
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "CC: " + number + "\nHolder: " + holder + "\nIssuer: " + issuer.toString() + "\nExpiration date: " + expireDate.toString();
    }

}
