package com.banks;

public class Transaction {
    private String transactionName;
    private double sum;

    public Transaction(String transactionName, double sum) {
        this.transactionName = transactionName;
        this.sum = sum;
    }
    public double getSum(){
        return sum;
    }

    public String getTransactionName(){
        return transactionName;
    }
}
