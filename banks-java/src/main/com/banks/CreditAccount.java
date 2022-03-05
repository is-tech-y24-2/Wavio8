package com.banks;

public class CreditAccount extends Account {
    private int creditLimit;
    private double commission;

    public CreditAccount(int creditLimit, double commission) {
        this.creditLimit = creditLimit;
        this.commission = commission;
    }


    @Override
    public void changeAccountSum() {
        if (Sum < 0) {
            Sum = Sum - commission;
        }
    }

    @Override
    public boolean checkWithdraw(int sum) {
        if (sum > Sum) {
            if (Math.abs(Sum - sum) < creditLimit) {
                System.out.println("Credit limit exceeded");
            }

            return Math.abs(Sum - sum) < creditLimit;
        } else {
            return true;
        }
    }
}
