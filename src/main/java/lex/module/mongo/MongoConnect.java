package lex.module.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import lex.Variable;

import org.bson.Document;

public class MongoConnect {
    public static MongoDatabase database;
    private static MongoClient client;

    public static void connect() {

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        final ConnectionString URI = new ConnectionString(Variable.CONNECTION_STRING);
        final MongoClientSettings SETTINGS = MongoClientSettings.builder()
                .applyConnectionString(URI)
                .serverApi(serverApi)
                .retryWrites(true)
                .retryReads(true)
                .build();

        // Create a new client and connect to the server

        try {
            MongoClient mongoClient = MongoClients.create(SETTINGS);

            // Send a ping to confirm a successful connection
            MongoDatabase database = mongoClient.getDatabase("bank");
            database.runCommand(new Document("ping", 1));
            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

            MongoConnect.database = database;
            MongoConnect.client = mongoClient;
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }

    public static void disconnect() {
        if (client != null) {
            client.close();
        }

    }
}
