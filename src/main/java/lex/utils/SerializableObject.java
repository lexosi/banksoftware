package lex.utils;

import java.io.Serializable;

public abstract class SerializableObject implements Serializable {
    public byte[] toBytes() {
        try {
            return SerializationUtils.serialize(this);
        } catch(Exception exception) {
            exception.printStackTrace();
            return new byte[0];
        }
    }
}
