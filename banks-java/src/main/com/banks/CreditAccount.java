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
        if (sum < 0) {
            sum = sum - commission;
        }
    }

    @Override
    public boolean checkWithdraw(int sum) {
        if (sum > this.sum) {
            if (Math.abs(this.sum - sum) < creditLimit) {
                System.out.println("Credit limit exceeded");
            }

            return Math.abs(this.sum - sum) < creditLimit;
        } else {
            return true;
        }
    }
}
