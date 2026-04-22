<!DOCTYPE html>
<html>
<head>
    <title>Bank Management System</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            min-height: 100vh;
            background: #000000;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .main-container {
            width: 100%;
            max-width: 500px;
        }

        .header {
            text-align: center;
            color: white;
            margin-bottom: 30px;
        }

        .header h1 {
            font-size: 32px;
            margin-bottom: 5px;
            text-shadow: 0 4px 15px rgba(0,0,0,0.3);
            letter-spacing: 1px;
        }

        .header p {
            font-size: 14px;
            opacity: 0.9;
        }

        .tabs {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
            flex-wrap: wrap;
            justify-content: center;
        }

        .tab-btn {
            padding: 10px 15px;
            background: rgba(255, 255, 255, 0.2);
            border: 2px solid rgba(255, 255, 255, 0.3);
            color: white;
            cursor: pointer;
            border-radius: 8px;
            font-weight: bold;
            transition: 0.3s;
            font-size: 12px;
        }

        .tab-btn:hover {
            background: rgba(255, 255, 255, 0.3);
            border-color: white;
        }

        .tab-btn.active {
            background: white;
            color: #667eea;
            border-color: white;
        }

        .container {
            padding: 30px;
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(12px);
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0,0,0,0.3);
            color: #333;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #667eea;
            font-size: 24px;
        }

        label {
            font-size: 14px;
            color: #555;
            font-weight: 500;
            display: block;
            margin-bottom: 5px;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            outline: none;
            font-size: 14px;
            transition: 0.3s;
        }

        input:focus, select:focus {
            border-color: #667eea;
            box-shadow: 0 0 10px rgba(102, 126, 234, 0.2);
        }

        input::placeholder {
            color: #aaa;
        }

        input[type="submit"] {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            font-weight: bold;
            cursor: pointer;
            border: none;
            transition: 0.3s;
            font-size: 15px;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
        }

        .form-section {
            display: none;
        }

        .form-section.active {
            display: block;
        }

        .info-box {
            background: #f0f4ff;
            padding: 10px;
            border-radius: 8px;
            border-left: 4px solid #667eea;
            margin-bottom: 15px;
            font-size: 12px;
            color: #555;
        }

    </style>
</head>

<body>

    <div class="main-container">
        <div class="header">
            <h1>Bank Management System</h1>
            <p>Manage your accounts with ease</p>
        </div>

        <div class="tabs">
            <button class="tab-btn active" onclick="switchTab('transactions')">Transactions</button>
            <button class="tab-btn" onclick="switchTab('details')">Account Details</button>
            <button class="tab-btn" onclick="switchTab('add')">Add Account</button>
        </div>

        <div class="container">

            <!-- Transactions Tab -->
            <div id="transactions" class="form-section active">
                <h2>Transactions</h2>
                <form action="BankServlet" method="post">
                    <label>Account Number</label>
                    <input type="text" name="accNo" placeholder="Enter Account No" required>

                    <label>Amount (if applicable)</label>
                    <input type="text" name="amount" placeholder="Enter Amount">

                    <label>Select Action</label>
                    <select name="action" required>
                        <option value="">-- Select Action --</option>
                        <option value="deposit">Deposit Money</option>
                        <option value="withdraw">Withdraw Money</option>
                        <option value="check">Check Balance</option>
                    </select>

                    <input type="submit" value="Process">
                </form>
            </div>

            <!-- Account Details Tab -->
            <div id="details" class="form-section">
                <h2>Account Details</h2>
                <div class="info-box">View complete details of your account including type and current balance.</div>
                <form action="BankServlet" method="post">
                    <label>Account Number</label>
                    <input type="text" name="accNo" placeholder="Enter Account No" required>

                    <input type="hidden" name="action" value="details">
                    <input type="submit" value="View Details">
                </form>
            </div>

            <!-- Add Account Tab -->
            <div id="add" class="form-section">
                <h2>Create New Account</h2>
                <div class="info-box">Fill in the details to create a new bank account.</div>
                <form action="BankServlet" method="post">
                    <label>Account Number</label>
                    <input type="text" name="accNo" placeholder="Enter New Account No" required>

                    <label>Account Type</label>
                    <select name="accountType" required>
                        <option value="">-- Select Account Type --</option>
                        <option value="Savings">Savings Account</option>
                        <option value="Current">Current Account</option>
                        <option value="Salary">Salary Account</option>
                    </select>

                    <label>Initial Balance</label>
                    <input type="text" name="amount" placeholder="Enter Initial Balance" required>

                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Create Account">
                </form>
            </div>

        </div>
    </div>

    <script>
        function switchTab(tabName) {
            // Hide all sections
            const sections = document.querySelectorAll('.form-section');
            sections.forEach(section => section.classList.remove('active'));

            // Remove active class from all buttons
            const buttons = document.querySelectorAll('.tab-btn');
            buttons.forEach(btn => btn.classList.remove('active'));

            // Show selected section
            document.getElementById(tabName).classList.add('active');

            // Add active class to clicked button
            event.target.classList.add('active');
        }

        // Handle action change for transaction form
        function handleActionChange(selectElement) {
            const amountInput = selectElement.closest('form').querySelector('input[name="amount"]');
            const action = selectElement.value;
            
            if (action === 'check') {
                amountInput.required = false;
                amountInput.placeholder = 'Not required for balance check';
                amountInput.style.opacity = '0.6';
            } else if (action === 'deposit' || action === 'withdraw') {
                amountInput.required = true;
                amountInput.placeholder = 'Enter Amount';
                amountInput.style.opacity = '1';
            } else {
                amountInput.required = false;
                amountInput.placeholder = 'Enter Amount';
            }
        }

        // Set up action change listener when page loads
        document.addEventListener('DOMContentLoaded', function() {
            const actionSelects = document.querySelectorAll('select[name="action"]');
            actionSelects.forEach(select => {
                select.addEventListener('change', function() {
                    handleActionChange(this);
                });
            });
        });
    </script>

</body>
</html>