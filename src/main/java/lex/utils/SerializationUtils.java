package lex.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;

public class SerializationUtils {

    public static byte[] serialize(Serializable obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        SerializationUtils.serialize(obj, baos);
        return baos.toByteArray();
    }

    public static void serialize(Serializable obj, OutputStream outputStream) {
        SerializationUtils.notNull(outputStream, "outputStream", new Object[0]);

        try {
            final ObjectOutputStream out = new ObjectOutputStream(outputStream);
            Throwable throwable = null;

            try {
                out.writeObject(obj);
            } catch (Throwable exception) {
                throwable = exception;
                throw exception;
            } finally {
                if (out != null) {
                    if (throwable != null) {
                        try {
                            out.close();
                        } catch (Throwable exception) {
                            throwable.addSuppressed(exception);
                        }
                    } else
                        out.close();
                }

            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static <T> T notNull(T object) {
        return SerializationUtils.notNull(object, "The validated object is null");
    }

    public static <T> T notNull(T object, String message, Object... values) {
        return Objects.requireNonNull(object, () -> {
            return String.format(message, values);
        });
    }

    public static <T> T deserialize(byte[] objectData) {
        SerializationUtils.notNull(objectData, "objectData", new Object[0]);
        return SerializationUtils.deserialize((InputStream) (new ByteArrayInputStream(objectData)));
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(InputStream inputStream) {
        SerializationUtils.notNull(inputStream, "inputStream", new Object[0]);

        try {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            Throwable throwable = null;

            T object;
            try {
                object = (T) in.readObject();

            } catch (Throwable exception) {
                throwable = exception;
                throw exception;
            } finally {
                if (in != null) {
                    if (throwable != null) {
                        try {
                            in.close();
                        } catch (Throwable exception) {
                            throwable.addSuppressed(exception);
                        }
                    } else {
                        in.close();
                    }
                }

            }

            return object;
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

}
