package lex.cash.operation;

import java.math.BigDecimal;

import lex.cash.AbstractOperation;
import lex.cash.Session;

public class OperationDepositMoney extends AbstractOperation {

    private BigDecimal amount;

    public OperationDepositMoney(long accountNumber, BigDecimal amount) {
        super(accountNumber);
        this.amount = amount;
    }

    @Override
    public void execute(Session session) {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
