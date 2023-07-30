package lex.bank;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lex.utils.ECardType;
import lex.utils.SerializableObject;

public class BankCard extends SerializableObject {
    private long accountNumber;
    private long cardNumber;
    private byte[] cardPin;
    private String ownerId;
    private ECardType type;

    public BankCard(long accountNumber, long cardNumber, byte[] cardPin, String ownerId, ECardType type) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
        this.ownerId = ownerId;
        this.type = type;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean compareCardPin(String comparePin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return digest.digest(comparePin.getBytes(StandardCharsets.UTF_8)) == this.cardPin;
        } catch (Exception ignored) {
        }
        return false;
    }

    public void setCardPin(String cardPin) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        this.cardPin = digest.digest(cardPin.getBytes(StandardCharsets.UTF_8));
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public ECardType getType() {
        return type;
    }

    public void setType(ECardType type) {
        this.type = type;
    }

}
