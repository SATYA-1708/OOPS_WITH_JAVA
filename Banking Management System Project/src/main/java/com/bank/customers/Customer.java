package com.bank.customers;

import com.bank.accounts.Account;

public class Customer {

    private int customerId;
    private String name;
    private Account account;


    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }


    public void linkAccount(Account account) {
        this.account = account;
    }


    public void displayCustomerDetails() {
        System.out.println("\nCustomer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());
    }
}