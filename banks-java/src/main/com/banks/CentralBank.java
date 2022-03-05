package com.banks;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private List<Bank> allBanks;

    public CentralBank() {
        allBanks = new ArrayList<Bank>();
    }

    public List<Bank> getAllBanks() {
        return allBanks;
    }

    public Bank registrationBank(String nameBank, double debitPercentageInBank, ArrayList<InterestBorder> interestBorderDeposit, int commission, int notInitializedLimit) {
        allBanks.add(new Bank(nameBank, debitPercentageInBank, interestBorderDeposit, commission, notInitializedLimit));
        return allBanks.get(allBanks.size() - 1);
    }

    public void signalToBanks() {
        for (Bank bank : allBanks) {
            for (ClientData clientData : bank.getClientInBank()) {
                clientData.getAccountClient().changeAccountSum();
            }
        }
    }

    public ClientData findAccountById(int id) {
        for (Bank bank : allBanks) {
            for (ClientData clientData : bank.getClientInBank()) {
                if (clientData.getAccountClient().getID() == id) {
                    return clientData;
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
