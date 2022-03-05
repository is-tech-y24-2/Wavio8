package com.tests;
import com.banks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private CentralBank newCentralBank;

    @BeforeEach
    void setUp() {
        newCentralBank = new CentralBank();
    }

    @Test
    void openAccountClientAndPutMoneyToAccount() throws Exception {
        var bank1 = newCentralBank.registrationBank("BSP", 4, new ArrayList<InterestBorder>(Arrays.asList(new InterestBorder(50000, 3), new InterestBorder(100000, 4), new InterestBorder(-1, 5))), 100, 10000);
        var client = new ClientDirector(new ClientBuilder(), "Vio", "Baiburtyan").getClientBuilder().addAddress("Zorge")
                .addPassportNumber(123).makeClient();
        var firstAccount = newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        assertEquals(0, newCentralBank.getAllBanks().get(0).getClientInBank().get(0).getAccountClient().getSum());
        bank1.putMoneyToAccount(firstAccount, 200);
        assertEquals("BSP", newCentralBank.getAllBanks().get(0).getNameBank());
        assertEquals(123, newCentralBank.getAllBanks().get(0).getClientInBank().get(0).getClient().getPassportNumber());
        assertEquals(100, newCentralBank.getAllBanks().get(0).getCommission());
        assertEquals(200, newCentralBank.getAllBanks().get(0).getClientInBank().get(0).getAccountClient().getSum());
    }

    @Test
    void addAllTypeAccountAndMoneyHasCome() throws Exception {
        var bank1 = newCentralBank.registrationBank("BSP", 4, new ArrayList<InterestBorder>(Arrays.asList(new InterestBorder(50000, 3), new InterestBorder(100000, 4), new InterestBorder(-1, 5))), 100, 10000);
        var client = new ClientDirector(new ClientBuilder(), "Vio", "Baiburtyan").getClientBuilder().addAddress("Zorge")
                .addPassportNumber(123).makeClient();
        var firstAccount = newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        assertEquals(0,newCentralBank.getAllBanks().get(0).getClientInBank().get(0).getAccountClient().getSum());
        bank1.putMoneyToAccount(firstAccount, 200);
        var secondAccount = newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        bank1.putMoneyToAccount(secondAccount, 400);
        var client2 = new ClientDirector(new ClientBuilder(), "n", "j").getClientBuilder().addAddress("r1")
                .makeClient();
        var thirdAccount = newCentralBank.openAccount(client2, bank1.openDepositAccount(55000, 40), bank1);
        assertEquals(400,bank1.getClientInBank().get(1).getAccountClient().getSum());
        assertEquals(3,bank1.getClientInBank().size());
        assertEquals("r1",bank1.getClientInBank().get(2).getClient().getAddress());
    }

    @Test
    void putMoneyToAccountAndCancelTransaction() throws Exception {
        var bank1 = newCentralBank.registrationBank("BSP", 4, new ArrayList<InterestBorder>(Arrays.asList(new InterestBorder(50000, 3), new InterestBorder(100000, 4), new InterestBorder(-1, 5))), 100, 10000);
        var client = new ClientDirector(new ClientBuilder(), "Vio", "Baiburtyan").getClientBuilder().addAddress("Zorge")
                .addPassportNumber(123).makeClient();
        var firstAccount = newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        bank1.putMoneyToAccount(firstAccount, 200);
        assertEquals(200,newCentralBank.getAllBanks().get(0).getClientInBank().get(0).getAccountClient().getSum());
        bank1.cancelTransaction(firstAccount, "Put money", 200);
        assertEquals(0,newCentralBank.getAllBanks().get(0).getClientInBank().get(0).getAccountClient().getSum());
    }

    @Test
    void changesInInterestDebitAndNewPercentage(){
        var bank1 = newCentralBank.registrationBank("BSP", 4, new ArrayList<InterestBorder>(Arrays.asList(new InterestBorder(50000, 3), new InterestBorder(100000, 4), new InterestBorder(-1, 5))), 100, 10000);
        var client = new ClientDirector(new ClientBuilder(), "Vio", "Baiburtyan").getClientBuilder().addAddress("Zorge")
                .addPassportNumber(123).makeClient();
        newCentralBank.openAccount(client, bank1.openDebitAccount(), bank1);
        assertEquals(4,bank1.getDebitPercentageInBank());
        bank1.changesInInterestDebit(10);
        assertEquals(10,bank1.getDebitPercentageInBank());

    }
}