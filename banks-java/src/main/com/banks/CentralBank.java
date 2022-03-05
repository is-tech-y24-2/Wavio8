package com.banks;

import java.util.ArrayList;

public class CentralBank {
    private ArrayList<Bank> allBanks;

    public CentralBank() {
        allBanks = new ArrayList<Bank>();
    }

    public ArrayList<Bank> getAllBanks() {
        return allBanks;
    }

    public Bank registrationBank(String nameBank, double debitPercentageInBank, ArrayList<InterestBorder> interestBorderDeposit, int commission, int notInitializedLimit) {
        allBanks.add(new Bank(nameBank, debitPercentageInBank, interestBorderDeposit, commission, notInitializedLimit));
        return allBanks.get(allBanks.size() - 1);
    }

    public void signalToBanks() {
        for (Bank bank : allBanks) {
            for (ClientData i : bank.getClientInBank()) {
                i.getAccountClient().changeAccountSum();
            }
        }
    }

    public ClientData findAccountById(int id) {
        for (Bank bank : allBanks) {
            for (ClientData i : bank.getClientInBank()) {
                if (i.getAccountClient().getID() == id) {
                    return i;
                }
            }
        }

        return null;
    }

    public ClientData openAccount(Client client, Account account, Bank bank) {
        ClientData newClientData = new ClientData(client, account);
        newClientData.setClient(client);
        bank.openAccount(newClientData);
        return newClientData;
    }
}
