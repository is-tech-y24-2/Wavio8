package com.banks;

public class DepositAccount extends Account {
    private double currentAccumulatedPayment = 0;
    private int countAllDay = 0;
    private double fixedPercentage;
    private int accumulationPeriod;

    public DepositAccount(double fixedPercentage, int accumulationPeriod) {
        this.fixedPercentage = fixedPercentage;
        this.accumulationPeriod = accumulationPeriod;
    }

    @Override
    public void changeAccountSum() {
        final int DAY_PER_YEAR=365;
        final int DAYS_IN_MONTH=30;
        final int HUNDRED_PERCENT=100;
        countAllDay ++;
        currentAccumulatedPayment = currentAccumulatedPayment + (sum * fixedPercentage / DAY_PER_YEAR / HUNDRED_PERCENT);
        if (countAllDay % DAYS_IN_MONTH == 0) {
            sum = sum + currentAccumulatedPayment;
            currentAccumulatedPayment = 0;
        }
    }

    @Override
    public boolean checkWithdraw(int sum) {
        if (sum > this.sum) {
            System.out.println("Amount higher than available");
        }

        if (countAllDay < accumulationPeriod) {
            System.out.println("It is impossible to withdraw from the deposit account - not much time has passed");
        }

        return (countAllDay >= accumulationPeriod) && (this.sum >= sum);
    }
}
