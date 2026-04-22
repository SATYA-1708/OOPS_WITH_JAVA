package com.bank.accounts;

public class SavingsAccount extends Account {

    private double interestRate;


    public SavingsAccount(int accNo, double balance, double interestRate) throws Exception {
        super(accNo, balance);
        this.interestRate = interestRate;
    }


    public double calculateInterest() {
        double interest = (balance * interestRate) / 100;
        return interest;
    }
}