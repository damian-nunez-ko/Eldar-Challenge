package eldar.creditcard.service;

import eldar.creditcard.exceptions.BadArgumentsException;
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

    // Credit Cards and Operations would be persisted to a db
    private final Map<Long, CreditCard> cards;
    private final Map<Integer, Operation> operations;
    private Integer currentOpId;

    private static final double MAX_OPERATION_AMOUNT = 1000.0;

    private Processor() {
        cards = new HashMap<>();
        operations = new HashMap<>();
        currentOpId = 0;
    }

    public static Processor getInstance() {
        if(processor == null)
            processor = new Processor();
        return processor;
    }

    public void addCard(CreditCard card) throws CreditCardAlreadyExistsException, BadArgumentsException {
        if(card == null) {
            throw new BadArgumentsException("Invalid Argument");
        }
        if(cards.containsKey(card.getId())) {
            throw new CreditCardAlreadyExistsException("Credit card already exists");
        }
        cards.put(card.getId(), card);
    }

    public CreditCard getCard(long id) {
        return cards.get(id);
    }

    public void doOperation(Operation op) throws CreditCardCantOperateException, OperationMaxAmountReachedException, BadArgumentsException {
        checkOperationArguments(op);
        if(!canCCOperate(op.getCard().getId())) {
            throw new CreditCardCantOperateException("Credit card has expired");
        }
        if(!isOperationValid(op)) {
            throw new OperationMaxAmountReachedException("Operation cost is higher than allowed");
        }
        // TODO: perform operation
        operations.put(currentOpId, op);
        currentOpId++;
    }

    public Operation getOperation(int id) {
        return operations.get(id);
    }

    public double getCostAddedFromRate(Operation op) throws BadArgumentsException {
        checkOperationArguments(op);
        return op.getAmount() * op.getCard().getIssuer().getRate(op);
    }

    public boolean isOperationValid(Operation op) throws BadArgumentsException {
        checkOperationArguments(op);
        return op.getAmount() + getCostAddedFromRate(op) <= MAX_OPERATION_AMOUNT;
    }

    public boolean canCCOperate(long id) throws BadArgumentsException {
        CreditCard cc = cards.get(id);
        if(cc == null || cc.getExpireDate() == null) {
            throw new BadArgumentsException("Invalid Argument");
        }
        return cc.getExpireDate().isAfter(LocalDate.now());
    }

    public boolean areCCEqual(long id1, long id2) throws BadArgumentsException {
        CreditCard cc1 = cards.get(id1);
        CreditCard cc2 = cards.get(id2);
        if(cc1 == null || cc2 == null) {
            throw new BadArgumentsException("Invalid Argument");
        }
        return cc1.equals(cc2);
    }

    public void checkOperationArguments(Operation op) throws BadArgumentsException {
        if(     op == null ||
                op.getCard() == null ||
                op.getPurchaseDate() == null ||
                op.getCard().getIssuer() == null ||
                op.getCard().getExpireDate() == null ||
                op.getCard().getHolder() == null ||
                op.getAmount() <= 0) {
            throw new BadArgumentsException("Invalid Argument");
        }
    }

}
