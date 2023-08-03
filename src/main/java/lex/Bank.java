package lex;

import lex.bank.BankUser;
import lex.module.mongo.MongoConnect;

public class Bank {
    public static void main(String[] args) throws Exception {
        MongoConnect.connect();
        BankUser nex = new BankUser("123456789", "abcd", "1234".getBytes());
        nex.saveMongo();
    }
}
