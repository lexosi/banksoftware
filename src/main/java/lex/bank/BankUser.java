package lex.bank;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lex.utils.EUserPermision;
import lex.utils.SerializableObject;

public class BankUser extends SerializableObject {
    private EUserPermision permision;
    private String identifier;
    private String name;
    private byte[] pin;

    public BankUser(String identifier, String name, byte[] pin) {
        this.permision = EUserPermision.NONE;
        this.identifier = identifier;
        this.name = name;
        this.pin = pin;
    }

    public boolean comparePin(String comparePin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return digest.digest(comparePin.getBytes(StandardCharsets.UTF_8)) == this.pin;
        } catch (Exception ignored) {
        }
        return false;
    }

    public void setPin(String pin) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        this.pin = digest.digest(pin.getBytes(StandardCharsets.UTF_8));
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EUserPermision getPermision() {
        return permision;
    }

    public void setPermision(EUserPermision permision) {
        this.permision = permision;
    }

}
