package eldar.creditcard.service;

import eldar.creditcard.exceptions.CreditCardAlreadyExistsException;
import eldar.creditcard.exceptions.CreditCardCantOperateException;
import eldar.creditcard.exceptions.OperationMaxAmountReachedException;
import eldar.creditcard.models.CreditCard;
import eldar.creditcard.models.Operation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Processor {

    private static Processor processor = null;
    private final Map<Integer, CreditCard> cards;
    private final Map<Integer, Operation> operations;
    private Integer currentOpId;

    private static final int MAX_OPERATION_AMOUNT = 1000;

    private Processor() {
        cards = new HashMap<>();
        operations = new HashMap<>();
        currentOpId = 0;
    }

    public static Processor getInstance()
    {
        if(processor == null)
            processor = new Processor();

        return processor;
    }

    public void addCard(CreditCard card) throws CreditCardAlreadyExistsException {
        if(cards.containsKey(card.getNumber())) {
            throw new CreditCardAlreadyExistsException("Credit card already exists");
        }

        cards.put(card.getNumber(), card);
    }

    public CreditCard getCard(int id) {
        return cards.get(id);
    }

    public void doOperation(Operation op) throws CreditCardCantOperateException, OperationMaxAmountReachedException {
        if(!canCCOperate(op.getCard().getNumber())) {
            throw new CreditCardCantOperateException("Credit card has expired");
        }
        if(!isOperationValid(op)) {
            throw new OperationMaxAmountReachedException("Operation cost is higher than allowed");
        }
        // TODO: perfom operation
        operations.put(currentOpId, op);
        currentOpId++;
    }

    public Operation getOperation(int id) {
        return operations.get(id);
    }

    public double getCostAddedFromRate(Operation op) {
        return op.getAmount() * op.getCard().getIssuer().getRate(op);
    }

    public boolean isOperationValid(Operation op) {
        return op.getAmount() * (1 + getCostAddedFromRate(op)) <= MAX_OPERATION_AMOUNT;
    }

    public boolean canCCOperate(int id) {
        CreditCard cc = cards.get(id);
        return cc.getExpireDate().isAfter(LocalDate.now());
    }

    public boolean areCCEqual(int id1, int id2) {
        return cards.get(id1).equals(cards.get(id2));
    }

}
