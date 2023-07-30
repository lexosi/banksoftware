package lex.cash;

import lex.utils.SerializableObject;

public abstract class AbstractOperation extends SerializableObject {
    private long timestamp;
    private long accountNumber;

    public AbstractOperation(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void performOperation(Session session) {
        this.timestamp = System.currentTimeMillis();
        this.execute(session);
    }

    public abstract void execute(Session session);

}
