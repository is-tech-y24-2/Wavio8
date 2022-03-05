package com.banks;

import java.util.ArrayList;
import java.util.Objects;

public class Bank {
    private String nameBank;
    private ArrayList<ClientData> clientInBank;
    private double debitPercentageInBank;
    private ArrayList<InterestBorder> interestBorderDeposit;
    private int commission;
    private int notInitializedLimit;
    private ArrayList<ClientData> subscription;

    public Bank(String name, double debitPercentageInBank, ArrayList<InterestBorder> interestBorderDeposit, int commission, int notInitializedLimit) {
        nameBank = name;
        clientInBank = new ArrayList<ClientData>();
        this.debitPercentageInBank = debitPercentageInBank;
        this.interestBorderDeposit = interestBorderDeposit;
        this.commission = commission;
        this.notInitializedLimit = notInitializedLimit;
        subscription = new ArrayList<ClientData>();
    }

    public ArrayList<ClientData> getClientInBank() {
        return clientInBank;
    }

    public String getNameBank() {
        return nameBank;
    }

    public double getDebitPercentageInBank() {
        return debitPercentageInBank;
    }

    public int getCommission() {
        return commission;
    }

    public DebitAccount openDebitAccount() {
        var debitAccount = new DebitAccount(debitPercentageInBank);
        return debitAccount;
    }

    public DepositAccount openDepositAccount(int sum, int accumulationPeriod) {
        double nowInterst = -1;
        for (InterestBorder i : interestBorderDeposit) {
            if ((sum < i.getSumBoarder()) && (i.getSumBoarder() != -1)) {
                nowInterst = i.getInterest();
                break;
            }
        }

        if (nowInterst == -1) {
            nowInterst = interestBorderDeposit.get(interestBorderDeposit.size() - 1).getInterest();
        }

        var depositAccount = new DepositAccount(nowInterst, accumulationPeriod);
        depositAccount.Sum = sum;
        return depositAccount;
    }

    public CreditAccount openCreditAccount(int creditLimit) {
        var creditAccount = new CreditAccount(creditLimit, commission);
        return creditAccount;
    }

    public void openAccount(ClientData clientData) {
        clientInBank.add(clientData);
    }

    public void putMoneyToAccount(ClientData clientData, int sum) throws Exception {
        for (ClientData i : clientInBank) {
            if (i.getAccountClient().getID() == clientData.getAccountClient().getID()) {
                i.getAccountClient().Sum = i.getAccountClient().Sum + sum;
                i.getClientTransactions().add(new Transaction("Put money", sum));
                return;
            }
        }

        throw new Exception("There is no such client");
    }

    public void subscribeToUpdates(ClientData clientData) {
        subscription.add(clientData);
    }

    public void withdrawMoney(ClientData clientData, int sum) throws Exception {
        if (checkDoubtful(clientData, sum)) {
            clientData.getAccountClient().Sum = clientData.getAccountClient().Sum - sum;
            clientData.getClientTransactions().add(new Transaction("Withdrew money", sum));
        }
    }

    public void transferMoney(ClientData clientData1, ClientData clientData2, int sum) throws Exception {
        if (checkDoubtful(clientData1, sum)) {
            clientData1.getAccountClient().Sum = clientData1.getAccountClient().Sum - sum;
            clientData1.getClientTransactions().add(new Transaction("Transfer withdrawal", sum));
            clientData2.getAccountClient().Sum = clientData2.getAccountClient().Sum + sum;
            clientData2.getClientTransactions().add(new Transaction("Transfer receiving", sum));
        }
    }

    public void cancelTransaction(ClientData clientData1, String transactionName, double sum) {
        for (Transaction i : clientData1.getClientTransactions()) {
            if (Objects.equals(transactionName, i.getTransactionName()) && sum == i.getSum()) {
                if (Objects.equals(i.getTransactionName(), "Transfer receiving") || Objects.equals(i.getTransactionName(), "Put money")) {
                    clientData1.getAccountClient().Sum -= sum;
                }

                if (Objects.equals(i.getTransactionName(), "Transfer withdrawal") || Objects.equals(i.getTransactionName(), "Withdrew money")) {
                    clientData1.getAccountClient().Sum += sum;
                }
            }
        }
    }

    public boolean checkDoubtful(ClientData clientData, int sum) throws Exception {
        if (!clientData.getAccountClient().checkWithdraw(sum)) {
            return false;
        }

        if ((clientData.getClient().getAddress() == null || clientData.getClient().getPassportNumber() == 0) &&
                (sum > notInitializedLimit)) {
            throw new Exception("It is impossible to perform a questionable operation");
        }

        return true;
    }

    public void changesInInterestDeposit(ArrayList<InterestBorder> interestBorderDeposit) {
        this.interestBorderDeposit = interestBorderDeposit;
        for (ClientData client : subscription) {
            if (client.getAccountClient() instanceof DepositAccount) {
                System.out.println("Deposit account conditions have changed");
            }
        }
    }

    public void changesInInterestDebit(double debitPercentageInBank) {
        this.debitPercentageInBank = debitPercentageInBank;
        for (ClientData client : subscription) {
            if (client.getAccountClient() instanceof DebitAccount) {
                System.out.println("Debit account conditions have changed");
            }
        }
    }
}