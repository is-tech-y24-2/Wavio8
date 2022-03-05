package com.banks;

public abstract class Account {
    private static int Id = 0;
    private int ID;
    protected double sum;

    public Account() {
        sum = 0;
        Id++;
        ID = Id;
    }
    public double getSum(){
        return sum;
    }
    public int getID(){
        return ID;
    }

    public abstract void changeAccountSum();
    public abstract boolean checkWithdraw(int sum);
}
