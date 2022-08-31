package eldar.creditcard;

import eldar.creditcard.models.CardIssuer;
import eldar.creditcard.models.CreditCard;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Processor {

    private static Processor processor = null;
    private final Map<Integer, CreditCard> cards;
    private static final int MAX_OPERATION_AMOUNT = 1000;

    private Processor() {
        cards = new HashMap<>();
    }

    public static Processor getInstance()
    {
        if(processor == null)
            processor = new Processor();

        return processor;
    }

    public void addCard(CreditCard card) {
        cards.put(card.getNumber(), card);
    }

    public CreditCard getCard(int id) {
        return cards.get(id);
    }

    public boolean isOperationValid(int amount) {
        return amount <= MAX_OPERATION_AMOUNT;
    }

    public boolean canCCOperate(int id) {
        CreditCard cc = cards.get(id);
        return cc.getExpireDate().isAfter(LocalDate.now());
    }

    public boolean areCCEqual(int id1, int id2) {
        return cards.get(id1).equals(cards.get(id2));
    }

}
