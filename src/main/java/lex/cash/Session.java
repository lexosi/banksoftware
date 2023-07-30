package lex.cash;

import lex.bank.BankUser;

public class Session {
    private BankUser user;
    private long accountNumber;
    private boolean singleAccount;
    
    private long timestampConection;
    private long timestampLastActivity;
    private long maxInactiveTime;
}
