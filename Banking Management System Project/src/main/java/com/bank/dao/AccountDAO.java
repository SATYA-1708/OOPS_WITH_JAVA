package com.bank.dao;

import java.sql.*;
import com.bank.util.BankUtil;

public class AccountDAO {

    public void deposit(int accNo, double amount) throws Exception {
        Connection con = BankUtil.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "UPDATE account SET balance = balance + ? WHERE acc_no=?"
        );
        ps.setDouble(1, amount);
        ps.setInt(2, accNo);

        ps.executeUpdate();
        con.close();
    }

    public void withdraw(int accNo, double amount) throws Exception {
        Connection con = BankUtil.getConnection();


        PreparedStatement check = con.prepareStatement(
                "SELECT balance FROM account WHERE acc_no=?"
        );
        check.setInt(1, accNo);
        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            double balance = rs.getDouble("balance");

            if (amount > balance) {
                throw new Exception("Insufficient Balance!");
            }


            PreparedStatement ps = con.prepareStatement(
                    "UPDATE account SET balance = balance - ? WHERE acc_no=?"
            );
            ps.setDouble(1, amount);
            ps.setInt(2, accNo);
            ps.executeUpdate();
        }

        con.close();
    }

    public double getBalance(int accNo) throws Exception {
        Connection con = BankUtil.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT balance FROM account WHERE acc_no=?"
        );
        ps.setInt(1, accNo);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("balance");
        } else {
            throw new Exception("Account not found!");
        }
    }

    public void addAccount(int accNo, String accountType, double balance) throws Exception {
        Connection con = BankUtil.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO account (acc_no, account_type, balance) VALUES (?, ?, ?)"
        );
        ps.setInt(1, accNo);
        ps.setString(2, accountType);
        ps.setDouble(3, balance);

        ps.executeUpdate();
        con.close();
    }

    public AccountDetails getAccountDetails(int accNo) throws Exception {
        Connection con = BankUtil.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT acc_no, account_type, balance FROM account WHERE acc_no=?"
        );
        ps.setInt(1, accNo);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            AccountDetails details = new AccountDetails();
            details.setAccountNumber(rs.getInt("acc_no"));
            details.setAccountType(rs.getString("account_type"));
            details.setBalance(rs.getDouble("balance"));
            con.close();
            return details;
        } else {
            con.close();
            throw new Exception("Account not found!");
        }
    }

    public static class AccountDetails {
        private int accountNumber;
        private String accountType;
        private double balance;

        public int getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}