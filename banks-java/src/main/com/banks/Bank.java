package com.banks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    private String nameBank;
    private List<ClientData> clientInBank;
    private double debitPercentageInBank;
    private List<InterestBorder> interestBorderDeposit;
    private int commission;
    private int notInitializedLimit;
    private List<ClientData> subscription;

    public Bank(String name, double debitPercentageInBank, ArrayList<InterestBorder> interestBorderDeposit, int commission, int notInitializedLimit) {
        nameBank = name;
        clientInBank = new ArrayList<ClientData>();
        this.debitPercentageInBank = debitPercentageInBank;
        this.interestBorderDeposit = interestBorderDeposit;
        this.commission = commission;
        this.notInitializedLimit = notInitializedLimit;
        subscription = new ArrayList<ClientData>();
    }

    public List<ClientData> getClientInBank() {
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
        final int NOT_INITIALIZED_SUM_BORDER=-1;
        double nowInterst = -1;
        for (InterestBorder interestBorder : interestBorderDeposit) {
            if ((sum < interestBorder.getSumBoarder()) && (interestBorder.getSumBoarder() != NOT_INITIALIZED_SUM_BORDER)) {
                nowInterst = interestBorder.getInterest();
                break;
            }
        }

        if (nowInterst == NOT_INITIALIZED_SUM_BORDER) {
            nowInterst = interestBorderDeposit.get(interestBorderDeposit.size() - 1).getInterest();
        }

        var depositAccount = new DepositAccount(nowInterst, accumulationPeriod);
        depositAccount.sum = sum;
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
        for (ClientData clientDataInBank : clientInBank) {
            if (clientDataInBank.getAccountClient().getID() == clientData.getAccountClient().getID()) {
                clientDataInBank.getAccountClient().sum = clientDataInBank.getAccountClient().sum + sum;
                clientDataInBank.getClientTransactions().add(new Transaction("Put money", sum));
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
            clientData.getAccountClient().sum = clientData.getAccountClient().sum - sum;
            clientData.getClientTransactions().add(new Transaction("Withdrew money", sum));
        }
    }

    public void transferMoney(ClientData firstClientData, ClientData otherClientData, int sum) throws Exception {
        if (checkDoubtful(firstClientData, sum)) {
            firstClientData.getAccountClient().sum = firstClientData.getAccountClient().sum - sum;
            firstClientData.getClientTransactions().add(new Transaction("Transfer withdrawal", sum));
            otherClientData.getAccountClient().sum = otherClientData.getAccountClient().sum + sum;
            otherClientData.getClientTransactions().add(new Transaction("Transfer receiving", sum));
        }
    }

    public void cancelTransaction(ClientData clientData, String transactionName, double sum) {
        for (Transaction transaction : clientData.getClientTransactions()) {
            if (Objects.equals(transactionName, transaction.getTransactionName()) && sum == transaction.getSum()) {
                if (Objects.equals(transaction.getTransactionName(), "Transfer receiving") || Objects.equals(transaction.getTransactionName(), "Put money")) {
                    clientData.getAccountClient().sum -= sum;
                }

                if (Objects.equals(transaction.getTransactionName(), "Transfer withdrawal") || Objects.equals(transaction.getTransactionName(), "Withdrew money")) {
                    clientData.getAccountClient().sum += sum;
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