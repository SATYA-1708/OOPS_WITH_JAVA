-- =====================================================
-- COMPLETE BANK DATABASE SETUP SCRIPT
-- =====================================================
-- Run this script in MySQL Workbench to set up the entire database
-- =====================================================

-- Step 1: Drop existing database (if you want to start fresh)
DROP DATABASE IF EXISTS bankdb;

-- Step 2: Create the database
CREATE DATABASE bankdb;

-- Step 3: Use the database
USE bankdb;

-- =====================================================
-- Table 1: Account Table
-- =====================================================
CREATE TABLE account (
    acc_no INT PRIMARY KEY,
    account_type VARCHAR(50) NOT NULL,
    balance DOUBLE NOT NULL DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =====================================================
-- Insert Sample Data into Account Table
-- =====================================================
INSERT INTO account (acc_no, account_type, balance) VALUES
(10001, 'Savings', 50000),
(10002, 'Current', 100000),
(10003, 'Salary', 75000),
(10004, 'Savings', 25000),
(10005, 'Current', 150000);

-- =====================================================
-- Verify Account Table Structure
-- =====================================================
DESC account;

-- =====================================================
-- View All Data in Account Table
-- =====================================================
SELECT * FROM account;

-- =====================================================
-- Optional: Create Transaction History Table (for future use)
-- =====================================================
CREATE TABLE transaction_history (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    acc_no INT NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount DOUBLE NOT NULL,
    balance_after DOUBLE NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (acc_no) REFERENCES account(acc_no)
);

-- =====================================================
-- Optional: Create Customer Table (for future use)
-- =====================================================
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    address VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- Optional: Link Customer to Account
-- =====================================================
ALTER TABLE account ADD COLUMN customer_id INT;
ALTER TABLE account ADD FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

-- =====================================================
-- Database Setup Complete!
-- =====================================================
-- You can now test the application
-- Go to: http://localhost:8080/BankProject
-- =====================================================
