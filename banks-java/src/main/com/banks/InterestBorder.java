package com.banks;

public class InterestBorder {
    private int sumBoarder;
    private double interest;

    public InterestBorder(int sumBoarder, double interest) {
        this.sumBoarder = sumBoarder;
        this.interest = interest;
    }

    public int getSumBoarder() {
        return sumBoarder;
    }

    public double getInterest() {
        return interest;
    }
}
