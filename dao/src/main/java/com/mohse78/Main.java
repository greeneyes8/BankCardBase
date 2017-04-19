package com.mohse78;


import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"com.mohse78.bankcardbase");
        Entity personEntity = schema.addEntity("Person");
        personEntity.setTableName("Persons");
        Entity bankEntity = schema.addEntity("Bank");
        bankEntity.setTableName("Banks");
        Entity cardEntity = schema.addEntity("Card");
        cardEntity.setTableName("Cards");
        Entity accountEntity = schema.addEntity("Account");
        accountEntity.setTableName("Accounts");
        Entity accountTransactionEntity = schema.addEntity("AccountTransaction");
        accountTransactionEntity.setTableName("AccountTransactions");
        Entity SMSBankMetaTable = schema.addEntity("SmsBankMetaData");
        Entity transactionType = schema.addEntity("TransactionType");
        transactionType.setTableName("TransactionTypes");

        personEntity.addIdProperty();
        personEntity.addStringProperty("name");
        personEntity.addStringProperty("mobile");
        personEntity.addBooleanProperty("deleted");

        bankEntity.addIdProperty();
        bankEntity.addStringProperty("name");
        bankEntity.addIntProperty("imageLogoId");
        bankEntity.addIntProperty("imageCardId");
        bankEntity.addStringProperty("cardNumberDefault");
        bankEntity.addBooleanProperty("deleted");

        accountEntity.addIdProperty();
        accountEntity.addStringProperty("accountIdOriginal");
        accountEntity.addBooleanProperty("forTransaction");
        accountEntity.addBooleanProperty("givSms");
        accountEntity.addDoubleProperty("balance");

        accountTransactionEntity.addIdProperty();
        accountTransactionEntity.addLongProperty("account_id");
        accountTransactionEntity.addDoubleProperty("lastBalance");
        accountTransactionEntity.addDoubleProperty("debit");
        accountTransactionEntity.addDoubleProperty("credit");
        accountTransactionEntity.addDoubleProperty("currentBalance");
        accountTransactionEntity.addStringProperty("description");
        accountTransactionEntity.addDateProperty("creationDate");
        accountTransactionEntity.addLongProperty("transactionType");

        cardEntity.addIdProperty();
        cardEntity.addLongProperty("bank_id");
        cardEntity.addLongProperty("account_id");
        cardEntity.addLongProperty("person_id");
        cardEntity.addStringProperty("cardNumber");
        cardEntity.addStringProperty("cvv2");
        cardEntity.addStringProperty("expDate");
        cardEntity.addStringProperty("bankBranchCode");

        SMSBankMetaTable.addIdProperty();
        SMSBankMetaTable.addStringProperty("bankName");
        SMSBankMetaTable.addStringProperty("accountNumber");
        SMSBankMetaTable.addStringProperty("debitFieldName");
        SMSBankMetaTable.addStringProperty("creditFieldName");
        SMSBankMetaTable.addStringProperty("description");
        SMSBankMetaTable.addDoubleProperty("amount");
        SMSBankMetaTable.addDoubleProperty("balance");
        SMSBankMetaTable.addDateProperty("date");


        transactionType.addIdProperty();
        transactionType.addBooleanProperty("type");
        transactionType.addStringProperty("name");
        transactionType.addIntProperty("imageLogoId");
        transactionType.addStringProperty("description");



        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema,"./app/src/main/java");
    }
}
