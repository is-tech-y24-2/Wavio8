package com.banks;

public class DebitAccount extends Account {
    private double currentAccumulatedPayment = 0;
    private int countAllDay = 0;
    private double fixedPercentage;

    public DebitAccount(double fixedPercentage) {
        this.fixedPercentage = fixedPercentage;
    }


    @Override
    public void changeAccountSum() {
        final int DAY_PER_YEAR=365;
        final int DAYS_IN_MONTH=30;
        final int HUNDRED_PERCENT=100;
        countAllDay ++;
        currentAccumulatedPayment = currentAccumulatedPayment + (sum * fixedPercentage / DAY_PER_YEAR / HUNDRED_PERCENT);
        if (countAllDay == DAYS_IN_MONTH) {
            sum = sum + currentAccumulatedPayment;
            currentAccumulatedPayment = 0;
            countAllDay = 0;
        }
    }

    @Override
    public boolean checkWithdraw(int sum) {
        if (sum > this.sum) {
            System.out.println("Amount higher than available");
        }

        return this.sum >= sum;
    }
}