package com.mohse78.bankcardbase;

import android.content.Context;

import java.util.List;

import DB.DBFactory;

/**
 * Created by ab.mohammadi on 2/4/2017.
 */
public class FirstInsertion {

    DBFactory db;

    public FirstInsertion(Context context) {
        db = new DBFactory(context);
    }

    public void FirstInsertionBanks( List<String> bankNames, List<Integer> bankLogoIds , List<Integer> bankCardImageIds) {
        for (int i = 0; i < bankNames.size(); i++) {
            Bank bank = new Bank();
            bank.setName(bankNames.get(i));
            bank.setImageLogoId(bankLogoIds.get(i));
            bank.setImageCardId(bankCardImageIds.get(i));
            db.insertBank(bank);
        }
    }
    public void FirstInsertionTransactionTypes( List<String> transTypeNames, List<Integer> transTypeLogoIds , List<Boolean> transTypeType) {
        for (int i = 0; i < transTypeNames.size(); i++) {
            TransactionType transactionType = new TransactionType();
            transactionType.setName(transTypeNames.get(i));
            transactionType.setImageLogoId(transTypeLogoIds.get(i));
            transactionType.setType(transTypeType.get(i));
            db.insertTransactionType(transactionType);
        }
    }
}
