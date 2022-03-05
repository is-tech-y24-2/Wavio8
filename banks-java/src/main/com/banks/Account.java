package com.banks;

public abstract class Account {
    private static int Id = 0;
    private int ID;
    protected double Sum;

    public Account() {
        Sum = 0;
        Id++;
        ID = Id;
    }
    public double getSum(){
        return Sum;
    }
    public int getID(){
        return ID;
    }

    public abstract void changeAccountSum();
    public abstract boolean checkWithdraw(int sum);
}
