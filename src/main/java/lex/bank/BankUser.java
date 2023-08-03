package lex.bank;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;

import lex.module.mongo.MongoConnect;
import lex.utils.EUserPermision;
import lex.utils.SerializableObject;

public class BankUser extends SerializableObject {
    private EUserPermision permision;
    private String identifier;
    private String name;
    private String pin;

    public BankUser(String identifier, String name, String pin) {
        this.permision = EUserPermision.NONE;
        this.identifier = identifier;
        this.name = name;
        try {
            this.setPin(pin);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public boolean comparePin(String comparePin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return new String(digest.digest(comparePin.getBytes(StandardCharsets.UTF_8))).equals(this.pin);
        } catch (Exception ignored) {
        }
        return false;
    }

    public void setPin(String pin) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        this.pin = new String(digest.digest(pin.getBytes(StandardCharsets.UTF_8)));
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

    public static MongoCollection<Document> getCollection() {
        return MongoConnect.database.getCollection("users");
    }

    public void saveMongo() {
        final MongoCollection<Document> COLLECTION = BankUser.getCollection();
        final Document DOCUMENT = this.toDocument();
        
        Bson filter = Filters.eq("_id", identifier);
        ReplaceOptions options = new ReplaceOptions().upsert(true);
        COLLECTION.replaceOne(filter, DOCUMENT, options);
    }

    public Document toDocument() {
        final Document DOCUMENT = new Document();
        DOCUMENT.append("_id", this.identifier);
        DOCUMENT.append("name", this.name);
        DOCUMENT.append("pin", this.pin);
        DOCUMENT.append("permision", this.permision.toString());
        return DOCUMENT;
    }

 
    public static BankUser getFromMongo(String identifier) {
        final MongoCollection<Document> COLLECTION = BankUser.getCollection();
        final Document DOCUMENT = COLLECTION.find(new Document("_id", identifier)).first();
        if (DOCUMENT == null) {
            return null;
        }
        String pin = DOCUMENT.getString("pin");
        BankUser USER = new BankUser(DOCUMENT.getString("_id"), DOCUMENT.getString("name"), pin);
        USER.pin = pin;
        return USER;
    }

}
