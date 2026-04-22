package com.bank.util;

import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;

public class BankUtil {

    // Generate random account number
    public static int generateAccountNumber() {
        Random r = new Random();
        return 10000 + r.nextInt(90000);
    }

    public static void validateMinimumBalance(double balance) throws Exception {
        if (balance < 1000) {
            throw new Exception("Minimum balance must be ₹1000");
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb",
                "root",
                "Satya@1708"
        );
    }
}