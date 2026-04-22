<%
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");
    Double balance = (Double) request.getAttribute("balance");
    String action = (String) request.getAttribute("action");
    Integer accountNumber = (Integer) request.getAttribute("accountNumber");
    String accountType = (String) request.getAttribute("accountType");
    Integer accNo = (Integer) request.getAttribute("accNo");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Transaction Result</title>
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

        .result-container {
            width: 100%;
            max-width: 500px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.3);
            overflow: hidden;
        }

        .result-header {
            padding: 30px;
            text-align: center;
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
        }

        .result-header.success {
            background: linear-gradient(to right, #11998e, #38ef7d);
        }

        .result-header.error {
            background: linear-gradient(to right, #eb3349, #f45c43);
        }

        .result-icon {
            font-size: 48px;
            margin-bottom: 15px;
        }

        .result-header h2 {
            font-size: 24px;
            margin-bottom: 5px;
        }

        .result-header p {
            font-size: 14px;
            opacity: 0.9;
        }

        .result-body {
            padding: 30px;
        }

        .info-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 0;
            border-bottom: 1px solid #e0e0e0;
            font-size: 15px;
        }

        .info-row:last-child {
            border-bottom: none;
        }

        .info-label {
            color: #667eea;
            font-weight: 600;
        }

        .info-value {
            color: #333;
            font-weight: 500;
        }

        .balance-highlight {
            background: #f0f4ff;
            padding: 15px;
            border-radius: 8px;
            border-left: 4px solid #667eea;
            margin: 15px 0;
        }

        .balance-highlight .balance-amount {
            font-size: 28px;
            font-weight: bold;
            color: #667eea;
        }

        .error-message {
            background: #ffebee;
            padding: 15px;
            border-radius: 8px;
            border-left: 4px solid #eb3349;
            margin: 15px 0;
            color: #c62828;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 25px;
        }

        .btn {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            transition: 0.3s;
            font-size: 14px;
        }

        .btn-primary {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
        }

        .btn-secondary {
            background: #e0e0e0;
            color: #333;
        }

        .btn-secondary:hover {
            background: #d0d0d0;
        }

        .details-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin: 15px 0;
        }

        .detail-card {
            background: #f5f5f5;
            padding: 15px;
            border-radius: 8px;
            border: 1px solid #e0e0e0;
        }

        .detail-card .label {
            color: #666;
            font-size: 12px;
            margin-bottom: 5px;
            text-transform: uppercase;
        }

        .detail-card .value {
            color: #333;
            font-size: 18px;
            font-weight: bold;
        }

    </style>
</head>

<body>

    <div class="result-container">

        <% if (error != null) { %>
            <div class="result-header error">
                <div class="result-icon">!</div>
                <h2>Transaction Failed</h2>
                <p>An error occurred</p>
            </div>
            <div class="result-body">
                <div class="error-message">
                    <%= error %>
                </div>
                <div class="button-group">
                    <a href="index.jsp" class="btn btn-primary">Back to Home</a>
                </div>
            </div>

        <% } else if ("add".equals(action)) { %>
            <div class="result-header success">
                <div class="result-icon">[OK]</div>
                <h2>Account Created Successfully</h2>
                <p>Your new account is ready to use</p>
            </div>
            <div class="result-body">
                <div class="balance-highlight">
                    <div style="font-size: 12px; color: #999; margin-bottom: 5px;">Initial Balance</div>
                    <div class="balance-amount"><%= String.format("%.2f", balance) %></div>
                </div>
                <div class="details-grid">
                    <div class="detail-card">
                        <div class="label">Account Number</div>
                        <div class="value"><%= accNo %></div>
                    </div>
                    <div class="detail-card">
                        <div class="label">Account Type</div>
                        <div class="value"><%= accountType %></div>
                    </div>
                </div>
                <div class="button-group">
                    <a href="index.jsp" class="btn btn-primary">New Transaction</a>
                    <a href="index.jsp" class="btn btn-secondary">Back Home</a>
                </div>
            </div>

        <% } else if ("details".equals(action)) { %>
            <div class="result-header success">
                <h2>Account Details</h2>
                <p>Complete account information</p>
            </div>
            <div class="result-body">
                <div class="balance-highlight">
                    <div style="font-size: 12px; color: #999; margin-bottom: 5px;">Current Balance</div>
                    <div class="balance-amount"><%= String.format("%.2f", balance) %></div>
                </div>
                <div class="details-grid">
                    <div class="detail-card">
                        <div class="label">Account Number</div>
                        <div class="value"><%= accountNumber %></div>
                    </div>
                    <div class="detail-card">
                        <div class="label">Account Type</div>
                        <div class="value"><%= accountType %></div>
                    </div>
                </div>
                <div class="button-group">
                    <a href="index.jsp" class="btn btn-primary">Back to Home</a>
                </div>
            </div>

        <% } else { %>
            <div class="result-header success">
                <div class="result-icon">[OK]</div>
                <h2>Transaction Successful</h2>
                <p><%= message %></p>
            </div>
            <div class="result-body">
                <div class="balance-highlight">
                    <div style="font-size: 12px; color: #999; margin-bottom: 5px;">Current Balance</div>
                    <div class="balance-amount"><%= String.format("%.2f", balance) %></div>
                </div>
                <div class="info-row">
                    <span class="info-label">Account Number:</span>
                    <span class="info-value"><%= accNo %></span>
                </div>
                <div class="info-row">
                    <span class="info-label">Transaction Type:</span>
                    <span class="info-value">
                        <% 
                            if ("deposit".equals(action)) { 
                                out.print("Deposit");
                            } else if ("withdraw".equals(action)) {
                                out.print("Withdrawal");
                            } else {
                                out.print("Balance Check");
                            }
                        %>
                    </span>
                </div>
                <div class="button-group">
                    <a href="index.jsp" class="btn btn-primary">New Transaction</a>
                    <a href="index.jsp" class="btn btn-secondary">Back Home</a>
                </div>
            </div>
        <% } %>

    </div>

</body>
</html>