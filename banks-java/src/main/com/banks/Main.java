package com.banks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        CentralBank newCentralBank = new CentralBank();
        Scanner console = new Scanner(System.in);
        Bank bank1 = newCentralBank.registrationBank("BSP", 4, new ArrayList<InterestBorder>(Arrays.asList(new InterestBorder(50000, 3), new InterestBorder(100000, 4), new InterestBorder(-1, 5))), 100, 10000);

        while (true) {
            System.out.println("ENTER information:");
            System.out.println("Name: ");
            console.nextLine();
            String nameClient = console.nextLine();
            System.out.println("Surname: ");
            String surnameClient = console.nextLine();
            System.out.println("Address: ");
            String address = console.nextLine();
            System.out.println("Passport Number: ");
            int passportNumber = console.nextInt();
            System.out.println("Id account: ");
            while (true) {
                System.out.println();
                System.out.println("----------------------------------------------------------------------");
                System.out.println("CHOOSE number : ");
                System.out.println("1)Open a debit account  ");
                System.out.println("2)Open a deposit account  ");
                System.out.println("3)Open a credit account  ");
                System.out.println("4)Put money to account ");
                System.out.println("5)Withdraw Money ");
                System.out.println("6)Money transfer ");
                System.out.println("7)Сhange user ");
                int ans = console.nextInt();
                if (ans == 7) {
                    break;
                }

                if (ans == 1 || ans == 2 || ans == 3) {
                    Client client3 = new ClientDirector(new ClientBuilder(), nameClient, surnameClient)
                            .getClientBuilder()
                            .addAddress(address)
                            .addPassportNumber(passportNumber).makeClient();
                    System.out.println("Сlient is registered. ");
                    System.out.println();
                    if (ans == 1) {
                        ClientData account = newCentralBank.openAccount(client3, bank1.openDebitAccount(), bank1);
                        System.out.println("Debit account created. Amount on account: 0 . ID account " + account.getAccountClient().getID());
                        System.out.println();
                    } else if (ans == 2) {
                        System.out.println("Enter the initial deposit amount: ");
                        int sum = console.nextInt();
                        System.out.println("Enter the accumulation period: ");
                        int period = console.nextInt();
                        ClientData account = newCentralBank.openAccount(client3, bank1.openDepositAccount(sum, period), bank1);
                        System.out.println("Deposit account created. Amount on account: " + sum + " ID account " + account.getAccountClient().getID());
                        System.out.println();
                    } else if (ans == 3) {
                        ClientData account = newCentralBank.openAccount(client3, bank1.openCreditAccount(20000), bank1);
                        System.out.println("Credit account created. Amount on account: 0 ID account " + account.getAccountClient().getID());
                        System.out.println();
                    }
                } else {
                    System.out.println("Enter ID account ");
                    int id = console.nextInt();
                    ClientData clientData = newCentralBank.findAccountById(id);
                    if (ans == 4) {
                        System.out.println("Enter sum to put money");
                        int sum = console.nextInt();
                        bank1.putMoneyToAccount(clientData, sum);
                    } else if (ans == 5) {
                        System.out.println("Enter sum to withdraw money");
                        int sum = console.nextInt();
                        bank1.withdrawMoney(clientData, sum);
                    } else if (ans == 6) {
                        System.out.println("Enter ID another account");
                        int id2 = console.nextInt();
                        ClientData clientData2 = newCentralBank.findAccountById(id2);
                        System.out.println("Enter sum to transfer");
                        int sum = console.nextInt();
                        bank1.transferMoney(clientData, clientData2, sum);
                    }

                    break;
                }
            }

            break;

        }

        /*System.out.println("All good: ");
        var client = new ClientDirector(new ClientBuilder(), "n", "j").getClientBuilder().addAddress("r")
                .addPassportNumber(123).makeClient();
        var client2 = new ClientDirector(new ClientBuilder(), "n", "j").getClientBuilder().addAddress("r1")
                .makeClient();
        var firstAccount = newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        bank1.putMoneyToAccount(firstAccount, 200);
        var secondAccount = newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        bank1.putMoneyToAccount(secondAccount, 400);
        var thirdAccount = newCentralBank.openAccount(client2, bank1.openDepositAccount(55000, 40), bank1);
        bank1.putMoneyToAccount(thirdAccount, 100);
        var fourthAccount = newCentralBank.openAccount(client, bank1.openCreditAccount(20000), bank1);
        bank1.putMoneyToAccount(fourthAccount, 8000);
        bank1.withdrawMoney(firstAccount, 100);
        newCentralBank.signalToBanks();
        bank1.withdrawMoney(fourthAccount, 240000);*/
    }

}
