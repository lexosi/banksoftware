package lex;

import lex.module.mongo.MongoConnect;

public class Bank {
    public static void main(String[] args) throws Exception {
        MongoConnect.connect();
    }
}
