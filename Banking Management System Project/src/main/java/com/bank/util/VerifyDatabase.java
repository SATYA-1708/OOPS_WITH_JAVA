package com.bank.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class VerifyDatabase {

    public static void main(String[] args) {
        try {
            System.out.println("Connecting to database...");
            Connection con = BankUtil.getConnection();
            System.out.println("Connected successfully!");
            
            System.out.println("\n=== Checking Account Table Structure ===");
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet columns = dbmd.getColumns(null, null, "account", null);
            
            System.out.println("\nColumn Name | Data Type | Nullable");
            System.out.println("------------------------------------");
            boolean hasAccountType = false;
            while (columns.next()) {
                String colName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int nullable = columns.getInt("NULLABLE");
                System.out.println(colName + " | " + dataType + " | " + (nullable == 1 ? "Yes" : "No"));
                if ("account_type".equalsIgnoreCase(colName)) {
                    hasAccountType = true;
                }
            }
            
            if (hasAccountType) {
                System.out.println("\n✓ SUCCESS: account_type column EXISTS");
            } else {
                System.out.println("\n✗ ERROR: account_type column NOT FOUND");
                System.out.println("\nAdding account_type column now...");
                Statement stmt = con.createStatement();
                stmt.execute("ALTER TABLE account ADD COLUMN account_type VARCHAR(50)");
                System.out.println("✓ Column added successfully!");
            }
            
            System.out.println("\n=== Sample Data in Account Table ===");
            ResultSet data = con.createStatement().executeQuery("SELECT * FROM account");
            System.out.println("acc_no | account_type | balance");
            System.out.println("-------------------------------");
            while (data.next()) {
                System.out.println(data.getInt(1) + " | " + 
                                 (data.getString(2) != null ? data.getString(2) : "NULL") + " | " + 
                                 data.getDouble(3));
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
