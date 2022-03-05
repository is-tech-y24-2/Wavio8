package com.banks;

import java.util.ArrayList;

public class ClientData {
    private Client client;
    private Account accountClient;
    private ArrayList<Transaction> clientTransactions;

    public ClientData(Client client, Account account) {
        this.client = client;
        accountClient = account;
        clientTransactions = new ArrayList<Transaction>();
    }
    public Account getAccountClient(){
        return accountClient;
    }
    public ArrayList<Transaction> getClientTransactions(){
        return clientTransactions;
    }
    public Client getClient(){
        return client;
    }
    public void setClient(Client client){
        this.client =client;
    }

}
