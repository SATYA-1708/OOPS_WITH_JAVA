package com.bank.util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseMigration {

    public static void main(String[] args) {
        try {
            Connection con = BankUtil.getConnection();
            Statement stmt = con.createStatement();
            
            System.out.println("Dropping existing account table...");
            stmt.execute("DROP TABLE IF EXISTS account");
            System.out.println("Table dropped successfully");
            
            System.out.println("\nCreating new account table...");
            String createTableSQL = "CREATE TABLE account (" +
                    "acc_no INT PRIMARY KEY, " +
                    "account_type VARCHAR(50) NOT NULL, " +
                    "balance DOUBLE NOT NULL, " +
                    "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)";
            
            stmt.execute(createTableSQL);
            System.out.println("Table created successfully");
            
            System.out.println("\nInserting sample data...");
            String insertSQL = "INSERT INTO account (acc_no, account_type, balance) VALUES " +
                    "(10001, 'Savings', 50000), " +
                    "(10002, 'Current', 100000), " +
                    "(10003, 'Salary', 75000)";
            
            stmt.execute(insertSQL);
            System.out.println("Sample data inserted");
            
            System.out.println("\nVerifying table structure:");
            ResultSet rs = stmt.executeQuery("DESC account");
            System.out.println("\n| Field | Type | Null | Key | Default | Extra |");
            System.out.println("|-------|------|------|-----|---------|-------|");
            while (rs.next()) {
                System.out.println("| " + rs.getString(1) + " | " + rs.getString(2) + 
                                 " | " + rs.getString(3) + " | " + rs.getString(4) + 
                                 " | " + rs.getString(5) + " | " + rs.getString(6) + " |");
            }
            
            System.out.println("\n\nDisplaying sample data:");
            rs = stmt.executeQuery("SELECT * FROM account");
            System.out.println("| acc_no | account_type | balance |");
            System.out.println("|--------|--------------|---------|");
            while (rs.next()) {
                System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + 
                                 " | " + rs.getDouble(3) + " |");
            }
            
            rs.close();
            stmt.close();
            con.close();
            
            System.out.println("\n\nSUCCESS: Database setup completed!");
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
