package com.banks;

public class DepositAccount extends Account {
    private double _currentAccumulatedPayment = 0;
    private int countAllDay = 0;
    private double fixedPercentage;
    private int accumulationPeriod;

    public DepositAccount(double fixedPercentage, int accumulationPeriod) {
        this.fixedPercentage = fixedPercentage;
        this.accumulationPeriod = accumulationPeriod;
    }

    @Override
    public void changeAccountSum() {
        countAllDay += 1;
        _currentAccumulatedPayment = _currentAccumulatedPayment + (Sum * fixedPercentage / 365 / 100);
        if (countAllDay % 30 == 0) {
            Sum = Sum + _currentAccumulatedPayment;
            _currentAccumulatedPayment = 0;
        }
    }

    @Override
    public boolean checkWithdraw(int sum) {
        if (sum > Sum) {
            System.out.println("Amount higher than available");
        }

        if (countAllDay < accumulationPeriod) {
            System.out.println("It is impossible to withdraw from the deposit account - not much time has passed");
        }

        return (countAllDay >= accumulationPeriod) && (Sum >= sum);
    }
}
