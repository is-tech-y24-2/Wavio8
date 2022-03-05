package com.banks;

public class DebitAccount extends Account {
    private double _currentAccumulatedPayment = 0;
    private int countAllDay = 0;
    private double fixedPercentage;

    public DebitAccount(double fixedPercentage) {
        this.fixedPercentage = fixedPercentage;
    }


    @Override
    public void changeAccountSum() {
        countAllDay += 1;
        _currentAccumulatedPayment = _currentAccumulatedPayment + (Sum * fixedPercentage / 365 / 100);
        if (countAllDay == 30) {
            Sum = Sum + _currentAccumulatedPayment;
            _currentAccumulatedPayment = 0;
            countAllDay = 0;
        }
    }

    @Override
    public boolean checkWithdraw(int sum) {
        if (sum > Sum) {
            System.out.println("Amount higher than available");
        }

        return Sum >= sum;
    }
}