package com.mohse78.bankcardbase.classes;

/**
 * Created by ab.mohammadi on 3/7/2017.
 */
public class Report_TypesAndAmount {
    private long typeId;
    private double amount;
    private boolean debitOrCredit;

    public boolean isDebitOrCredit() {
        return debitOrCredit;
    }

    public void setDebitOrCredit(boolean debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
