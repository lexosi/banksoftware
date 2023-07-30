package lex.bank;

import lex.utils.EBank;
import lex.utils.SerializableObject;

public class BankAccount extends SerializableObject {
    private long accountNumber;
    private long[] cardNumber;
    private String[] ownerId;
    private long balance;
    private EBank bank;

    public BankAccount(long accountNumber, long[] cardNumber, String[] ownerId, long balance, EBank bank) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.ownerId = ownerId;
        this.balance = balance;
        this.bank = bank;
    }

    public BankAccount(long accountNumber, String[] ownerId, long balance, EBank bank) {
        this.accountNumber = accountNumber;
        this.ownerId = ownerId;
        this.balance = balance;
        this.bank = bank;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long[] getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long[] cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String[] getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String[] ownerId) {
        this.ownerId = ownerId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public EBank getBank() {
        return bank;
    }

    public void setBank(EBank bank) {
        this.bank = bank;
    }

}
