package eldar.creditcard;

import java.util.Date;
import java.util.Objects;

public class CreditCard {

    private final CardIssuer issuer;
    private final Integer number;
    private final Person holder;
    private final Date expireDate;

    public CreditCard(CardIssuer issuer, Integer number, Person holder, Date expireDate) {
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

    public Date getExpireDate() {
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
}
