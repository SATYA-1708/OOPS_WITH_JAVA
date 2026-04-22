-- Drop existing account table if it exists
DROP TABLE IF EXISTS account;

-- Create new account table with complete structure
CREATE TABLE account (
    acc_no INT PRIMARY KEY,
    account_type VARCHAR(50) NOT NULL,
    balance DOUBLE NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data (optional)
INSERT INTO account (acc_no, account_type, balance) VALUES
(10001, 'Savings', 50000),
(10002, 'Current', 100000),
(10003, 'Salary', 75000);

-- Verify table structure
DESC account;

-- Show sample data
SELECT * FROM account;
