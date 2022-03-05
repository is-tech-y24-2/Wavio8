package com.banks;

import java.util.ArrayList;
import java.util.List;

public class ClientData {
    private Client client;
    private Account accountClient;
    private List<Transaction> clientTransactions;

    public ClientData(Client client, Account account) {
        this.client = client;
        accountClient = account;
        clientTransactions = new ArrayList<Transaction>();
    }

    public Account getAccountClient() {
        return accountClient;
    }

    public List<Transaction> getClientTransactions() {
        return clientTransactions;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
