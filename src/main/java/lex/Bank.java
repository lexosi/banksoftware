package lex;

import lex.bank.BankUser;
import lex.module.mongo.MongoConnect;

public class Bank {
    public static void main(String[] args) throws Exception {
        MongoConnect.connect();
        BankUser user = new BankUser("123456789", "test", "1234");
        user.saveMongo();
        BankUser nex = BankUser.getFromMongo("123456789");
        System.out.println(nex.toString());
        System.out.println(nex.comparePin("1111"));
        System.out.println(nex.comparePin("1234"));
        MongoConnect.disconnect();
    }
}
